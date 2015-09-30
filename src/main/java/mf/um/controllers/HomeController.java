package mf.um.controllers;

import mf.um.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UsersService usersService;

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }

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
    public String userProfile(Model model, Principal principal) {
        model.addAttribute("userprofile", "Пользователи");
        model.addAttribute("allUsers", usersService.findByName(principal.getName()));
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
