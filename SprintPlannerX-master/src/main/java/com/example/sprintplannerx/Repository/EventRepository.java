package com.example.sprintplannerx.Repository;

import com.example.sprintplannerx.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventByEventName(String eventName);

    @Query("select distinct t.Event from Task t where ((t.Developer.username =:username)or (t.Analyst.username=:username))")
    List<Event> getRegisteredByUsername(@Param("username") String username);

    @Query("SELECT e FROM Event e JOIN e.tasks t WHERE t.ID = :taskId")
    List<Event> findByTasksId(@Param("taskId") Long taskId);

}
