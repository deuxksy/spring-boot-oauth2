package com.zzizily.oauth2.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tricycle")
@Slf4j
@RequiredArgsConstructor
public class OauthRestController {

  @GetMapping("/me")
  public Principal userMe(Principal principal){
    log.info("{}", principal);
    return principal;
  }

  @GetMapping("/data")
  public Map<String, Object> getData(){
    HashMap<String, Object> map = new HashMap<>();
    map.put("k1", "v1");
    return map;
  }

  @GetMapping("/callback")
  public ResponseEntity callback(@RequestParam Map<String,String> params) {
    return ResponseEntity.ok().body(params);
  }
}
