package mf.um.controllers;

import mf.um.LoginFilter;
import mf.um.domain.Role;
import mf.um.domain.Users;
import mf.um.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by qurbonov on 9/28/2015.
 */
@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProcess(HttpServletRequest request, Credentials credentials) {
        HttpSession session = request.getSession();

        String from = request.getParameter("from");
        Users users = usersService.findUsers(credentials);

/*
test
        asdas
//*/
//        List d = users.getRoles();
//        System.out.println("d " + d.toString());
//        String adm = "redirect:/home";
//        String user = "redirect:/user/index.html#/";
//        for (Role role : users.getRoles()) {
//            if (role.getId() == 1) {
//                adm = "redirect:/home";
//            }
//            if (role.getId() == 1) {
//                user = "redirect:/user/index.html#/";
//            }
//        }
        Role o = users.getRoles().get(0);
        Long d = o.getId();
        if (users != null && users.isEnabled()) {
            session.setAttribute(LoginFilter.USER_KEY, users);
            String url = from;
            if (url == null || url.isEmpty()) {
                url = "/home";
            }
            if (d == 1) {
                return "redirect:/home";
            } else if (d == 2) {
                return "redirect:/user/index.html#/";
            }
        } else {
            String url = "redirect:/login?fail=1";
            if (from != null && !from.isEmpty()) {
                url = url + "&from=" + from;
            }
            return url;
        }
        return from;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

}
