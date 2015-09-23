package mf.um.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/layout", method = RequestMethod.GET)
    public String layout(Model model) {
        return "layout";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("modules", "Модулы");
        model.addAttribute("users", "Пользователи");
        model.addAttribute("departments", "Отделы");
        model.addAttribute("message", "Welcome");
        return "/pages/home";
    }

    @RequestMapping(value = "/userprofile", method = RequestMethod.GET)
    public String userProfile(Model model) {
        return "/pages/userProfile";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        return "/pages/about";
    }

    @RequestMapping(value = "/mfhujjat", method = RequestMethod.GET)
    public String mfhujjat(Model model) {
        model.addAttribute("addmodule", "Добавить модул");
        return "/pages/mfhujjat";
    }

}
