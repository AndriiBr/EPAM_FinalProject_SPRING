delete from role;
delete from user_edition;
delete from usr;
delete from edition;

insert into usr (id, username, password, email, first_name, user_image, balance)
VALUES (1, 'testAdmin', 'Test1234', 'admin@gmail.com', 'Andrii', 'no image', 1000);
insert into usr (id, username, password, email, first_name, user_image, balance)
VALUES (2, 'testUser1', 'Test1234', 'user1@gmail.com', 'Diana', 'no image', 200);
insert into usr (id, username, password, email, first_name, user_image, balance)
VALUES (3, 'testUser2', 'Test1234', 'user2@gmail.com', 'Artem', 'no image', 200);

insert into role (user_id, roles) VALUES (1, 'ROLE_USER'), (1, 'ROLE_ADMIN');
insert into role (user_id, roles) VALUES (2, 'ROLE_USER');
insert into role (user_id, roles) VALUES (3, 'ROLE_BLOCKED');

insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (1,
        'Test Edition 1',
        'Тестове Видання 1',
        'Test Text TEXT 1',
        'Тестовий текст ТЕКСТ 1',
        'http://localhost:3000/test/test_image.jpg',
        1,
        500);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (2,
        'Test Edition 2',
        'Тестове Видання 2',
        'Test Text TEXT 2',
        'Тестовий текст ТЕКСТ 2',
        'http://localhost:3000/test/test_image.jpg',
        1,
        500);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (3,
        'Test Edition 3',
        'Тестове Видання 3',
        'Test Text TEXT 3',
        'Тестовий текст ТЕКСТ 3',
        'http://localhost:3000/test/test_image.jpg',
        2,
        450);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (4,
        'Test Edition 4',
        'Тестове Видання 4',
        'Test Text TEXT 4',
        'Тестовий текст ТЕКСТ 4',
        'http://localhost:3000/test/test_image.jpg',
        2,
        300);

insert into user_edition(edition_id, user_id) VALUES (1, 1);
insert into user_edition(edition_id, user_id) VALUES (2, 1);
insert into user_edition(edition_id, user_id) VALUES (3, 1);
insert into user_edition(edition_id, user_id) VALUES (1, 2);
insert into user_edition(edition_id, user_id) VALUES (2, 2);
insert into user_edition(edition_id, user_id) VALUES (1, 3);

alter sequence hibernate_sequence restart 10;
alter sequence usr_id_seq restart 10;
alter sequence edition_id_seq restart 10;
