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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MainController {


    private final HabitService habitService;

    @Autowired
    public MainController(HabitService habitService){
        this.habitService = habitService;
        System.out.println("build controller");
    }

    @RequestMapping("/")
    public String mainPage(Model model){
        List<Habit> allHabits = habitService.findAll();
        model.addAttribute("habits", allHabits);
        return "main";
    }

    @RequestMapping(value="/addhabit", method = RequestMethod.GET)
    public String addHabitForm(Habit habit){
        return "newHabit";
    }

    @RequestMapping(value="/addhabit", method = RequestMethod.POST)
    public String addHabit(Habit habit, BindingResult result, Model model){
        if (result.hasErrors()){
            return "newHabit";
        }
        habitService.save(habit);
        return "redirect:/";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteHabit(@PathVariable("id") long id, Model model){
        Habit habitToDelete = habitService.findByID(id);
        return "redirect:/";
    }
}
