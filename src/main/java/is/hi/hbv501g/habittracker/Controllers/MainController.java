package is.hi.hbv501g.habittracker.Controllers;

import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
import is.hi.hbv501g.habittracker.Services.CategoryService;
import is.hi.hbv501g.habittracker.Services.GoalService;
import is.hi.hbv501g.habittracker.Services.HabitService;
import is.hi.hbv501g.habittracker.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MainController {
    /* Tilviksbreytur */
    private final HabitService habitService;
    private final GoalService goalService;
    private final TaskService taskService;
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
    private static final String NEW_CATEGORY = "newCategory";


    @Autowired
    public MainController(HabitService habitService, GoalService goalService, TaskService taskService,
                          CategoryService categoryService){
        this.habitService = habitService;
        this.goalService = goalService;
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    /**
     * Route for requests to "/" path (the root).
     *
     * @return String with path to "main" html file.
     */
    @RequestMapping("/")
    public String mainPage(Model model){
        List<Habit> allHabits = habitService.findAll();
        List<Goal> allGoals = goalService.findAll();
        List<Task> allTasks = taskService.findAll();
        List<Category> allCats = categoryService.findAll();
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
    public String addHabitForm(@PathVariable("id") long id, Habit habit){ // SonarLint: Replace this persistent entity with a simple POJO or DTO object.
        return NEW_HABIT;
    }

    @RequestMapping(value="/addgoal/{id}", method = RequestMethod.GET)
    public String addGoalForm(@PathVariable("id") long id, Goal goal){
        return NEW_GOAL;
    }

    @RequestMapping(value="category/{idCat}/addtask/{id}", method = RequestMethod.GET)
    public String addTaskForm(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Task task){
        return NEW_TASK;
    }

    @RequestMapping(value="/addcategory", method = RequestMethod.GET)
    public String addCategoryForm(Category category){
        System.out.println("[a_1]  /addCategory: " + category); // veb3
        System.out.println("[a_2]  /addCategory: (getName)" + category.getName()); // veb3
        System.out.println("[a_3]  /addCategory: (getHabits)" + category.getHabits()); // veb3
        System.out.println("[a_4]  /addCategory: (getGoals)" + category.getGoals()); // veb3
        System.out.println("[a_5]  /addCategory: (getID)" + category.getID()); // veb3


        return NEW_CATEGORY;
    }

    /**
     * Route for requests to "/addhabit" path.
     * Gathers the results from a filled out "newHabit.html" form and creates a new Habit object with them.
     * Habit object is then saved and stored in database.
     *
     * @return String with path to route /.
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
     * @return String with path to route /.
     */
    @RequestMapping(value="/addgoal/{idCat}", method = RequestMethod.POST)
    public String addGoal(@PathVariable("idCat") long idCat, Goal goal, BindingResult result, Model model){
        if (result.hasErrors()){
            System.out.println("aahhhh");
            return NEW_GOAL;
        }
        if(goal.getGoalDueDate().isBefore(LocalDate.now())){
            System.out.println("ooooohhh");
            return NEW_GOAL;
        }
        Category category = categoryService.findByID(idCat);
        System.out.println("[c_1]  cat: " + category); // veb3
        System.out.println("[c_2]  cat(getName)" + category.getName()); // veb3
        System.out.println("[c_3]  cat(getHabits)" + category.getHabits()); // veb3
        System.out.println("[c_4]  cat(getGoals)" + category.getGoals()); // veb3
        System.out.println("[c_5]  cat(getID)" + category.getID()); // veb3

        List<Goal> goals = category.getGoals();

        System.out.println("[d_1]  goal: " + goal); // veb3
        System.out.println("[d_2]  goals:" + goals); // veb3
        System.out.println("[d_3]  goal: (getName)" + goal.getName()); // veb3
        System.out.println("[d_4]  goal: (getGoalDueDate)" + goal.getGoalDueDate()); // veb3
        System.out.println("[d_5]  goal: (getGoalProgress())" + goal.getGoalProgress()); // veb3
        System.out.println("[d_6]  goal: (getTasks())" + goal.getTasks()); // veb3
        System.out.println("[d_7]  goal: (getCategory())" + goal.getCategory()); // veb3
        System.out.println("[d_8]  goal: (geID)" + goal.getID()); // veb3

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
     * @param id id of the goal to add a task to
     * @return String with path to route /.
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

    @RequestMapping(value="/addcategory", method = RequestMethod.POST)
    public String addCategory(Category category, BindingResult result, Model model){
        System.out.println("[b_1]  /addCategory: " + category); // veb3
        System.out.println("[b_2]  /addCategory: (getName)" + category.getName()); // veb3
        System.out.println("[b_3]  /addCategory: (getHabits)" + category.getHabits()); // veb3
        System.out.println("[b_4]  /addCategory: (getGoals)" + category.getGoals()); // veb3
        System.out.println("[b_5]  /addCategory: (getID)" + category.getID()); // veb3
        // System.err.println("[b_6] category: " + category); // veb Afþví að þetta er .err þá prentast þetta seinast út...úúúú
        System.out.printf("[b_7] category: (getName) %s", category.getName());
        System.out.println("[b_8] MainController.addCategory");
        System.out.println("[b_9] category = " + category + ", result = " + result + ", model = " + model);
        if (result.hasErrors()){
            return NEW_CATEGORY;
        }
        categoryService.save(category);
        return REDIRECT;
    }

    /**
     * Route for requests to "/delete/{id}" path.
     * Deletes a habit.
     *
     * @param id id of the habit to delete.
     * @return String with path to route /.
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
     * @param id id of the goal to delete.
     * @return String with path to route /.
     */
    @RequestMapping(value="/category/{idCat}/deleteGoal/{id}", method = RequestMethod.GET)
    public String deleteGoal(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        Goal goal = goalService.findByID(id);
        Category cat = categoryService.findByID(idCat);
        List<Goal> goals = cat.getGoals();
        goals.remove(goal);
        cat.setGoals(goals);
        categoryService.save(cat);
        goalService.deleteByID(id);
        return REDIRECT_CAT;
    }

    /**
     * Route for requests to "/deleteTask/{id}" path.
     * Deletes a task.
     *
     * @param id id of the task to delete.
     * @return String with path to route /.
     */
    @RequestMapping(value="category/{idCat}/deleteTask/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        taskService.deleteByID(id);
        return REDIRECT_CAT;
    }

    @RequestMapping(value="/deleteCategory/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") long id, Model model){
        categoryService.deleteByID(id);
        return REDIRECT;
    }

    /**
     * Route for requests to "/updateTask/{id}" path.
     * Updates data of task after it's checked as completed.
     *
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
     * @param id id of the habit to update.
     * @return String with path to route /.
     */
    @RequestMapping(value="category/{idCat}/updateHabit/{id}", method = RequestMethod.GET)
    public String updateStreakHabit(@PathVariable("idCat") long idCat, @PathVariable("id") long id, Model model){
        Habit habit = habitService.findByID(id);
        Category cat = categoryService.findByID(idCat);
        habitService.updateHabitByID(id);
        categoryService.save(cat);
        return "redirect:/category/{idCat}";
    }

    //TO BE DELETED/UPDATED
    @RequestMapping(value="/checkbox-altered/{id}", method = RequestMethod.GET)
    public String checkboxAltered(@RequestParam("isChecked") boolean checkbox, @PathVariable("id") long id){
        Goal goal = goalService.findByID(id);
        goal.setGoalCompleted(checkbox);
        goalService.save(goal);
        return REDIRECT;
    }

    /**
     * Route for requests to "/updateGoal/{id}" path.
     * Updates data of a goal after it has been checked as completed.
     *
     * @param id id of the habit to update.
     * @return String with path to route /.
     */
    public String updateGoal(@PathVariable("id") long id, Model model){
        goalService.updateGoalByID(id);
        return REDIRECT;
    }

    @RequestMapping(value="/category/{id}", method = RequestMethod.GET)
    public String openCategory(@PathVariable("id") long id, Model model){
        categoryService.findByID(id);
        List<Habit> habits = categoryService.getHabitsByID(id);
        List<Goal> goals = categoryService.getGoalsByID(id);
        Category cat = categoryService.findByID(id);
        model.addAttribute(HABITS, habits);
        model.addAttribute(GOALS, goals);
        model.addAttribute("category", cat);
        model.addAttribute("idCat", id);
        return "category";
    }

    // veb3 todo stats
    // veb3 todo html lúkkið


}
