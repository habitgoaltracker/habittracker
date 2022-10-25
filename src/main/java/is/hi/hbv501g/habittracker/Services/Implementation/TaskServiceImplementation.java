package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import is.hi.hbv501g.habittracker.Persistence.Repositories.GoalRepository;
import is.hi.hbv501g.habittracker.Persistence.Repositories.TaskRepository;
import is.hi.hbv501g.habittracker.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;
    private final GoalRepository goalRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository, GoalRepository goalRepository){
        this.taskRepository = taskRepository;
        this.goalRepository = goalRepository;
    }

    @Override
    public Task findByID(long ID) {
        return null;
    }

    @Override
    public Task save(Task task) {
       return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public void deleteByID(long ID) {
        Task task = taskRepository.findByID(ID);
        Goal goal = task.getTaskGoal();
        List<Task> taskList = goal.getTasks();
        taskList.remove(task);
        goalRepository.save(goal);
        taskRepository.deleteById(ID);
    }

    @Override
    public void updateTaskByID(long ID) {
        // TODO
        this.deleteByID(ID); //temporary
    }
}
