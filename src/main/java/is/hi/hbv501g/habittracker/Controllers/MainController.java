package is.hi.hbv501g.habittracker.Controllers;


import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Entities.User;
import is.hi.hbv501g.habittracker.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    private final CategoryService categoryService;

    @Autowired
    public MainController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /* Fastar */
    private static final String REDIRECT = "redirect:/";
    private static final String GOALS = "goals";
    private static final String HABITS = "habits";
    private static final String CATEGORY = "category";
    private static final String CATEGORY_ID = "idCat";

    @RequestMapping(value="/addcategory", method = RequestMethod.GET)
    public String addCategoryForm(Category category){ // SonarLint: Replace this persistent entity with a simple POJO or DTO object.
        return "newCategory";
    }

    /**
     * Route for requests to "/addcategory" path.
     * Gathers the results from a filled out "newCategory.html" form and creates a new Category object with them.
     * Category object is then saved and stored in database.
     * creates a new directory on main.
     *
     * @param category category to be added.
     * @return String with path to route /.
     */
    @RequestMapping(value="/addcategory", method = RequestMethod.POST)
    public String addCategory(Category category, BindingResult result, Model model, HttpSession session){
        if (result.hasErrors()){
            return "newCategory";
        }
        category.setUser((User) session.getAttribute("LoggedInUser"));
        System.out.println(category.getUser().getUsername());
        categoryService.save(category);
        return REDIRECT;
    }

    /**
     * Route for requests to "/deleteCategory/{id}" path.
     * Deletes a category.
     *
     * @param id id of the category to delete.
     * @return String with path to route /.
     */
    @RequestMapping(value="/deleteCategory/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") long id, Model model){
        categoryService.deleteByID(id);
        return REDIRECT;
    }

    /**
     * Route for requests to "/category/{id}" path.
     * Opens a category page.
     *
     * @param id id of the category.
     * @return route to the category.
     */
    @RequestMapping(value="/category/{id}", method = RequestMethod.GET)
    public String openCategory(@PathVariable("id") long id, Model model, HttpSession session){
        categoryService.findByID(id);
        List<Habit> habits = categoryService.getHabitsByID(id);
        List<Goal> goals = categoryService.getGoalsByID(id);
        Category cat = categoryService.findByID(id);
        if(!Objects.equals(cat.getUser().getUsername(), ((User) session.getAttribute("LoggedInUser")).getUsername())) {
            return "unauthorizedAccess";
        }
        model.addAttribute(HABITS, habits);
        model.addAttribute(GOALS, goals);
        model.addAttribute(CATEGORY, cat);
        model.addAttribute(CATEGORY_ID, id);
        return CATEGORY;
    }
}
