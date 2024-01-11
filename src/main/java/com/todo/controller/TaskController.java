package com.todo.controller;

import com.todo.dto.TaskDto;
import com.todo.entity.Status;
import com.todo.entity.Task;
import com.todo.service.TaskService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
@RestController
@RequestMapping("/rest/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public List<TaskDto> getAll(@RequestParam(required = false) String description,
                                @RequestParam(required = false) Status status,
                                @RequestParam(required = false) Time time,
                                @RequestParam(required = false) TaskOrder order,
                                @RequestParam(required = false) Integer pageNumber,
                                @RequestParam(required = false) Integer pageSize) {
        order = isNull(order) ? TaskOrder.ID : order;
        pageNumber = isNull(pageNumber) ? 0 : pageNumber;
        pageSize = isNull(pageSize) ? 3 : pageSize;

        List<Task> tasks = taskService.getAll(order.getFieldName(), pageNumber, pageSize);
        return tasks.stream().map(TaskController::toTaskDto).collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Integer getAllCount(@RequestParam(required = false) String description,
                               @RequestParam(required = false) Status status) {

        return taskService.getAllCount(description, status);
    }

//    @PostMapping
//    public ResponseEntity<TaskInfo> createPlayer(@RequestBody TaskInfo info) {
//        if (StringUtils.isEmpty(info.description)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        if (isNull(info.status)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        Task task = new Task(info.description, info.status, info.time);
//        taskService.createTask(task);
//        return ResponseEntity.status(HttpStatus.OK).body(toTaskInfo(task));
//    }

    @GetMapping("/{ID}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("ID") long id) {
        if (id <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        Task task = taskService.getTask(id);
        if (isNull(task)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(toTaskDto(task));
        }
    }

    @PostMapping("/{ID}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("ID") long id,
                                              @RequestBody TaskDto info) {
        if (id <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        if (nonNull(info.description) || info.description.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        Task task = taskService.updateTask(id, info.description, info.status);
        if (isNull(task)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(toTaskDto(task));
        }
    }

    @PostMapping("/delete/{ID}")
    public ResponseEntity delete(@PathVariable("ID") long id) {
        if (id <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        Task task = taskService.delete(id);
        if (isNull(task)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    private static TaskDto toTaskDto(Task task) {
        if (isNull(task)) return null;

        TaskDto result = new TaskDto();
        result.id = task.getId();
        result.description = task.getDescription();
        result.status = task.getStatus();
        //result.time = task.getTime();
        return result;
    }
}
