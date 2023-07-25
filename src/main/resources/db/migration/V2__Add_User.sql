INSERT INTO users (username, password_hash)
VALUES ('admin', 'hashed_password_here');

INSERT INTO roles (name) VALUES ('USER'), ('ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
((SELECT id FROM users WHERE username = 'admin'),
 (SELECT id FROM roles WHERE name = 'ADMIN'));
