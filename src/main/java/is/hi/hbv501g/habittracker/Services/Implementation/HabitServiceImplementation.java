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

/*
    @Override
    public void createHabitById(Habit habit){
        habit.setCreatedDate(LocalDate.now());
    }

 */

    @Override
    public void updateHabitByID(long id) {

        Habit habit = findByID(id);
        System.out.println("habitName = " + habit.getName() + " habitID = " + habit.getID() + " id = "+ id);
        System.out.println("id = " + id);
        updateHabitStreak(habit);
        updateHighestStreakByID(habit);
        updateHabitTotalCompletins(habit);
        save(habit);


    }

    public void updateHabitStreak(Habit habit){

        // Catch error if localdate is not today? add habit vs  done habit

        LocalDate lastDate = habit.getLastDate();
        LocalDate currDate = LocalDate.now();
        int currStreak = habit.getStreak();
        //int totalComp = habit.getTotalCompletions();
        /* problem creating stöffs
        int createdDate = habit.getCreatedDate().getDayOfYear();
        int totalChance = currDate.getDayOfYear() - createdDate;
        System.out.println("totalChance = " + totalChance);
        System.out.println(createdDate);
         ;
         */

        if (lastDate==null){ // new habit
            habit.setCreatedDate(LocalDate.now());
            System.out.println("🪅Null habit or new habit🪅");
            habit.setLastDate(currDate);
            habit.setStreak(1);
            habit.setHighestStreak(1);
            //habit.setTotalCompletions(60);
        }

        else if(lastDate!=null){ // habit streak date stuff
            boolean broken = !(lastDate.isEqual(currDate.minusDays(1))); // boolean lostStreak?
            boolean unbroken = ( lastDate.isEqual(currDate.minusDays(1))); // boolean onStreak?
            System.out.println("unbroken = " + unbroken);
            System.out.println("broken = " + broken);
            habit.setTotalCompletions(habit.getTotalCompletions()+1);

            if (unbroken) { // habit streak brooke
                System.out.println("🐋Unbroken habit🐋");
                habit.setStreak(++currStreak);
                habit.setLastDate(currDate);
                //habit.setTotalCompletions(++totalComp);
            }

            else if (broken){ // habit streak unbroken
                System.out.println("❤️‍🩹Broken habit❤️‍");
                habit.setLastDate(currDate);
                habit.setStreak(1);
            }}
        System.out.println("lastDate = " + lastDate);
        System.out.println("currDate = " + currDate);
        System.out.println("currDate.minus = " + currDate.minusDays(1));

    }

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

    public void updateHabitTotalCompletins(Habit habit) {
        int totalComp = habit.getTotalCompletions();

        //if (habit.getLastDate())
        habit.setTotalCompletions(++totalComp);
        System.out.println("totalComp = " + totalComp);


    }

    public void completedHabitById(long id){
        Habit habit = findByID(id);
        habit.setHabitCompleted(true);


        habit.setLastDate(LocalDate.now());
        habit.setStreak(1);
        habit.setTotalCompletions(habit.getTotalCompletions()+1);
        save(habit);

    }

    public void totalCompletedHabitTally(boolean completed){

    }
}
