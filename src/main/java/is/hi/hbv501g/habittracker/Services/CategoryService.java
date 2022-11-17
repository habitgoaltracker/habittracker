package is.hi.hbv501g.habittracker.Services;


import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public interface CategoryService {
    Category findByName(String name);

    Category findByID(long ID);

    List<Category> findAll(HttpSession session);

    Category save(Category category);

    void deleteByID(long id);

    List<Goal> getGoalsByID(long id);

    List<Habit> getHabitsByID(long id);
}
