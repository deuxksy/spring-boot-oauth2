package com.zzizily.oauth2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@Slf4j
public class SimpleController {

    @GetMapping("/api/me")
    public Principal userMe(Principal principal){
        log.info("{}", principal);
        return principal;
    }

    @GetMapping("/oauth/callback")
    public ResponseEntity callback(@RequestParam Map<String,String> params) {
        return ResponseEntity.ok().body(params);
    }
}
