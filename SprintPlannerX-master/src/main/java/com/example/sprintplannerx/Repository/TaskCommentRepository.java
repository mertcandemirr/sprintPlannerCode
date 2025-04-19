package com.example.sprintplannerx.Repository;

import com.example.sprintplannerx.Entities.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
}
