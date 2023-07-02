package com.epam.esm.SpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public class MainController {
    @GetMapping("/unsecured")
    public String unsecuredData(){
        return "Unsecured data";
    }
    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}
