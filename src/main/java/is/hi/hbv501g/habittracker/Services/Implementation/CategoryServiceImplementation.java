package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Repositories.CategoryRepository;
import is.hi.hbv501g.habittracker.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).get(0);
    }

    @Override
    public Category findByID(long ID) {
        return categoryRepository.findByID(ID);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteByID(long id) {
        Category category = findByID(id);
        this.categoryRepository.delete(category);
    }

    @Override
    public List<Goal> getGoalsByID(long id) {
        Category category = categoryRepository.findByID(id);
        return category.getGoals();
    }

    @Override
    public List<Habit> getHabitsByID(long id) {
        Category category = categoryRepository.findByID(id);
        return category.getHabits();
    }
}
