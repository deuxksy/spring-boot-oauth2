-- hibernate only SQL Script
INSERT INTO oauth_user(username, password, site_id, use_yn) VALUES ('user', '{noop}password', 1, 'Y');
INSERT INTO oauth_user(username, password, site_id, use_yn) VALUES ('admin', '{noop}password', 1, 'Y');

INSERT INTO oauth_user_role (oauth_user_id, role ) VALUES ( 1, 'ROLE_USER');
INSERT INTO oauth_user_role (oauth_user_id, role ) VALUES ( 2, 'ROLE_ADMIN');