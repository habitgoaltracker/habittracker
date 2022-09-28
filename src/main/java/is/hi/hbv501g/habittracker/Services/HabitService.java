package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import org.springframework.stereotype.Service;

import java.util.List;

/**************************************************************

 nafn: Árni Björn Óskarsson
 t-póstur: abo25@gmail.com

 lýsing:
 **************************************************************/

@Service
public interface HabitService {
    Habit findByName(String Name);
    Habit findByID(long ID);
    List<Habit> findAll();
    Habit save(Habit habit);
    void delete(Habit habit);
}
