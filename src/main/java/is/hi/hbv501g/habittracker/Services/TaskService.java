package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    Task save(Task task);
    void deleteByID(long ID);
}
