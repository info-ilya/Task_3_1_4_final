-- Dumping data for tables

-- NOTE: The passwords are encrypted using BCrypt
--
-- Default password: admin
-- Default login: admin@localhost.com

INSERT IGNORE INTO `user` (age,password,first_name,last_name,email)
VALUES
(110,'$2a$10$ZPuF9m9My.MwbBHWF.jUxOoabnH7NC46YNly3bYPqjl2c.khWeTU6','Admin','Admin','admin@localhost.com');


INSERT IGNORE INTO `role` (name)
VALUES
('USER'), ('ADMIN');

INSERT IGNORE INTO `users_roles` (user_id,role_id)
VALUES
(1, 'USER'),
(1, 'ADMIN');