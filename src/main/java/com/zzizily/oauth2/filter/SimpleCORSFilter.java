package com.zzizily.oauth2.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RequiredArgsConstructor
public class SimpleCORSFilter implements Filter {

  private final CorsEndpointProperties corsEndpointProperties;

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;

    String origin = request.getHeader("Origin");
    if (corsEndpointProperties.getAllowedOrigins().contains(origin)) {
      response.setHeader("Access-Control-Allow-Origin", origin);
    }
    response.setHeader("Access-Control-Allow-Methods", Strings.join(corsEndpointProperties.getAllowedMethods().iterator(), ','));
    response.setHeader("Access-Control-Allow-Headers", Strings.join(corsEndpointProperties.getAllowedHeaders().iterator(), ','));
    response.setHeader("Access-Control-Max-Age", "" + corsEndpointProperties.getMaxAge().getSeconds());

    log.info("{}", response.getHeader("Access-Control-Allow-Origin"));

    if (StringUtils.equalsIgnoreCase("OPTIONS", request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(req, res);
    }
  }

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void destroy() {
  }
}