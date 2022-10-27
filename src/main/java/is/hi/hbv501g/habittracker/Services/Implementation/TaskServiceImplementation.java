package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import is.hi.hbv501g.habittracker.Persistence.Repositories.GoalRepository;
import is.hi.hbv501g.habittracker.Persistence.Repositories.TaskRepository;
import is.hi.hbv501g.habittracker.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Documentation of following methods can be found in the corresponding Service interface.
 */
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
        return taskRepository.findByID(ID);
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
        newTaskUpdateByID(ID);
        goalRepository.save(goal);
        taskRepository.deleteById(ID);
    }

    @Override
    public void updateTaskByID(long ID) {
        Task task = findByID(ID);
        Goal goal = task.getTaskGoal();
        List<Task> tasks = goal.getTasks();
        task.setTaskCompleted(true);
        int completed = 0;
        for (Task value : tasks) {
            if (value.isTaskCompleted()) {
                completed = completed + 1;
            }
        }
        double progress = ((double)completed)/((double)tasks.size());
        double progressRounded = Math.round(progress*100.0)/100.0;
        goal.setGoalProgress(progressRounded);
        goalRepository.save(goal);
        taskRepository.save(task);
    }

    @Override
    public void newTaskUpdateByID(long ID) {
        Task task = findByID(ID);
        Goal goal = task.getTaskGoal();
        List<Task> tasks = goal.getTasks();
        int completed = 0;
        for (Task value : tasks) {
            if (value.isTaskCompleted()) {
                completed = completed + 1;
            }
        }
        double progress = ((double)completed)/((double)tasks.size());
        double progressRounded = Math.round(progress*100.0)/100.0;
        goal.setGoalProgress(progressRounded);
        goalRepository.save(goal);
    }
}
