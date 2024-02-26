package SpringDB;

import SpringDB.model.Performer;
import SpringDB.repository.PerformerRepository;
import SpringDB.repository.TaskRepository;
import SpringDB.service.PerformerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PerformerServiceTest {
    @InjectMocks
    private PerformerService performerService;
    @Mock
    private PerformerRepository performerRepository;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void findAllTest() {
        Performer performer = new Performer();
        performerRepository.save(performer);

        List<Performer> expected = Collections.singletonList(performer);
        when(performerRepository.findAll()).thenReturn(expected);

        List<Performer> actual = performerService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void findPerformerByIdTest() {

    }

    @Test
    public void saveTest() {

    }

    @Test
    public void updatePerformerNameTest() {

    }

    @Test
    public void deletePerformerTest() {

    }

    @Test
    public void clearListPerformerTest() {

    }

}
