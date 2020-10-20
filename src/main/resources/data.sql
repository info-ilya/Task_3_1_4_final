-- Dumping data for tables

-- NOTE: The passwords are encrypted using BCrypt
--
-- Default password: admin
-- Default login: admin
-- Default role for admin - admin

INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES
('admin','$2a$10$ZPuF9m9My.MwbBHWF.jUxOoabnH7NC46YNly3bYPqjl2c.khWeTU6','Admin','Admin','admin@localhost.com');

INSERT INTO `role` (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1, 1),
(1, 2);