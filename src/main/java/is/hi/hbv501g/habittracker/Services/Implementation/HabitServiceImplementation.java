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
    // bara ef JPA ekki til staðar, til að gera hlutina manually
    // ? private List<Book> bookRepository = new ArrayList<>();
    // ? private int id_counter = 0;

    @Autowired
    public HabitServiceImplementation(HabitRepository habitRepository){
        this.habitRepository = habitRepository;
        // Create 3 random habits for testing purposes, to make debugging easier.
        // To be removed in final version.
        //Habit demo1 = habitRepository.save(new Habit("_Lesa bók", "vitkun", 0, 14, LocalDate.now().minusDays(3))); // streak uppfært = fyrra fyrradag
        //habitRepository.save(new Habit("_Æfa", "hreyfing", 0, 14, LocalDate.now().minusDays(2))); // streak uppfært = fyrradag
        //habitRepository.save(new Habit("_Læra japönsku", "tungumál", 9, 14, LocalDate.now().minusDays(1))); // streak uppfært = gær
        //habitRepository.save(new Habit("_Drekka vatn", "heilsa", 7, 14, LocalDate.now().minusDays(0))); // streak uppfært = í dag
        //habitRepository.save(new Habit("_Forrita", "gáfnalykill", 9, 14, LocalDate.now().minusDays(0))); // streak uppfært = í dag
        //habitRepository.save(new Habit("_Leika við köttinn minn", "tilfinningahlýjun", 1, 14, LocalDate.now().minusDays(0))); // streak uppfært = í dag en með resettað streak
        //habitRepository.save(new Habit("_Elda", "mataræði", 0, 0, null)).setID(100009); // streak = nýtt

        //demo1.setID(10007);
        // JPA gives each book an ID, but here we add them Manually
        // JPA gives each book an ID, but here we update them manually so we can use them over and over for testing purposes.
        /*
        // Habit býður ekki uppá foreach þannig ætla að skrappa þessu í bili og henda bara út hverju habit fyrir sig.
        for(Habit habit : habitRepository){
        }

        int demo_id = 1000000;
        for (int i = 0; i < 7; i++) {

        }
  */
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
        // TODO BUG Habit streak resettar sig ekki niður í einn þegar streak brotnar
        // TODO BUG Last date uppfærist heldur ekki í currDate þegar streak er brotið
        // HUGMYND að bíða með að láta hafa dag á milli streaks...kannski 10sek eða eitthvað svo maður verði var við bugs fyrr.
        Habit habit = findByID(id);
        int streak = habit.getStreak();
        int currStreak = streak;
        int highStreak = habit.getHighestStreak();
        LocalDate currDate = LocalDate.now();

        if (habit.getLastDate()==null){
            habit.setLastDate(currDate);
            habit.setStreak(1);
            currStreak = 1;
        }

        else if (habit.getLastDate().equals(currDate.minusDays(1))) {
            habit.setStreak(++currStreak);
            habit.setLastDate(currDate);
        }

        if(habit.getStreak() > highStreak){
            habit.setHighestStreak(currStreak);
        }

        save(habit);
    }
}
