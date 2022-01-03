package com.bsf.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        System.out.println("Index action");
        return "Welcome to BSF backend!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseBody
    public String admin() {
        System.out.println("admin action");
        return "Welcome Admin!!";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String user() {
        System.out.println("user action");
        return "Welcome User!!";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @ResponseBody
    public String manager() {
        System.out.println("manager action");
        return "Welcome Manager!!";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @RequestMapping(value = "/adminmanager", method = RequestMethod.GET)
    @ResponseBody
    public String adminmanager() {
        System.out.println("admin manager action");
        return "Welcome Admin Manager!!";
    }
}
