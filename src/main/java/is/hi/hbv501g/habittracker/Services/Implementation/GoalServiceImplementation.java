package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Repositories.GoalRepository;
import is.hi.hbv501g.habittracker.Services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImplementation implements GoalService {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalServiceImplementation(GoalRepository goalRepository){
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal save(Goal goal) {
        return this.goalRepository.save(goal);
    }

    @Override
    public void deleteByID(long ID) {
        this.goalRepository.deleteById(ID);
    }

    @Override
    public List<Goal> findAll(){
        return this.goalRepository.findAll();
    }
}
