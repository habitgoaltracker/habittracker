package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import is.hi.hbv501g.habittracker.Persistence.Repositories.HabitRepository;
import is.hi.hbv501g.habittracker.Persistence.Repositories.TaskRepository;
import is.hi.hbv501g.habittracker.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImplementation implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
       return taskRepository.save(task);
    }

    @Override
    public void deleteByID(long ID) {
        taskRepository.deleteById(ID);
    }
}
