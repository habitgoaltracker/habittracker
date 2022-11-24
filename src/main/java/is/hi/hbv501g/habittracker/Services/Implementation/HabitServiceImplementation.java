package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Repositories.HabitRepository;
import is.hi.hbv501g.habittracker.Services.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Documentation of following methods can be found in the corresponding Service interface.
 */
@Service
public class HabitServiceImplementation implements HabitService {

    private final HabitRepository habitRepository;

    /**
     * Instantiates a new Habit service implementation.
     *
     * @param habitRepository the habit repository
     */
    @Autowired
    public HabitServiceImplementation(HabitRepository habitRepository){
        this.habitRepository = habitRepository;
    }

    @Override
    public Habit findByID(long id) {
        return habitRepository.findByID(id);
    }

    @Override
    public List<Habit> findAll() {
        return habitRepository.findAll();
    }

    @Override
    public Habit save(Habit habit) {
        return habitRepository.save(habit);
    }

    /**
     * Deletes the requested habit.
     * @param id ID of the habit to be deleted.
     */
    @Override
    public void deleteByID(long id) { habitRepository.deleteById(id); }


    /**
     * Updates the habit with the given ID.
     * @param id ID of the habit to be updated.
     */
    @Override
    public void updateHabitByID(long id) {
        Habit habit = findByID(id);
        updateHabitStreak(habit);
        updateHighestStreakByID(habit);
        completedHabitById(habit);
        save(habit);
    }

    /**
     * Update habit streak.
     *
     * @param habit the habit
     */
    public void updateHabitStreak(Habit habit){
        LocalDate lastDate = habit.getLastDate();
        LocalDate currDate = LocalDate.now();
        int currStreak = habit.getStreak();

        if (lastDate==null){ // new habit
            habit.setCreatedDate(LocalDate.now());
            habit.setLastDate(currDate);
            habit.setStreak(1);
            habit.setHighestStreak(1);
        }

        else if(lastDate!=null){ // habit exists
            boolean broken = !(lastDate.isEqual(currDate.minusDays(1))); // boolean lostStreak?
            boolean unbroken = ( lastDate.isEqual(currDate.minusDays(1))); // boolean onStreak?

            if (unbroken) { // habit streak broken
                habit.setStreak(++currStreak);
                habit.setLastDate(currDate);
            }

            else if (broken){ // habit streak unbroken
                habit.setLastDate(currDate);
                habit.setStreak(1);
            }}


    }

    /**
     * Update highest streak by id.
     *
     * @param habit the habit
     */
    public void updateHighestStreakByID(Habit habit){
        int currStreak = habit.getStreak();
        int highStreak = habit.getHighestStreak();

        if(currStreak > highStreak){
            habit.setHighestStreak(currStreak);
        }

        else {
            habit.setHighestStreak(highStreak);

        }


    }

    /**
     * Habit completed and total completions updated.
     *
     * @param habit the habit
     */
    public void completedHabitById(Habit habit){
        if(!habit.isHabitCompleted()){
            habit.setHabitCompleted(true);
            habit.setTotalCompletions(habit.getTotalCompletions()+1);
        }
        System.out.println("Habit already completed");
    }


}
