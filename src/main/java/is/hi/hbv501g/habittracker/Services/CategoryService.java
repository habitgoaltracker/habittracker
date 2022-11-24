package is.hi.hbv501g.habittracker.Services;


import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public interface CategoryService {

    /**
     * Finds a category by its ID.
     * @param ID the ID of the category.
     * @return the category (or categories) that have an equivalent ID (if any).
     */
    Category findByID(long ID);

    /**
     * Finds all existing categories.
     * @return a list of all categories that exist (if any).
     */
    List<Category> findAll(HttpSession session);

    /**
     * Saves the requested category.
     * @param category the category to be saved.
     * @return the category to be saved.
     */
    Category save(Category category);

    /**
     * Deletes the requested category.
     * @param id the ID of the category to be deleted.
     */
    void deleteByID(long id);

    /**
     * Updates the requested category.
     * @param id the ID of the category to be updated.
     */
    List<Goal> getGoalsByID(long id);

    /**
     * Gets all habits of a category and returns them.
     *
     * @param id the ID of the category to get the habits.
     * @return habits of the category.
     */
    List<Habit> getHabitsByID(long id);
}
