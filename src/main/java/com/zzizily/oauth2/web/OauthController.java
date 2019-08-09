package com.zzizily.oauth2.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/tricycle")
@Slf4j
@RequiredArgsConstructor
public class OauthController {

  @GetMapping("/logout")
  public void exit(HttpServletRequest request, HttpServletResponse response, @RequestParam String callback) {
    new SecurityContextLogoutHandler().logout(request, null, null);
    try {
      log.info("{}", callback);
      response.sendRedirect(callback);
    } catch (IOException e) {
      log.error("{}", e);
    }
  }
}