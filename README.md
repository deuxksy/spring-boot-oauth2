## spring-boot-oauth2

Spring Boot 2.1 기반으로 Spring Security OAuth2를 살펴보는 프로젝트입니다. 

- Authorization Code Grant Type
- Implicit Grant
- Resource Owner Password Credentials Grant
- Client Credentials Grant Type OAuth2 

인증 방식에 대한 간단한 셈플 코드부터 OAuth2 TokenStore 저장을 h2, redis 

## 구성

- Spring Boot 2.1.4
- Spring Security OAuth2
- Lombok
- Java8

## 목차

- [branch step-01 OAuth2 인증 방식 Flow 및 Sample Code](https://github.com/cheese10yun/springboot-oauth2/blob/master/docs/OAuth2-Grant.md)
- [branch step-02 토큰과 클라이언트 정보 RDBMS 저장](https://github.com/cheese10yun/springboot-oauth2/blob/master/docs/OAuth2-RDBMSt.md)
- tag v1 rdb > h2 로 변경, maven 을 gradle 로 변경


http://cloud-oauth-local-service-pig.trcc.com:8080/oauth/authorize?client_id=client&redirect_uri=http://localhost:9000/callback&response_type=token&scope=read_profile&state=test

## 참조
- [Spring Security Oauth2](https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/htmlsingle/)
- [Spring Security](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [jwt](https://jwt.io/)

### 개인블로그
- [CORS 설정과 Spring Security](https://oddpoet.net/blog/2017/04/27/cors-with-spring-security/)
- [스프링 부트 2 및 OAuth2 / JWT 구성](https://cnpnote.tistory.com/entry/SPRING-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-2-%EB%B0%8F-OAuth2-JWT-%EA%B5%AC%EC%84%B1)
- [Spring Boot Oauth2 – ResourceServer](https://daddyprogrammer.org/post/1754/spring-boot-oauth2-resourceserver/)
- [Spring Boot 2, OAuth2 and JWT authentication example](https://medium.com/@ChamithKodikara/jwt-oauth2-authentication-example-with-spring-boot-2-2e92bacd68e5)
- [Spring security JWT 연동](https://yookeun.github.io/java/2017/07/23/spring-jwt/)
- [spring OAuth server 구성](https://derekpark.tistory.com/40?category=758413)
- [Centralized Authorization with OAuth2 + JWT using Spring Boot 2](https://blog.marcosbarbero.com/centralized-authorization-jwt-spring-boot2/)