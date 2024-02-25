package SpringDB.repository;

import SpringDB.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.status=ToDo")
    List<Task> filterByStatus(String email);

//    @Query("UPDATE Task t SET t.status=?1 WHERE t.id=?2")
//    Task updateStatusInTask(String status, Long id);
}
