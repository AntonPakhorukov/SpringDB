package SpringDB;

import SpringDB.model.Task;
import SpringDB.repository.TaskRepository;
import SpringDB.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTaskTest() {
        Task task = new Task();
        taskService.createTask(task);
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
    }

    @Test
    public void getAllTasksTest() {
        taskService.createTask(new Task());
        taskService.createTask(new Task());
        List<Task> expected = taskRepository.findAll();

        List<Task> actualList = taskService.getAllTask();

        assertEquals(expected, actualList);
    }

    @Test
    public void getTaskByIdTest() {
        Task task = new Task();
        task.setDescription("getTaskByIdOne");
        taskRepository.save(task);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task currentTask = taskService.getTaskById(1L);

        assertEquals(task.getDescription(), currentTask.getDescription());
    }

    @Test
    public void filterByStatusTest() {
        Task expected1 = new Task();
        expected1.setStatus(Task.taskStatus.ToDo);
        taskService.createTask(expected1);

        Task expected2 = new Task();
        expected1.setStatus(Task.taskStatus.ToDo);
        taskService.createTask(expected2);

        Task expected3 = new Task();
        expected1.setStatus(Task.taskStatus.InProgress);
        taskService.createTask(expected3);

        List<Task> expected = taskRepository.filterByStatus("ToDo");

        when(taskRepository.filterByStatus("ToDo")).thenReturn(expected);

        List<Task> currentList = taskService.filterByStatus(Task.taskStatus.ToDo);

        assertEquals(expected.size(), currentList.size());
    }

    @Test
    public void updateTaskByStatus(){
        Task task = new Task();
        task.setId(1L);
        task.setStatus(Task.taskStatus.ToDo);
        taskService.createTask(task);
        Task updateTask = new Task();
        updateTask.setStatus(Task.taskStatus.InProgress);
        taskService.updateStatusInTask(1L, updateTask);
        assertEquals(task.getStatus(), updateTask.getStatus());
//        Mockito.verify(taskRepository, Mockito.times(1)).updateStatusInTask("InProgress", 1L);

// Не сработала, не смог разобрать причину, ПОМОГИТЕ ПОНЯТЬ))

//        Task expected = taskRepository.updateStatusInTask("InProgress", 1L);
//
//        Task updateTask = new Task();
//        updateTask.setStatus(Task.taskStatus.InProgress);
//        updateTask.setId(1L);
//        Task currentTask = taskService.updateStatusInTask(1L, "InProgress");
//
//        assertEquals(expected, currentTask);

    }
}
