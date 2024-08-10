package t1.ismailov.timetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1.ismailov.timetracking.entity.ExecutionTime;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {
}
