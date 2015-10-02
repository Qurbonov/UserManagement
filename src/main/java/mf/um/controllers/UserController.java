package mf.um.controllers;

import mf.um.domain.Users;
import mf.um.services.ModuleService;
import mf.um.services.UsersService;
import mf.um.services.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by qurbonov on 9/2/2015.
 */
@Controller
public class UserController {
    @Autowired
    UsersService usersService;
    @Autowired
    ModuleService moduleService;

    //manage users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", "Пользователи");
        model.addAttribute("allUsers", usersService.findAll());
        return "/pages/users";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String adduser(Model model) {
        model.addAttribute("adduser", "Добавить пользователя");
        Users user = new Users();
        user.setUsername("default");
        model.addAttribute("user", user);
        model.addAttribute("availableModules", moduleService.findAll());
        return "forms/addUser";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") Users user) {
        user = usersService.save(user);
        return "redirect:/users/";
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable Long userId, Model model) {
        model.addAttribute("user", usersService.findOne(userId));
        model.addAttribute("edituser", "Изменит пользователя");
        return "forms/editUser";
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.POST)
    public String edit(@PathVariable Long userId, Model model, Users users) {
        users.setId(userId);
        model.addAttribute("user", usersService.save(users));
        model.addAttribute("adduser", "Добавить пользователя");
        return "redirect:/users/";
    }

    //delete module
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteModule(@PathVariable(value = "id") String id) {
        usersService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
