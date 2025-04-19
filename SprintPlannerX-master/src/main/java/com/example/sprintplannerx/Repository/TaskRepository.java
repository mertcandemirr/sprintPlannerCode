package com.example.sprintplannerx.Repository;

import com.example.sprintplannerx.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT COUNT(*) FROM tasks" +
            " WHERE ((developer_id = (SELECT id FROM users WHERE username = :username))" +
            " OR (analyst_id = (SELECT id FROM users WHERE username = :username)))" +
            " AND status = 'done'", nativeQuery = true)
    int getDoneTaskCountForUser(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM tasks  WHERE ((developer_id = (SELECT id FROM users WHERE username = :username)) OR (analyst_id = (SELECT id FROM users WHERE username = :username))) AND status = 'todo'", nativeQuery = true)
    int getToDoTaskCountForUser(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM tasks" +
            " WHERE ((developer_id = (SELECT id FROM users WHERE username = :username))" +
            " OR (analyst_id = (SELECT id FROM users WHERE username = :username)))" +
            " AND status = 'overdue'", nativeQuery = true)
    int getOverdueTaskCountForUser(@Param("username") String username);

    @Query("SELECT t FROM Task t WHERE  t.Status = :status")
    List<Task> findByStatus(@Param("status") String status);

    @Query("SELECT t FROM Task t WHERE (t.Developer.username = :username OR t.Analyst.username = :username) AND t.Status = :status")
    List<Task> findByUserAndStatus(@Param("username") String username, @Param("status") String status);

    @Query("SELECT t FROM Task t WHERE (t.Developer.username = :username OR t.Analyst.username = :username) ORDER BY t.DueDate ASC")
    List<Task> findAllOrderByDueDate(@Param("username") String username);

    Task getTaskByID(Long taskId);

    @Query("SELECT t FROM Task t WHERE (t.Developer.username = :username OR t.Analyst.username = :username) AND t.isStarred = true")
    List<Task> getStarredTasksByUsername(@Param("username") String username);

}
