insert into user_role (id, role_en, role_ua)
VALUES (0, 'banned', 'заблокований');
insert into user_role (id, role_en, role_ua)
VALUES (2, 'user', 'користувач');
insert into user_role (id, role_en, role_ua)
VALUES (3, 'admin', 'адміністратор');

insert into genre (id, name_en, name_ua)
values (1, 'fantasy', 'фентезі');
insert into genre (id, name_en, name_ua)
values (2, 'economy', 'економіка');
insert into genre (id, name_en, name_ua)
values (3, 'news', 'новини');
insert into genre (id, name_en, name_ua)
values (4, 'science', 'наука');
insert into genre (id, name_en, name_ua)
values (5, 'nature', 'природа');
insert into genre (id, name_en, name_ua)
values (6, 'technology', 'технології');

insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (1,
        'National Geographic - The Space',
        'Національна географія - Космос',
        'Enjoy surprising and informative stories that will give you a deeper appreciation of our immense universe.',
        'Насолоджуйтесь дивовижними та цікавими історіями, які допоможуть вам глибше зрозуміти наш безмежний всесвіт.',
        'http://localhost:3000/img/National Geographic - The Space.jpg', 5, 500);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (2,
        'Forbes',
        'Форбс',
        'News and features from Forbes Magazine, a leading source for reliable business news and financial information.',
        'Новини та матеріали журналу Форбс, головного джерела правдивих ділових новин та фінансової інформації.',
        'http://localhost:3000/img/Forbes.jpg', 2, 500);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (3,
        'National Geographic - Wild',
        'Національна географія - Дика природа',
        'Nat Geo WILD is the only magazine 100% dedicated to animals and the people who love them.',
        'Нат Гео Природа - це єдиний журнал, на 100% присвячений тваринам та людям, які їх люблять.',
        'http://localhost:3000/img/National Geographic - Wild.jpg',
        5,
        450);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (4,
        'Time',
        'Час',
        'TIME Magazine is the one of the most authoritative and informative guide to what is happening in current affairs, politics and business.',
        'Журнал Час - це один із самих інформативних джовідників по поточним новинам, політиці та бізнесу. ',
        'http://localhost:3000/img/Time.jpg',
        3,
        300);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (5,
        'Fantasy Magazine',
        'Журнал фантастики',
        'Fantasy Magazine is a digital magazine focusing exclusively on the fantasy genre.',
        'Журнал Фантастики - це цифровий журнал, цілком присвячений жанру фентезі.',
        'http://localhost:3000/img/Fantasy magazine.jpg',
        1,
        400);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (6,
        'Science News',
        'Новини науки',
        'Science news: astronomy, archaeology, paleontology, health, physics, space exploration and others.',
        'Новини науки: астрономія, археологія, здоров''я, вивчення космосу та інше.',
        'http://localhost:3000/img/Science News.jpg',
        4,
        420);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (7,
        'Wired',
        'На проводі',
        'Wired - is the essential source of information and ideas that make sense of a world in constant transformation',
        'На проводі - найважливіше джерело інформації та ідей, які надають сенс світу, який знаходиться в постійній трансформації.',
        'http://localhost:3000/img/Wired.jpg',
        6,
        370);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (8,
        'Digital Innovation',
        'Цифрові інновації',
        'Digital innovation is the use of digital technology  to improve business processes.',
        'Цифрові інновації - це використання ІТ технологій для покращення бізнес-процесів.',
        'http://localhost:3000/img/Digital Innovation.jpg',
        6,
        430);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (9,
        'Data Economy',
        'Економіка даних',
        'Data economy is the digital ecosystem for the producers and consumers of data.',
        'Економінка даних - це цифрова екосистема для виробників та користувачів інформації.',
        'http://localhost:3000/img/Data Economy.jpg',
        2,
        280);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (10,
        'Economics',
        'Економіка',
        'Explore mind-stretching insights into the world’s biggest issues including climate change.',
        'Вивчайте самі хвилюючі розум питання про світові проблеми, включаючи зміну клімату.',
        'http://localhost:3000/img/Economics.jpg',
        2,
        310);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (11,
        'Recycling',
        'Переробка',
        'Recycling. Global pollution problems and how to deal with them.',
        'Переробка. Глобальні проблеми забруднення середовища та способи їх вирішення.',
        'http://localhost:3000/img/Recycling.jpg',
        2,
        280);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (12,
        'Science',
        'Наука',
        'Magazine about impactful research, daily news and expert commentary.',
        'Журнал про результативні дослідження, щоденні новини та експертні коментарі.',
        'http://localhost:3000/img/Science.jpg',
        4,
        315);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (13,
        'Popular science',
        'Популярна наука',
        'Popular Science features news articles and comments about the latest scientific advances.',
        'Популярна наука - містить статті та коментарі стосовно останніх наукових досліджень.',
        'http://localhost:3000/img/Popular science.jpg',
        4,
        340);
insert into edition (id, title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values (14,
        'Nature',
        'Природа',
        'Nature is the world`s leading multidisciplinary science journal.',
        'Природа - це світовий мульти-дисциплінний науковий журнал.',
        'http://localhost:3000/img/Nature.jpg',
        5,
        280);

insert into usr (id, username, pass, email, first_name, user_image, balance, role_id)
VALUES (1, 'admin', 'Pass1234', 'admin@gmail.com', 'Andrii', 'no image', 1100, 3);
insert into usr (id, username, pass, email, first_name, user_image, balance, role_id)
VALUES (2, 'user1', 'Pass1234', 'user1@gmail.com', 'Diana', 'no image', 1200, 2);
insert into usr (id, username, pass, email, first_name, user_image, balance, role_id)
VALUES (3, 'user2', 'Pass1234', 'user2@gmail.com', 'Artem', 'no image', 1300, 2);
insert into usr (id, username, pass, email, first_name, user_image, balance, role_id)
VALUES (4, 'user3', 'Pass1234', 'user3@gmail.com', 'Alexandra', 'no image', 1400, 2);
insert into usr (id, username, pass, email, user_image, balance, role_id)
VALUES (5, 'user4', 'Pass1234', 'user4@gmail.com', 'no image', 1500, 2);

insert into user_edition(user_id, edition_id) VALUES (1, 1);