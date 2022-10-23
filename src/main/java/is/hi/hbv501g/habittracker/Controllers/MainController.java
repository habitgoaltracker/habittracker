package is.hi.hbv501g.habittracker.Controllers;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Persistence.Entities.Task;
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

@Controller
public class MainController {
    private final HabitService habitService;
    private final GoalService goalService;
    private final TaskService taskService;

    /* Fastar */
    private static final String REDIRECT = "redirect:/";
    private static final String NEW_GOAL = "newGoal";
    private static final String NEW_HABIT = "newHabit";
    private static final String NEW_TASK = "newTask";
    private static final String GOALS = "goals";
    private static final String HABITS = "habits";
    private static final String TASKS = "tasks";
    private static final String MAIN = "main";


    @Autowired
    public MainController(HabitService habitService, GoalService goalService, TaskService taskService){
        this.habitService = habitService;
        this.goalService = goalService;
        this.taskService = taskService;
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
        model.addAttribute(HABITS, allHabits);
        model.addAttribute(GOALS, allGoals);
        model.addAttribute("tasks", allTasks);
        return MAIN;
    }

    /**
     * Route for requests to "/addhabit" path.
     * Displays a form that users can fill out for habit creation.
     *
     * @return String with path to "newHabit" html file.
     */
    @RequestMapping(value="/addhabit", method = RequestMethod.GET)
    public String addHabitForm(Habit habit){ // SonarLint: Replace this persistent entity with a simple POJO or DTO object.
        return NEW_HABIT;
    }

    @RequestMapping(value="/addgoal", method = RequestMethod.GET)
    public String addGoalForm(Goal goal){
        return NEW_GOAL;
    }

    @RequestMapping(value="/addtask/{id}", method = RequestMethod.GET)
    public String addTaskForm(@PathVariable("id") long id, Task task){
        return NEW_TASK;
    }

    /**
     * Route for requests to "/addhabit" path.
     * Gathers the results from a filled out "newHabit.html" form and creates a new Habit object with them.
     * Habit object is then saved and stored in database.
     *
     * @return String with path to route /.
     */
    @RequestMapping(value="/addhabit", method = RequestMethod.POST)
    public String addHabit(Habit habit,  BindingResult result, Model model){
        if (result.hasErrors()){
            return NEW_HABIT;
        }
        habitService.save(habit);
        return REDIRECT;
    }

    @RequestMapping(value="/addgoal", method = RequestMethod.POST)
    public String addGoal(Goal goal, BindingResult result, Model model){
        if (result.hasErrors()){
            return NEW_GOAL;
        }
        if(goal.getGoalDueDate().isBefore(LocalDate.now())){
            return NEW_GOAL;
        }
        goalService.save(goal);
        return REDIRECT;
    }

    @RequestMapping(value="/addtask/{id}", method = RequestMethod.POST)
    public String addTask(@PathVariable("id") long id, Task task, BindingResult result, Model model){
        if (result.hasErrors()) {
            return NEW_TASK;
        }
        Goal goal = goalService.findByID(id);
        List<Task> taskList = goal.getTasks();
        taskList.add(task);
        goal.setTasks(taskList);
        taskService.save(task);
        goalService.save(goal);
        return REDIRECT;
    }

    /**
     * Route for requests to "/delete/{id}" path.
     * Deletes a habit.
     *
     * @param id id of the habit to delete.
     * @return String with path to route /.
     */
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteHabit(@PathVariable("id") long id, Model model){
        habitService.deleteByID(id);
        return REDIRECT;
    }

    @RequestMapping(value="/deleteGoal/{id}", method = RequestMethod.GET)
    public String deleteGoal(@PathVariable("id") long id, Model model){
        goalService.deleteByID(id);
        return REDIRECT;
    }

    @RequestMapping(value="/deleteTask/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") long id, Model model){
        taskService.deleteByID(id);
        return REDIRECT;
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String updateStreakHabit(@PathVariable("id") long id, Model model){
        habitService.updateHabitByID(id);
        return REDIRECT;
    }

    @RequestMapping(value="/checkbox-altered/{id}", method = RequestMethod.GET)
    public String checkboxAltered(@RequestParam("isChecked") boolean checkbox, @PathVariable("id") long id){
        Goal goal = goalService.findByID(id);
        System.out.println(goal.isGoalCompleted());
        goal.setGoalCompleted(checkbox);
        goalService.save(goal);
        if(checkbox)System.out.println("checkbox checked");
        return REDIRECT;
    }

    public String updateGoal(@PathVariable("id") long id, Model model){
        goalService.updateGoalByID(id);
        return REDIRECT;
    }
}
