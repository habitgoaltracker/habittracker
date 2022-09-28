package is.hi.hbv501g.habittracker.Controllers;

import is.hi.hbv501g.habittracker.Persistence.Entities.Habit;
import is.hi.hbv501g.habittracker.Services.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    private HabitService habitService;

    @Autowired
    public MainController(HabitService habitService){
        this.habitService = habitService;
    }

    @RequestMapping("/")
    public String mainPage(Model model){
        List<Habit> allHabits = habitService.findAll();
        model.addAttribute("habits", allHabits);
        return "main";
    }
}
