CREATE SCHEMA IF NOT EXISTS oauth;

SET SCHEMA oauth;

DROP TABLE IF EXISTS oauth_client_details;
DROP TABLE IF EXISTS oauth_client_token;
-- JWT 사용시 필요 없음
-- DROP TABLE IF EXISTS oauth_access_token;
-- DROP TABLE IF EXISTS oauth_refresh_token;
DROP TABLE IF EXISTS oauth_code;
DROP TABLE IF EXISTS oauth_approvals;

-- used in tests that use HSQL
create table oauth_client_details
(
  client_id               VARCHAR(256) PRIMARY KEY,
  resource_ids            VARCHAR(256),
  client_secret           VARCHAR(256),
  scope                   VARCHAR(256),
  authorized_grant_types  VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities             VARCHAR(256),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(256)
);

create table oauth_client_token
(
  token_id          VARCHAR(256),
  token             LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name         VARCHAR(256),
  client_id         VARCHAR(256)
);

-- create table oauth_access_token
-- (
--   token_id          VARCHAR(256),
--   token             LONGVARBINARY,
--   authentication_id VARCHAR(256) PRIMARY KEY,
--   user_name         VARCHAR(256),
--   client_id         VARCHAR(256),
--   authentication    LONGVARBINARY,
--   refresh_token     VARCHAR(256)
-- );

-- create table oauth_refresh_token
-- (
--   token_id       VARCHAR(256),
--   token          LONGVARBINARY,
--   authentication LONGVARBINARY
-- );

create table oauth_code
(
  code           VARCHAR(256),
  authentication LONGVARBINARY
);

create table oauth_approvals
(
  userId         VARCHAR(256),
  clientId       VARCHAR(256),
  scope          VARCHAR(256),
  status         VARCHAR(10),
  expiresAt      TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

-- 사용자 정보
-- create table oauth_user (
--   id serial PRIMARY KEY,
--   username VARCHAR(256),
--   password VARCHAR(256),
--   site_id  SMALLINT,
--   use_yn  CHAR(1)
-- );


-- 사용자 롤
-- CREATE TABLE oauth_user_role (
--  id serial NOT NULL,
--  oauth_user_id int4 NOT NULL,
--  role varchar(20) NOT NULL,
--  CONSTRAINT oauth_user_role_pk PRIMARY KEY (id)
-- );

-- 소셜 계정 정보
-- CREATE TABLE oauth_social_account (
--   social_id varchar(100) NOT NULL,
--   oauth_user_id int4 NOT NULL,
--   "type" varchar(10) NOT NULL,
--   insdate timestamp NOT NULL DEFAULT CURRENT_DATE,
--   CONSTRAINT oauth_social_account_pk PRIMARY KEY (social_id,oauth_user_id,"type")
-- );
-- COMMENT ON TABLE oauth_social_account IS '소셜 로그인 연동 계정 관리';