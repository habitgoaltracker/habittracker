package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    /**
     * Finds a habit by its id.
     * @param ID The id of the task.
     * @return the task (or tasks) that have an equivalent ID (if any).
     */
    Task findByID(long ID);

    /**
     * Saves the requested task.
     * @param task the task to be saved.
     * @return the task to be saved.
     */
    Task save(Task task);

    /**
     * Finds all existing habits.
     * @return
     */
    List<Task> findAll();

    /**
     * Deletes the requested task by id.
     * @param ID the id of the task
     */
    void deleteByID(long ID);

    /**
     * Updates the requested task by id.
     * @param ID the id of the task.
     */
    void updateTaskByID(long ID);

    /**
     * Updates the progress of the goal that references a task by the tasks' id.
     * @param ID the id of the task.
     */
    void newTaskUpdateByID(long ID);
}
