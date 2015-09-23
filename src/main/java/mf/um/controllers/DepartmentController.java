package mf.um.controllers;

import mf.um.domain.Department;
import mf.um.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qurbonov on 9/6/2015.
 */
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    //list dept
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String departments(Model model) {
        model.addAttribute("departments", "Отделы");
        model.addAttribute("allDepartment", departmentService.findAll());
        return "/pages/departments";
    }

    //add dept form
    @RequestMapping(value = "/departments/new", method = RequestMethod.GET)
    public String addDepartment(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("addDepartment", "Добавить отдела");
        return "forms/addDepartment";
    }

    @RequestMapping(value = "/departments/new", method = RequestMethod.POST)
    public String createDepartment(Department department) {
        department = departmentService.save(department);
        return "redirect:/departments/";
    }

    //edit department Form
    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.GET)
    public String editmodule(@PathVariable Long departmentId, Model model) {
        model.addAttribute("department", departmentService.findOne(departmentId));
        model.addAttribute("editdepartment", "Редактировать");
        return "forms/editDepartment";
    }

    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.POST)
    public String edit(@PathVariable Long departmentId, Model model, Department department) {

        department.setId(departmentId);
        model.addAttribute("department", departmentService.save(department));
        model.addAttribute("addDepartment", "Добавить модул");
        return "redirect:/departments/";
    }


    //delete module
    @RequestMapping(value = "/departments/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteModule(@PathVariable(value = "id") String id) {
        departmentService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
