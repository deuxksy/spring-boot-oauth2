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