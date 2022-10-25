package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    Task findByID(long ID);
    Task save(Task task);
    List<Task> findAll();
    void deleteByID(long ID);
    void updateTaskByID(long id);
}
