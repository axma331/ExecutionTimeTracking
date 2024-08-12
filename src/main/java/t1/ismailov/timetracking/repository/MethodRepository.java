package t1.ismailov.timetracking.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import t1.ismailov.timetracking.entity.ExecutionTime;
import t1.ismailov.timetracking.entity.Method;

import java.util.List;
import java.util.Optional;

@Repository
public interface MethodRepository extends JpaRepository<Method, Long> {

    @EntityGraph(attributePaths = {"executionTimes"})
    Optional<Method> findByNameAndDeclaredType(String name, String declaredType);

    @EntityGraph(attributePaths = {"executionTimes"})
    Optional<Method> findById(long id);

    @EntityGraph(attributePaths = {"executionTimes"})
    List<Method> findAllByGroup(String group);

    @Query("SELECT e FROM ExecutionTime e WHERE e.async = :isAsync")
    List<ExecutionTime> findExecutionTimeByAsync(@Param("isAsync") boolean isAsync);

    @Query("SELECT e FROM ExecutionTime e ")
    List<ExecutionTime> findAllExecutionTimes();
}
