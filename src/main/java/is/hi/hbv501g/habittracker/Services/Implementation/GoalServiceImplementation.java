package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Repositories.GoalRepository;
import is.hi.hbv501g.habittracker.Services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Documentation of following methods can be found in the corresponding Service interface.
 */
@Service
public class GoalServiceImplementation implements GoalService {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalServiceImplementation(GoalRepository goalRepository){
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal findByName(String name) {
        return goalRepository.findByName(name).get(0);
    }

    @Override
    public Goal findByID(long id) {
        return goalRepository.findByID(id);
    }

    @Override
    public List<Goal> findAll(){
        return this.goalRepository.findAll();
    }

    @Override
    public Goal save(Goal goal) {
        return this.goalRepository.save(goal);
    }

    @Override
    public void deleteByID(long id) {
        this.goalRepository.deleteById(id);
    }


    @Override
    public void updateGoalByID(long id){
        Goal goal = goalRepository.findByID(id);
        LocalDate currDate = LocalDate.now();
        if(goal.getGoalDueDate().isBefore(currDate)){
            goal.setGoalDueDate(null);
        }
    }
}
