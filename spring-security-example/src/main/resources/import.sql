-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO COMPANY (name, description) VALUES ('Company 1', 'Desctription for Company 1');
INSERT INTO COMPANY (name, description) VALUES ('Company 2', 'Desctription for Company 2');
INSERT INTO COMPANY (name, description) VALUES ('Company 3', 'Desctription for Company 3');

INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, penalty_points) VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07', 0);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, penalty_points, company_id) VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07', 0, 1);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, penalty_points) VALUES ('system_admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pera', 'Peric', 'system_admin@example.com', true, '2017-10-01 18:57:58.508-07', 0);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, penalty_points) VALUES ('user1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07', 0);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, penalty_points, company_id) VALUES ('admin1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07', 0, 3);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, penalty_points) VALUES ('system_admin1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pera', 'Peric', 'system_admin@example.com', true, '2017-10-01 18:57:58.508-07', 0);

INSERT INTO ROLE (name) VALUES ('ROLE_USER');			--registrovani korisnik
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');			--administrator kompanije
INSERT INTO ROLE (name) VALUES ('ROLE_SYSTEM_ADMIN');	--administrator sistema


INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- admin-u dodeljujemo rolu ADMIN
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 3); 
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); -- admin-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 2); -- admin-u dodeljujemo rolu ADMIN
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 3); 


INSERT INTO EQUIPMENT (name, price, company_id) VALUES ('Equipment 1', 256.5, 1);
INSERT INTO EQUIPMENT (name, price, company_id) VALUES ('Eq 2', 256.5, 1);
INSERT INTO EQUIPMENT (name, price, company_id) VALUES ('Equipment 3', 256.5, 2);
INSERT INTO EQUIPMENT (name, price, company_id) VALUES ('Equipment 4', 256.5, 3);

INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (0, '2024-02-27 08:00:00', 60, NULL, 2, NULL);
INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (1, '2024-02-28 09:00:00', 60, 1, 2, 1);
INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (2, '2024-02-24 09:00:00', 60, 1, 2, 1);
INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (2, '2024-02-29 10:00:00', 60, 2, 2, 1);
INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (0, '2024-02-29 10:00:00', 60, NULL, 2, NULL);
INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (1, '2024-01-31 09:00:00', 60, 1, 2, 1);
INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (1, '2024-01-31 09:00:00', 60, 4, 5, 4);
INSERT INTO APPOINTMENT (status, start_date, duration, equipment_id, administrator_id, user_id) VALUES (0, '2024-02-20 09:00:00', 60, 4, 5, 4);
