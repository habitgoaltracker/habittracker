package is.hi.hbv501g.habittracker.Services;


import is.hi.hbv501g.habittracker.Persistence.Entities.Category;

import java.util.List;

public interface CategoryService {
    Category findByName(String name);

    Category findByID(long ID);

    List<Category> findAll();

    Category save(Category category);

    void deleteByID(long id);
}
