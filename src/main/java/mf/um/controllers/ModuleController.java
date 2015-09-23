package mf.um.controllers;

import mf.um.domain.Module;
import mf.um.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qurbonov on 9/2/2015.
 */
@Controller
public class ModuleController implements ErrorController {
    @Autowired
    ModuleService moduleService;

    //List modules
    @RequestMapping(value = "/modules", method = RequestMethod.GET)
    public String systems(Model model) {
        model.addAttribute("modules", "Модулы");
        model.addAttribute("allModules", moduleService.findAll());

        return "/pages/modules";
    }

    //add module Form
    @RequestMapping(value = "/modules/new", method = RequestMethod.GET)
    public String addmodule(Model model) {
        model.addAttribute("module", new Module());
        model.addAttribute("addmodule", "Добавить модул");
        return "forms/addModule";
    }

    //add module Form action
    @RequestMapping(value = "/modules/new", method = RequestMethod.POST)
    public String save(Module module) {
        module = moduleService.save(module);
        return "redirect:/modules/";
    }

    //edit module Form
    @RequestMapping(value = "/modules/{moduleId}", method = RequestMethod.GET)
    public String editmodule(@PathVariable Long moduleId, Model model) {
        model.addAttribute("module", moduleService.findOne(moduleId));
        model.addAttribute("editmodule", "Редактировать");
        return "forms/editModule";
    }

    @RequestMapping(value = "/modules/{moduleId}", method = RequestMethod.POST)
    public String edit(@PathVariable Long moduleId, Model model, Module module) {
        module.setId(moduleId);
        model.addAttribute("module", moduleService.save(module));
        model.addAttribute("addmodule", "Добавить модул");
        return "redirect:/modules/";
    }

    //delete module
    @RequestMapping(value = "/modules/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteModule(@PathVariable(value = "id") String id) {
        moduleService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
