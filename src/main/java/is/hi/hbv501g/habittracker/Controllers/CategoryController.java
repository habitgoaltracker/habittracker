package is.hi.hbv501g.habittracker.Controllers;

import is.hi.hbv501g.habittracker.Persistence.Entities.*;
import is.hi.hbv501g.habittracker.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class CategoryController {
    private final HabitService habitService;
    private final GoalService goalService;
    private final TaskService taskService;
    private final UserService userService;
    private final CategoryService categoryService;

    /* Fastar */
    private static final String REDIRECT = "redirect:/";

    private static final String REDIRECT_CAT = "redirect:/category/{idCat}";

    private static final String NEW_GOAL = "newGoal";
    private static final String NEW_HABIT = "newHabit";
    private static final String NEW_TASK = "newTask";
    private static final String GOALS = "goals";
    private static final String HABITS = "habits";
    private static final String TASKS = "tasks";
    private static final String MAIN = "main";
    private static final String CATEGORIES = "categories";
    private static final String CATEGORY = "category";
    private static final String CATEGORY_ID = "idCat";



    @Autowired
    public CategoryController(HabitService habitService, GoalService goalService, TaskService taskService,
                              CategoryService categoryService, UserService userService){
        this.habitService = habitService;
        this.goalService = goalService;
        this.taskService = taskService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    /**
     * Route for requests to "/" path (the root).
     *
     * @return String with path to "main" html file.
     */
    @RequestMapping("/")
    public String updateCategoryPage(Model model, HttpSession session){
        if(session.getAttribute("LoggedInUser") == null) {
            return "redirect:/login";
        }
        List<Habit> allHabits = habitService.findAll();
        List<Goal> allGoals = goalService.findAll();
        List<Task> allTasks = taskService.findAll();
        List<Category> allCats = categoryService.findAll(session);
        model.addAttribute(HABITS, allHabits);
        model.addAttribute(GOALS, allGoals);
        model.addAttribute(TASKS, allTasks);
        model.addAttribute(CATEGORIES, allCats);
        return MAIN;
    }

    /**
     * Route for requests to "/addhabit" path.
     * Displays a form that users can fill out for habit creation.
     *
     * @return String with path to "newHabit" html file.
     */
    @RequestMapping(value="/addhabit/{id}", method = RequestMethod.GET)
    public String addHabitForm(@PathVariable("id") long id, Habit habit){
        //habitService.findByID(id);
       // Habit habit = habitService.findByID(id);

        //habitService.createHabitById(id);
        return NEW_HABIT;
    }

    /**
     * Route for requests to "/addgoal" path.
     * Displays a form that users can fill out for goal creation.
     *
     * @return String with path to "newGoal" html file.
     */
    @RequestMapping(value="/addgoal/{id}", method = RequestMethod.GET)
    public String addGoalForm(@PathVariable("id") long id, Goal goal){
        return NEW_GOAL;
    }

    /**
     * Route for requests to "/addtask" path.
     * Displays a form that users can fill out for habit creation.
     *
     * @return String with path to "newTask" html file.
     */
    @RequestMapping(value="category/{idCat}/addtask/{id}", method = RequestMethod.GET)
    public String addTaskForm(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Task task){
        return NEW_TASK;
    }

    /**
     * Route for requests to "/addhabit" path.
     * Gathers the results from a filled out "newHabit.html" form and creates a new Habit object with them.
     * Habit object is then saved and stored in database.
     *
     * @param idCat id of the category of the habit.
     * @return String with path to route to current category.
     */
    @RequestMapping(value="/addhabit/{idCat}", method = RequestMethod.POST)
    public String addHabit(@PathVariable("idCat") long idCat, Habit habit,  BindingResult result, Model model){
        if (result.hasErrors()){
            return NEW_HABIT;
        }
        Category category = categoryService.findByID(idCat);
        List<Habit> habits = category.getHabits();
        habits.add(habit);
        category.setHabits(habits);
        habit.setCategory(category);
        habitService.save(habit);
        categoryService.save(category);
        return REDIRECT_CAT;
    }

    /**
     * Route for requests to "/addgoal" path.
     * Gathers the results from a filled out "newGoal.html" form and creates a new Goal object with them.
     * Goal object is then saved and stored in database.
     *
     * @param idCat id of the category of the goal.
     * @return String with path to route to current category.
     */
    @RequestMapping(value="/addgoal/{idCat}", method = RequestMethod.POST)
    public String addGoal(@PathVariable("idCat") long idCat, Goal goal, BindingResult result, Model model){
        if (result.hasErrors()){
            return NEW_GOAL;
        }
        if(goal.getGoalDueDate()==null){
            return NEW_GOAL;
        }
        if(goal.getGoalDueDate().isBefore(LocalDate.now())){
            return NEW_GOAL;
        }
        Category category = categoryService.findByID(idCat);
        List<Goal> goals = category.getGoals();
        goals.add(goal);
        category.setGoals(goals);
        goal.setCategory(category);
        goalService.save(goal);
        categoryService.save(category);
        return REDIRECT_CAT;
    }

    /**
     * Route for requests to "/addtask/{id}" path.
     * Gathers the results from a filled out "newTask.html" form and creates a new Task object with them.
     * Task object is then referenced by the goal it was added to.
     *
     * @param idCat id of the category of the task.
     * @param id id of the goal to add a task to.
     * @return String with path to route of current category.
     */
    @RequestMapping(value="/category/{idCat}/addtask/{id}", method = RequestMethod.POST)
    public String addTask(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Task task, BindingResult result, Model model){
        if (result.hasErrors()) {
            return NEW_TASK;
        }
        Goal goal = goalService.findByID(id);
        List<Task> taskList = goal.getTasks();
        taskList.add(task);
        goal.setTasks(taskList);
        task.setTaskCompleted(false);
        task.setTaskGoal(goal);
        taskService.save(task);
        goalService.save(goal);
        long ID = task.getID();
        taskService.newTaskUpdateByID(ID);
        return REDIRECT_CAT;
    }

    /**
     * Route for requests to "/category/{idCat}/deleteHabit/{id}" path.
     * Deletes a habit.
     *
     * @param idCat id of the category of the habit.
     * @param id id of the habit to delete.
     * @return String with path to route of current category.
     */
    @RequestMapping(value="/category/{idCat}/deleteHabit/{id}", method = RequestMethod.GET)
    public String deleteHabit(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        Habit habit = habitService.findByID(id);
        Category cat = categoryService.findByID(idCat);
        List<Habit> habits = cat.getHabits();
        habits.remove(habit);
        cat.setHabits(habits);
        categoryService.save(cat);
        habitService.deleteByID(id);
        return REDIRECT_CAT;
    }

    /**
     * Route for requests to "/deleteGoal/{id}" path.
     * Deletes a goal.
     *
     * @param idCat id of the category of the goal.
     * @param id id of the goal to delete.
     * @return String with path to route of current category.
     */
    @RequestMapping(value="/category/{idCat}/deleteGoal/{id}", method = RequestMethod.GET)
    public String deleteGoal(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        goalService.deleteByID(id, idCat);
        return REDIRECT_CAT;
    }

    /**
     * Route for requests to "/deleteTask/{id}" path.
     * Deletes a task.
     *
     * @param idCat id of the category of the task.
     * @param id id of the task to delete.
     * @return String with path to route of current category.
     */
    @RequestMapping(value="/category/{idCat}/deleteTask/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        taskService.deleteByID(id);
        return REDIRECT_CAT;
    }


    /**
     * Route for requests to "/updateTask/{id}" path.
     * Updates data of task after it's checked as completed.
     *
     * @param idCat id of the category of the task.
     * @param id id of the task to update.
     * @return String with path to route /.
     */
    @RequestMapping(value="/category/{idCat}/updateTask/{id}", method = RequestMethod.GET)
    public String updateTask(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        taskService.updateTaskByID(id);
        return REDIRECT_CAT;
    }

    /**
     * Route for requests to "/update/{id}" path.
     * Updates data of habit after it has been checked as completed for the day.
     *
     * @param idCat id of the category of the habit.
     * @param id id of the habit to update.
     * @return String with path to route /.
     */
    @RequestMapping(value="category/{idCat}/updateHabit/{id}", method = RequestMethod.GET)
    public String updateStreakHabit(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model, HttpSession session){

        Habit habit = habitService.findByID(id);
        Category cat = categoryService.findByID(idCat);
        habit.setUser((User) session.getAttribute("LoggedInUser"));

        //habitService.createHabitById(id);
        habitService.updateHabitByID(id);
        categoryService.save(cat);
        return "redirect:/category/{idCat}";
    }

    /**
     * Route for requests to "/updateGoal/{id}" path.
     * Updates data of a goal after it has been checked as completed.
     *
     * @param idCat id of the category of the goal.
     * @param id id of the habit to update.
     * @return String with path to route /.
     */
    @RequestMapping(value="category/{idCat}/updateGoal/{id}", method = RequestMethod.GET)
    public String updateGoal(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        goalService.deleteByID(id, idCat);
        Category cat = categoryService.findByID(idCat);
        categoryService.save(cat);
        return REDIRECT_CAT;
    }
}
