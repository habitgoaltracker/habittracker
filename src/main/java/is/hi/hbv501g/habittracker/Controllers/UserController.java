package is.hi.hbv501g.habittracker.Controllers;
import is.hi.hbv501g.habittracker.Persistence.Entities.User;
import is.hi.hbv501g.habittracker.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Route for requests to "/signup" path.
     *
     * @return The signup form.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user){
        return "signup";
    }

    /**
     * Route for requests to "/signup" path.
     * Gathers the credentials from a filled out sign up form and creates a new user.
     * User is then saved and stored in database.
     *
     * @return Path to root.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/signup";
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null){
            userService.save(user);
        }
        return "redirect:/";
    }

    /**
     * Route for requests to "/login" path.
     * Gathers the credentials from a filled out login form and creates a new user.
     * User is then saved and stored in database.
     *
     * @return The login form.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    /**
     * Route for requests to "/login" path.
     * Gathers the credentials from a filled out login form and validates them (logs user in if credentials match a user in database).
     * User is then added to HttpSession object.
     *
     * @return Path to root if credentials match the credentials of a user in the database, otherwise returns "wrongCredentials"
     * html.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/signup";
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/";
        }
        return "wrongCredentials";
    }

    //TO BE DELETED
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser != null) {
            model.addAttribute("LoggedInUser", sessionUser);
            return "LoggedInUser";
        }
        return "redirect:/";
    }
}
