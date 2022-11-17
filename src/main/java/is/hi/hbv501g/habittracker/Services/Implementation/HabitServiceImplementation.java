package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Repositories.HabitRepository;
import is.hi.hbv501g.habittracker.Services.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import javax.persistence.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.YEARS;

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
    public Habit findByName(String name) {
        return habitRepository.findByName(name).get(0);
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

    @Override
    public void deleteByID(long id) { habitRepository.deleteById(id); }


    @Override
    public void updateHabitByID(long id) {
        Habit habit = findByID(id);
        System.out.println("habitName = " + habit.getName() + " habitID = " + habit.getID() + " id = "+ id);
        if (!habit.isHabitCompleted()) {
            habit.setHabitCompleted(true);
            updateHabitStreak(habit);
            updateHighestStreakByID(habit);
            updateHabitTotalCompletions(habit);
            save(habit);
        } else {
            System.out.println(habit.toString());
            System.out.println("Habit already completed.....loser");
        }

    }

    @Override
    public void createHabit(Habit habit) {
        habit.setLastDate(LocalDate.ofEpochDay(0));
        System.out.println(habit.toString());
        save(habit);
    }

    /**
     * Update habit streak.
     *
     * @param habit the habit
     */
    public void updateHabitStreak(Habit habit){
        // Catch error if localdate is not today? add habit vs  done habit
        LocalDate lastDate = habit.getLastDate();
        LocalDate currDate = LocalDate.now();
        habit.setTotalHabitChances(ChronoUnit.DAYS.between(habit.getCreatedDate(), LocalDate.now()));

        int currStreak = habit.getStreak();
        

        if (lastDate==null){ // new habit
            System.out.println("🪅Null habit or new habit🪅");
            habit.setLastDate(currDate);
            habit.setStreak(1);
            habit.setHighestStreak(1);
        }

        else if(lastDate!=null){ // habit streak date stuff
            boolean broken = !(lastDate.isEqual(currDate.minusDays(1))); // boolean lostStreak?
            boolean unbroken = ( lastDate.isEqual(currDate.minusDays(1))); // boolean onStreak?
            System.out.println("unbroken = " + unbroken);
            System.out.println("broken = " + broken);

            if (unbroken) { // habit streak brooke
                System.out.println("🐋Unbroken habit🐋");
                habit.setStreak(++currStreak);
                habit.setLastDate(currDate);
            }

            else if (broken){ // habit streak unbroken
                System.out.println("❤️‍🩹Broken habit❤️‍");
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
            System.out.println("New high streak");
            habit.setHighestStreak(currStreak);
        }
        else {
            System.out.println("No new high streak");
            habit.setHighestStreak(highStreak);
        }


    }

    /**
     * Update habit total completions.
     *
     * @param habit the habit
     */
    public void updateHabitTotalCompletions(Habit habit) {
        int totalComp = habit.getTotalCompletions();
        habit.setTotalCompletions(++totalComp);
        System.out.println("totalComp = " + totalComp);
    }

    /**
     * Completed habit by id.
     *
     * @param id the id
     */
    public void completedHabitById(long id){
        Habit habit = findByID(id);
        habit.setHabitCompleted(true);
        habit.setLastDate(LocalDate.now());
        habit.setStreak(1);
        habit.setTotalCompletions(habit.getTotalCompletions()+1);
        save(habit);

    }

    /**
     * Total completed habit tally.
     *
     * @param completed the completed
     */
    public void totalCompletedHabitTally(boolean completed){

    }
}
