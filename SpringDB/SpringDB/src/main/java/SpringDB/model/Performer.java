package SpringDB.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "performers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    // Variations
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Task> tasks;
}
