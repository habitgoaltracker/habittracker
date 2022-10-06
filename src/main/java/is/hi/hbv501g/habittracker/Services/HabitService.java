package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HabitService {
    Habit findByName(String name);
    Habit findByID(long ID);
    List<Habit> findAll();
    Habit save(Habit habit);
    void deleteByID(long ID);
}
