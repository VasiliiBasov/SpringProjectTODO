package com.todo.service;

import com.todo.entity.Task;
import com.todo.repository.TaskRepository;
import lombok.Data;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.math.BigInteger;

@Service
@Data
public class TimeService {
    private final TaskRepository taskRepository;

    public void start(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        BigInteger time = task.getTime();
        if (time.longValue() < 0) return;
        BigInteger currentTime = BigInteger.valueOf(System.currentTimeMillis());
        time = time.subtract(currentTime);

        System.out.println(time);

        task.setTime(time);
        taskRepository.save(task);
    }

    public void stop(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        BigInteger time = task.getTime();
        if (time.longValue() > 0) return;
        BigInteger currentTime = BigInteger.valueOf(System.currentTimeMillis());
        time = time.add(currentTime);

        task.setTime(time);
        taskRepository.save(task);
    }
}
