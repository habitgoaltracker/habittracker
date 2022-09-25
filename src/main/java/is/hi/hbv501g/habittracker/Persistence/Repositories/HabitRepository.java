package is.hi.hbv501g.habittracker.Persistence.Repositories;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**************************************************************

 nafn: Árni Björn Óskarsson
 t-póstur: abo25@gmail.com

 lýsing:
 **************************************************************/
public interface HabitRepository extends JpaRepository<Habit, Long> {
    Habit save(Habit habit);
    void delete(Habit habit);
    List<Habit> findAll();
    List<Habit> findByName(String name);
    Habit findByID(long id);
}
