package com.example.sprintplannerx.Service;

import com.example.sprintplannerx.Entities.Event;
import com.example.sprintplannerx.Repository.EventRepository;
import com.example.sprintplannerx.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createNewEvent(Event newEvent) {
        Event event = new Event();
        event.setEventName(newEvent.getEventName());
        event.setStartDate(newEvent.getStartDate());
        event.setEndDate(newEvent.getEndDate());
        event.setLead(userRepository.findByUsername(newEvent.getLead().getUsername()).orElse(null));
        eventRepository.save(event);
        return event;
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


    public List<Event> getRegisteredEvents(String username) {
        return eventRepository.getRegisteredByUsername(username);
    }

    public void deleteEventsByTaskId(Long taskId) {
        eventRepository.findByTasksId(taskId).forEach(event -> {
            event.getTasks().removeIf(task -> task.getID().equals(taskId));
            eventRepository.save(event);
        });
    }


}
