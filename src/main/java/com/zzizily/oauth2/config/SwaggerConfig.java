package com.zzizily.oauth2.config;

import com.google.common.base.Predicates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger SPEC
 * https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@RequiredArgsConstructor
@Slf4j
public class SwaggerConfig {

  private final ResourceServerProperties resourceServerProperties;
  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${security.oauth2.client.scope}")
  private List<String> scopes;

  @Bean
  public Docket defaultApiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .ignoredParameterTypes(AuthenticationPrincipal.class)
      .apiInfo(getApiInfo())
      .select()
      .paths(PathSelectors.ant("/**"))
      .paths(Predicates.not(PathSelectors.regex("/(error|actuator).*")))
      .build()
      // swagger 에서 녹색 자물쇠 나오게 하기
      .securityContexts(Collections.singletonList(securityContext()))
      .securitySchemes(Collections.singletonList(securitySchema()))
      ;
  }


  private ApiInfo getApiInfo() {

    return new ApiInfo(
      String.format("Tricycle RESTful %s", applicationName),
      "Tricycle",
      "0.0.1",
      "/",
      new Contact("Tricycle", "http://www.tricycle.co.kr", "tricycle_dev@tricycle.co.kr"),
      null,
      null,
      Collections.emptyList()
    );
  }

  private SecurityContext securityContext() {
    return SecurityContext
      .builder()
      .securityReferences(defaultAuth())
      .forPaths(PathSelectors.ant("/**"))
      .build();
  }

  private List<SecurityReference> defaultAuth() {
    final AuthorizationScope[] authorizationScopes = new AuthorizationScope[scopes.size()];
    for (int i = 0; i < scopes.size(); i++) {
      authorizationScopes[i] = new AuthorizationScope(scopes.get(i), "");
    }
    return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
  }

  /**
   * Oauth2 Swager UI 관련 설정
   *
   * @return
   */
  private OAuth securitySchema() {
    log.info("{}", resourceServerProperties.getTokenInfoUri());
    List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
    for (String scope : scopes) {
      authorizationScopeList.add(new AuthorizationScope(scope, ""));
    }
    List<GrantType> grantTypes = new ArrayList<>();
    GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(
      resourceServerProperties.getTokenInfoUri()
                              .replace("check_token", "token")
    );
    grantTypes.add(creGrant);
    return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
  }
}


