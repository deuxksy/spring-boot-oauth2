package com.zzizily.oauth2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SimpleController {

    @GetMapping("/api/data")
    public Authentication session() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/api/me")
    public Principal userMe(Principal principal){
        return principal;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/callback")
    public ResponseEntity callback(@RequestParam Map<String,String> params) {
        return ResponseEntity.ok().body(params);
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
