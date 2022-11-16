package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoalService {

    /**
     *
     * Saves a goal to the database - or - Finds a goal by its name.
     * @param goal the goal to be saved - or - the name of the goal.
     * @return the saved goal - or - the goal equivalent to the name (if any).
     */
    Goal findByName(String goal);

    /**
     * Finds a goal by its ID.
     * @param id the ID of the goal.
     * @return the goal (or goals) that have an equivalent ID (if any).
     */
    Goal findByID(long id);

    /**
     * Finds all existing goals.
     * @return a list of all goals that exist (if any).
     */
    List<Goal> findAll();

    /**
     * Saves the requested goal.
     * @param goal the goal to be saved.
     * @return the goal to be saved.
     */
    Goal save(Goal goal);

    /**
     * Deletes the requested goal.
     * @param id the ID of the goal to be deleted.
     */
    void deleteByID(long id, long idCat);

    /**
     * Updates the requested goal.
     * @param id the ID of the goal to be updated.
     */
    void updateGoalByID(long id);


}
