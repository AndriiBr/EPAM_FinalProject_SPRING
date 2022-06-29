insert into usr (username, password, email, first_name, user_image, balance)
    VALUES ('admin', 'Pass1234', 'admin@gmail.com', 'Andrii', 'no image', 1100);
insert into usr (username, password, email, first_name, user_image, balance)
    VALUES ('user1', 'Pass1234', 'user1@gmail.com', 'Diana', 'no image', 1200);
insert into usr (username, password, email, first_name, user_image, balance)
    VALUES ('user2', 'Pass1234', 'user2@gmail.com', 'Artem', 'no image', 1300);
insert into usr (username, password, email, first_name, user_image, balance)
VALUES ('user3', 'Pass1234', 'user3@gmail.com', 'Alexandra', 'no image', 1400);

insert into role (user_id, roles) VALUES (1, 'ROLE_USER'), (1, 'ROLE_ADMIN');
insert into role (user_id, roles) VALUES (2, 'ROLE_USER');
insert into role (user_id, roles) VALUES (3, 'ROLE_USER');
insert into role (user_id, roles) VALUES (4, 'ROLE_BLOCKED');
