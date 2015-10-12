package mf.um.controllers;

import mf.um.domain.Department;
import mf.um.domain.Users;
import mf.um.services.DepartmentService;
import mf.um.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by qurbonov on 10/9/2015.
 */
@RestController
public class RestApiExample {
    @Autowired
    UsersService usersService;
    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/api/dvs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Users>> getUsers() {
        Collection<Users> userses = usersService.findAll();
        return new ResponseEntity<Collection<Users>>(userses, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/dept", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Department>> getDepartments() {
        Collection<Department> departments = departmentService.findAll();
        return new ResponseEntity<Collection<Department>>(departments, HttpStatus.OK);
    }
}
