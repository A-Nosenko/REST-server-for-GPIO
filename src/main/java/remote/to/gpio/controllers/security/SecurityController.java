package remote.to.gpio.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import remote.to.gpio.models.user.User;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/17/2018.
 */
@Controller
public class SecurityController {

    @RequestMapping(method = RequestMethod.GET, value = "login")
    public String login(User user, ModelMap modelMap, Model model, String error) {

        modelMap.put("user", user);
        if (error != null) {
            model.addAttribute("error", "Wrong login or password.");
        }
        return "login";
    }
}
