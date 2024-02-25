package SpringDB.controller;

import SpringDB.model.Performer;
import SpringDB.model.Task;
import SpringDB.service.PerformerService;
import SpringDB.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.weaver.patterns.PerFromSuper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 1. Добавление задачи:
 * POST localhost:8080/api/tasks
 * 2. Просмотр всех задач:
 * GET localhost:8080/api/tasks
 * 3. Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
 * GET localhost:8080/api/tasks/filter/status
 * 4. Изменение статуса задачи.
 * PUT localhost:8080/api/tasks/update/id
 * 5. Удаление задачи.
 * DELETE localhost:8080/api/tasks/id
 * ДОПОЛНИТЕЛЬНО:
 * 6. Сортировка по id:
 * localhost:8080/api/tasks/sort
 * 7. найти задачу по id:
 * localhost:8080/api/tasks/id
 */
@RestController
@Tag(name = "Task API")
public class LibraryController {
    @Autowired
    private final TaskService taskService;

    @Autowired
    public LibraryController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    private PerformerService performerService;

    @GetMapping("/login")
    public String loginEndpoint() {
        return "You need enter login and password";
    }

    @GetMapping("/tasks")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/tasks/filter/{status}")
    public List<Task> filterByStatus(@PathVariable Task.taskStatus status) {
        return taskService.filterByStatus(status);
    }

    @PutMapping("/updateStatus/{id}")
    public Task updateStatusInTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateStatusInTask(id, task);
    }

    @DeleteMapping("tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/tasks/{id}/performers/{performerId}")
    public Task assignPerformerToTask(@PathVariable Long id, @PathVariable Long performerId) {
        return taskService.assignPerformerToTask(id, performerId);
    }

    //    @PutMapping("/performers/{id}/tasks/{taskId}")
//    public Performer assignTaskToPerformer(@PathVariable(name = "id") Long id, @PathVariable(name = "taskId") Long taskId){
//        return performerService.assignTaskToPerformer(id, taskId);
//    }
    @DeleteMapping("/tasks/{id}/performers/{performerId}")
    public Task deassignPerformerToTask(@PathVariable Long id, @PathVariable Long performerId) {
        return taskService.deassingPerformerToTask(id, performerId);
    }

    @GetMapping("/performers")
    public List<Performer> findAll() {
        return performerService.findAll();
    }

    @PostMapping("/performers")
    public Performer save(@RequestBody Performer performer) {
        return performerService.save(performer);
    }

    @GetMapping("/performers/{id}")
    public Performer findPerformerById(@PathVariable Long id) {
        return performerService.findPerformerById(id);
    }

    @PutMapping("/performers/{id}")
    public Performer updatePerformerById(@PathVariable Long id, @RequestBody Performer performer) {
        return performerService.updatePerformerName(id, performer);
    }

    @DeleteMapping("/performers/{id}")
    public void deletePerformerById(@PathVariable Long id) {
        performerService.deletePerformer(id);
    }


    @DeleteMapping("/performersDeleteAll")
    public void deletePerformersAll() {
        performerService.clearListPerformer();
    }
}
