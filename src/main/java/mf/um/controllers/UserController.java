package mf.um.controllers;

import mf.um.domain.Role;
import mf.um.domain.Users;
import mf.um.services.DepartmentService;
import mf.um.services.ModuleService;
import mf.um.services.RoleService;
import mf.um.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by qurbonov on 9/2/2015.
 */
@Controller
public class UserController {
    @Autowired
    UsersService usersService;
    @Autowired
    ModuleService moduleService;
    @Autowired
    RoleService roleService;
    @Autowired
    DepartmentService departmentService;

    //manage users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", "Пользователи");
        model.addAttribute("allUsers", usersService.findAll());
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("allModules", moduleService.findAll());
        return "/pages/users";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String adduser(Model model) {
        model.addAttribute("adduser", "Добавить пользователя");
        Users user = new Users();
        model.addAttribute("user", user);
        model.addAttribute("availableModules", moduleService.findAll());
        model.addAttribute("availableRoles", roleService.findAll());
        model.addAttribute("availableDepartments", departmentService.findAll());
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
        model.addAttribute("availableModules", moduleService.findAll());
        model.addAttribute("availableRoles", roleService.findAll());
        model.addAttribute("availableDepartments", departmentService.findAll());
        return "forms/editUser";
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.POST)
    public String edit(@PathVariable Long userId, @ModelAttribute("user") Users user, Principal principal, Model model) {
        user.setId(userId);
        usersService.save(user);
        return "redirect:/users/";
    }

    //delete module
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUser(@PathVariable(value = "id") String id) {
        usersService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
