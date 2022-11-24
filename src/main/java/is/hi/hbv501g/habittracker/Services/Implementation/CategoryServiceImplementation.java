package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Entities.User;
import is.hi.hbv501g.habittracker.Persistence.Repositories.CategoryRepository;
import is.hi.hbv501g.habittracker.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByID(long ID) {
        return categoryRepository.findByID(ID);
    }

    @Override
    public List<Category> findAll(HttpSession session) {
        List<Category> allCategories = categoryRepository.findAll();
        List<Category> filteredCategory = new ArrayList<>();
        if(session.getAttribute("LoggedInUser") != null){
            for(Category category : allCategories){
                if(category.getUser() != null){
                    if(Objects.equals(category.getUser().getUsername(), ((User) session.getAttribute("LoggedInUser")).getUsername())) {
                        filteredCategory.add(category);
                    }
                }
            }
            return filteredCategory;
        }
        return allCategories;
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
