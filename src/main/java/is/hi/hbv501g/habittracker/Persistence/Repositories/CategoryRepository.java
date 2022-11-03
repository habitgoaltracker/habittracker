package is.hi.hbv501g.habittracker.Persistence.Repositories;

import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;

import java.util.List;

public interface CategoryRepository {
    Category save(Category category);
    void delete(Category category);
    List<Category> findAll();
    List<Category> findByName(String name);
    Category findByID(long id);
}
