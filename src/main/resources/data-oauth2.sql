INSERT INTO `oauth_client_details` (
  `client_id`,
  `resource_ids`,
  `client_secret`,
  `scope`,
  `authorized_grant_types`,
  `web_server_redirect_uri`,
  `authorities`,
  `access_token_validity`,
  `refresh_token_validity`,
  `additional_information`,
  `autoapprove`
) VALUES (
  'client',
  null,
  '{noop}password',
  'read_profile,read_posts',
  'authorization_code,implicit,password,client_credentials,refresh_token',
  'http://cloud-oauth-local-service-pig.trcc.com:8080/callback',
  null,
  60,
  6000,
  null ,
  'false'
);