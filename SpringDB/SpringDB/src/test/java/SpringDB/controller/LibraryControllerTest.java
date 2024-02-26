package SpringDB.controller;

import SpringDB.repository.PerformerRepository;
import SpringDB.repository.TaskRepository;
import SpringDB.service.PerformerService;
import SpringDB.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LibraryControllerTest {
    @InjectMocks
    private PerformerService performerService;
    @InjectMocks
    private TaskService taskService;
    @Mock
    private PerformerRepository performerRepository;
    @Mock
    private TaskRepository taskRepository;
    @BeforeEach
    private void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void loginEndpoint() {

    }

}
