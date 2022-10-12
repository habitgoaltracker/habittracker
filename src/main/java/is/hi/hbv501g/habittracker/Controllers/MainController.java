package is.hi.hbv501g.habittracker.Controllers;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Services.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {


    private final HabitService habitService;

    @Autowired
    public MainController(HabitService habitService){
        this.habitService = habitService;
    }

    /**
     * Route for requests to "/" path (the root).
     *
     * @return String with path to "main" html file.
     */
    @RequestMapping("/")
    public String mainPage(Model model){
        List<Habit> allHabits = habitService.findAll();
        model.addAttribute("habits", allHabits);
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


}
