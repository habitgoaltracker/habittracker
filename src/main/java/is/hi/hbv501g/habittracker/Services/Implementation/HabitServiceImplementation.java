package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Repositories.HabitRepository;
import is.hi.hbv501g.habittracker.Services.HabitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**************************************************************

 nafn: Árni Björn Óskarsson
 t-póstur: abo25@gmail.com

 lýsing:
 **************************************************************/
public class HabitServiceImplementation implements HabitService {
    private HabitRepository habitRepository;
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
    public void delete(Habit habit) {
        habitRepository.delete(habit);
    }
}
