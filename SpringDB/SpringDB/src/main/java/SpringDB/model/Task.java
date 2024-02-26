package SpringDB.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
//    @Enumerated(EnumType.STRING)
    private taskStatus status;
    @OneToMany
    @JoinColumn(name = "performer_id")
    private List<Performer> performers;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String date;

    public enum taskStatus {
        ToDo,
        InProgress,
        Done;
    }

    @Override
    public String toString() {
        return "Task(" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", performers=" + performers +
                ", date='" + date + '\'' +
                ')';
    }
}
