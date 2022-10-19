package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoalService {

    Goal save(Goal goal);
    void deleteByID(long ID);
    List<Goal> findAll();

}