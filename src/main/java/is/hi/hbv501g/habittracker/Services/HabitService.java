package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HabitService {

    /**
     * Finds a habit by its name.
     * @param name The name of the habit.
     * @return The habit (or habits) equivalent to the name (if any).
     */
    Habit findByName(String name);

    /**
     * Finds a habit by its ID.
     * @param ID The ID of the habit.
     * @return The habit (or habits) that have an equivalent ID (if any).
     */
    Habit findByID(long ID);

    /**
     * Finds all existing habits.
     *
     * @return A list of all habits that exist (if any).
     */
    List<Habit> findAll();

    /**
     * Saves the requested habit.
     * @param habit Habit to be saved.
     * @return Habit to be saved.
     */
    Habit save(Habit habit);

    /**
     * Deletes the requested habit.
     * @param id ID of the habit to be deleted.
     */
    void deleteByID(long id);

    /** Updates the requested habit.
     * @param id ID of the habit to be updated.
     */
    void updateHabitByID(long id);
}
