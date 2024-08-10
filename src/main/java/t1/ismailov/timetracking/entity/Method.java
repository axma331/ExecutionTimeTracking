package t1.ismailov.timetracking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "methods")
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "declared_type", nullable = false)
    private String declaredType;                         //Класс которому принадлежит метод

    @Column(name = "group_method", nullable = false)
    private String group;                               //Пакет в котором лежит класс

    @OneToMany(mappedBy = "methodId")
    private List<ExecutionTime> executionTimes;
}
