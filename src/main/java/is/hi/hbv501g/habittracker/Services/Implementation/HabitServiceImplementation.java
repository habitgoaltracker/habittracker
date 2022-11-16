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

    @Override
    public void updateHabitByID(long id) {
        // TODO BUG Hægt að bæta við habit án nafns...
        // TODO Skera niður í fleiri föll
        Habit habit = findByID(id);
        //int currStreak = habit.getStreak();
        //int highStreak = habit.getHighestStreak();
        //LocalDate currDate = LocalDate.now();
        //LocalDate lastDate = habit.getLastDate();


        System.out.println("habitName = " + habit.getName());
        System.out.println("id = " + id);

        /*
        if (lastDate==null){
            System.out.println("🪅Null habit or new habit🪅");
            habit.setLastDate(currDate);
            habit.setStreak(1);
            habit.setHighestStreak(1);
        }

        else if(lastDate!=null){
            boolean broken = !(lastDate.isEqual(currDate.minusDays(1))); // boolean lostStreak?
            boolean unbroken = ( lastDate.isEqual(currDate.minusDays(1))); // boolean onStreak?
            System.out.println("unbroken = " + unbroken);
            System.out.println("broken = " + broken);

        if (unbroken) {
            System.out.println("🐋Unbroken habit🐋");
            habit.setStreak(++currStreak);
            habit.setLastDate(currDate);
        }

        else if (broken){
            System.out.println("❤️‍🩹Broken habit❤️‍");
            habit.setLastDate(currDate);
            habit.setStreak(1);
        }}

         */

        /*
        if(currStreak > highStreak){
            System.out.println("New high streak");
            habit.setHighestStreak(currStreak);
        }

        else {
            System.out.println("No new high streak");
            habit.setHighestStreak(0);
        }

         */
        updateHabitStreak(habit);
        updateHighestStreakByID(habit);
        save(habit);
        int currStreak = habit.getStreak();
        int highStreak = habit.getHighestStreak();
        System.out.println("!!!!!!!!currStreak = " + currStreak);
System.out.println("!!!!!!!!!highStreak = " + highStreak);

    }

    public void updateHabitStreak(Habit habit){
        // local lastdate vs object lastdate
        LocalDate lastDate = habit.getLastDate();
        LocalDate currDate = LocalDate.now();
        int currStreak = habit.getStreak();

        if (lastDate==null){
            System.out.println("🪅Null habit or new habit🪅");
            habit.setLastDate(currDate);
            habit.setStreak(1);
            habit.setHighestStreak(1);
        }

        else if(lastDate!=null){
            boolean broken = !(lastDate.isEqual(currDate.minusDays(1))); // boolean lostStreak?
            boolean unbroken = ( lastDate.isEqual(currDate.minusDays(1))); // boolean onStreak?
            System.out.println("unbroken = " + unbroken);
            System.out.println("broken = " + broken);

            if (unbroken) {
                System.out.println("🐋Unbroken habit🐋");
                habit.setStreak(++currStreak);
                habit.setLastDate(currDate);
            }

            else if (broken){
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
        System.out.println("highStreak: " + highStreak);
        System.out.println("currStreak: " + currStreak);

        if(currStreak > highStreak){
            System.out.println("New high streak");
            habit.setHighestStreak(currStreak);
            System.out.println("currStreak => " + currStreak);
            System.out.println("highStreak => " + highStreak);
        }

        else {
            System.out.println("No new high streak");
            habit.setHighestStreak(highStreak);
            System.out.println("currStreak =>> " + currStreak);
            System.out.println("highStreak =>> " + highStreak);

        }
        System.out.println("highStreak = :" + highStreak);
        System.out.println("currStreak = :" + currStreak);


    }
}
