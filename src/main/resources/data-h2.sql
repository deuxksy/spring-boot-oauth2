SET SCHEMA oauth;

INSERT INTO oauth_client_details (
  client_id,
  resource_ids,
  client_secret,
  scope,
  authorized_grant_types,
  web_server_redirect_uri,
  authorities,
  access_token_validity,
  refresh_token_validity,
  additional_information,
  autoapprove
) VALUES (
  'local_client',
  null,
  '{noop}password',-- '{bcrypt}$2a$10$iP9ejueOGXO29.Yio7rqeuW9.yOC4YaV8fJp3eIWbP45eZSHFEwMG', -- password
  'read_profile,read_posts', -- 'test'
  'authorization_code,implicit,password,client_credentials,refresh_token',
  'http://cloud-oauth-local-service-pig.trcc.com:38001/callback,http://cloud-oauth-local-service-pig.trcc.com:18002/callback',
  null,
  60,
  6000,
  null ,
  'false'
);

INSERT INTO oauth_client_details (
  client_id,
  resource_ids,
  client_secret,
  scope,
  authorized_grant_types,
  web_server_redirect_uri,
  authorities,
  access_token_validity,
  refresh_token_validity,
  additional_information,
  autoapprove
) VALUES (
   'dev_client',
   null,
   '{noop}password',-- '{bcrypt}$2a$10$iP9ejueOGXO29.Yio7rqeuW9.yOC4YaV8fJp3eIWbP45eZSHFEwMG', -- password
   'read_profile,read_posts', -- 'test'
   'authorization_code,implicit,password,client_credentials,refresh_token',
   'http://cloud-oauth-dev-service-pig.trcc.com:8080/login,http://cloud-oauth-dev-service-pig.trcc.com:8080/callback,http://cloud-oauth-dev-service-pig.trcc.com:8080/callback,http://cloud-oauth-stage-service-pig.trcc.com:8080/callback',
   null,
   60,
   6000,
   null ,
   'false'
 );

-- INSERT INTO oauth_user(username, password, site_id, use_yn) VALUES ('user', '{noop}password', 1, 'Y');
-- INSERT INTO oauth_user(username, password, site_id, use_yn) VALUES ('admin', '{noop}password', 2, 'Y');

-- INSERT INTO oauth_user_role (oauth_user_id, role ) VALUES ( 1, 'ROLE_USER');
-- INSERT INTO oauth_user_role (oauth_user_id, role ) VALUES ( 2, 'ROLE_ADMIN');