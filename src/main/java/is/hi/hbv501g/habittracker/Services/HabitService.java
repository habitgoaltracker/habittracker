package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;

import java.util.List;

/**************************************************************

 nafn: Árni Björn Óskarsson
 t-póstur: abo25@gmail.com

 lýsing:
 **************************************************************/
public interface HabitService {
    Habit findByName(String Name);
    Habit findByID(String ID);
    List<Habit> findAll();
    Habit save(Habit habit);
    void delete(Habit habit);
}
