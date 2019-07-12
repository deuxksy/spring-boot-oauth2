package com.zzizily.oauth2;

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

    @GetMapping("/api/session")
    public Authentication session() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/callback")
    public ResponseEntity callback(@RequestParam Map<String,String> params) {
        return ResponseEntity.ok().body(params);
    }

    @GetMapping("/user/me")
    public Principal userMe(Principal principal){
        return principal;
    }
}
