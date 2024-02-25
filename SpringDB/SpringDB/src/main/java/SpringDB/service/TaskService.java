package SpringDB.service;

import SpringDB.repository.TaskRepository;
import SpringDB.aspect.TrackUserAction;
import SpringDB.model.Performer;
import SpringDB.model.Task;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. Добавление задачи.
 * 2. Просмотр всех задач.
 * 3. Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
 * 4. Изменение статуса задачи.
 * 5. Удаление задачи.
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PerformerService performerService;

    @TrackUserAction
    public Task createTask(Task task) {
        task.setDate(String.valueOf(Calendar.getInstance().getTime()));
        return taskRepository.save(task);
    }

    @TrackUserAction
    public List<Task> getAllTask() {
        return taskRepository.findAll().stream().sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
    }

    @TrackUserAction
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No task with id: " + id));
    }

    @TrackUserAction
    public List<Task> filterByStatus(Task.taskStatus status) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @TrackUserAction
    public Task updateStatusInTask(Long id, Task task) {
        Task currentTask = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        currentTask.setId(task.getId());
        currentTask.setDescription(task.getDescription());
        currentTask.setPerformers(task.getPerformers());
        currentTask.setDate(task.getDate());
        currentTask.setStatus(task.getStatus());
        return taskRepository.save(currentTask);
    }

    @TrackUserAction
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @TrackUserAction
    public Task assignPerformerToTask(Long id, Long performerId) {
        Task existingTask = getTaskById(id); // Нашли задачу
        Performer performer = performerService.findPerformerById(performerId); // нашли исполнителя
        existingTask.getPerformers().add(performer); // у задачи добавили исполнителя в список исполнителей
        taskRepository.save(existingTask);
        return existingTask;
    }


    @TrackUserAction
    public Task deassingPerformerToTask(Long id, Long performerId) {
        Task existingTask = getTaskById(id);
        existingTask.getPerformers().removeIf(performer -> performer.getId().equals(performerId));
        Performer performer = performerService.findPerformerById(performerId);
        return taskRepository.save(existingTask);
    }

}
