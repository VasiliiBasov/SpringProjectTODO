package com.todo.service;

import com.todo.entity.Status;
import com.todo.entity.Task;
import com.todo.repository.TaskRepository;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Data
public class TaskService {
    private final TaskRepository taskRepository;

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAll(String order, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order));
        return taskRepository.findAll(pageable).toList();
    }

    public Integer getAllCount(String description, Status status) {
        return taskRepository.getAllCount(description, status);
    }

    public Task updateTask(long id, String description, Status status) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        task.setDescription(description);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task updateTask(long id, String description, Status status, BigInteger time) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        task.setDescription(description);
        task.setStatus(status);
        task.setTime(time);
        return taskRepository.save(task);
    }

    public Task delete(long id) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        taskRepository.delete(task);
        return task;
    }
}
