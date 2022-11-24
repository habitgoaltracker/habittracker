package is.hi.hbv501g.habittracker.Persistence.Repositories;

import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task save(Task task);
    void delete(Task task);
    List<Task> findAll();
    Task findByID(long id);
}
