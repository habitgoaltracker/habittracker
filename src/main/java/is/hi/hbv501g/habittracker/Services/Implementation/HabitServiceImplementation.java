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
    public Habit findByID(long ID) {
        return habitRepository.findByID(ID);
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
        int streak = habit.getStreak();
        int currStreak = streak + 1;
        int highStreak = habit.getHighestStreak();
        LocalDate currDate = LocalDate.now();

        if(habit.getLastDate() != currDate.minusDays(1)) {
            habit.setLastDate(currDate);
            habit.setStreak(1);
        }

        else if (habit.getLastDate().equals(currDate.minusDays(1))) {
            habit.setStreak(currStreak);
            habit.setLastDate(currDate);
        }

        if(habit.getStreak() > highStreak){
            habit.setHighestStreak(currStreak);
        }

        save(habit);
    }
}
