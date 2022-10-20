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

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {


    private final HabitService habitService;
    private final GoalService goalService;
    private final TaskService taskService;

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
        model.addAttribute("habits", allHabits);
        model.addAttribute("goals", allGoals);
        return "main";
    }

    /**
     * Route for requests to "/addhabit" path.
     * Displays a form that users can fill out for habit creation.
     *
     * @return String with path to "newHabit" html file.
     */
    @RequestMapping(value="/addhabit", method = RequestMethod.GET)
    public String addHabitForm(Habit habit){
        return "newHabit";
    }

    @RequestMapping(value="/addgoal", method = RequestMethod.GET)
    public String addGoalForm(Goal goal){
        return "newGoal";
    }

    @RequestMapping(value="/addtask", method = RequestMethod.GET)
    public String addTaskForm(Task task){
        return "newTask";
    }

    /**
     * Route for requests to "/addhabit" path.
     * Gathers the results from a filled out "newHabit.html" form and creates a new Habit object with them.
     * Habit object is then saved and stored in database.
     *
     * @return String with path to route /.
     */
    @RequestMapping(value="/addhabit", method = RequestMethod.POST)
    public String addHabit(Habit habit, BindingResult result, Model model){
        if (result.hasErrors()){
            return "newHabit";
        }
        habitService.save(habit);
        return "redirect:/";
    }

    @RequestMapping(value="/addgoal", method = RequestMethod.POST)
    public String addGoal(Goal goal, BindingResult result, Model model){
        if (result.hasErrors()){
            return "newGoal";
        }
        if(goal.getGoalDueDate().isBefore(LocalDate.now())){
            return "newGoal";
        }
        goalService.save(goal);
        return "redirect:/";
    }

    @RequestMapping(value="/addtask", method = RequestMethod.POST)
    public String addTask(Task task, BindingResult result, Model model){
        if (result.hasErrors()){
            return "newTask";
        }
        taskService.save(task);
        return "redirect:/";
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
        return "redirect:/";
    }

    @RequestMapping(value="/deleteGoal/{id}", method = RequestMethod.GET)
    public String deleteGoal(@PathVariable("id") long id, Model model){
        goalService.deleteByID(id);
        return "redirect:/";
    }

    @RequestMapping(value="/deleteTask/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") long id, Model model){
        taskService.deleteByID(id);
        return "redirect:/";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String updateStreakHabit(@PathVariable("id") long id, Model model){
        habitService.updateHabitByID(id);
        return "redirect:/";
    }

    public String updateGoal(@PathVariable("id") long id, Model model){
        goalService.updateGoalByID(id);
        return "redirect:/";
    }
}
