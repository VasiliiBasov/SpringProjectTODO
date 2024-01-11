package com.todo.repository;

import com.todo.entity.Status;
import com.todo.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @Query("select count(s) from Task s where " +
            "(:description is null or s.description like %:description%) and " +
            "(:status is null or s.status = :status)")
    Integer getAllCount(@Param("description") String description,
                        @Param("status") Status status);
}
