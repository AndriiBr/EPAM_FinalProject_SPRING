insert into genre (name_en, name_ua)
values ('fantasy', 'фентезі');
insert into genre (name_en, name_ua)
values ('economy', 'економіка');
insert into genre (name_en, name_ua)
values ('news', 'новини');
insert into genre (name_en, name_ua)
values ('science', 'наука');
insert into genre (name_en, name_ua)
values ('nature', 'природа');
insert into genre (name_en, name_ua)
values ('technology', 'технології');

insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('National Geographic - The Space',
        'Національна географія - Космос',
        'Enjoy surprising and informative stories that will give you a deeper appreciation of our immense universe.',
        'Насолоджуйтесь дивовижними та цікавими історіями, які допоможуть вам глибше зрозуміти наш безмежний всесвіт.',
        'http://localhost:3000/img/National Geographic - The Space.jpg', 5, 500);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Forbes',
        'Форбс',
        'News and features from Forbes Magazine, a leading source for reliable business news and financial information.',
        'Новини та матеріали журналу Форбс, головного джерела правдивих ділових новин та фінансової інформації.',
        'http://localhost:3000/img/Forbes.jpg', 2, 500);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('National Geographic - Wild',
        'Національна географія - Дика природа',
        'Nat Geo WILD is the only magazine 100% dedicated to animals and the people who love them.',
        'Нат Гео Природа - це єдиний журнал, на 100% присвячений тваринам та людям, які їх люблять.',
        'http://localhost:3000/img/National Geographic - Wild.jpg',
        5,
        450);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Time',
        'Час',
        'TIME Magazine is the one of the most authoritative and informative guide to what is happening in current affairs, politics and business.',
        'Журнал Час - це один із самих інформативних джовідників по поточним новинам, політиці та бізнесу. ',
        'http://localhost:3000/img/Time.jpg',
        3,
        300);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Fantasy Magazine',
        'Журнал фантастики',
        'Fantasy Magazine is a digital magazine focusing exclusively on the fantasy genre.',
        'Журнал Фантастики - це цифровий журнал, цілком присвячений жанру фентезі.',
        'http://localhost:3000/img/Fantasy magazine.jpg',
        1,
        400);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Science News',
        'Новини науки',
        'Science news: astronomy, archaeology, paleontology, health, physics, space exploration and others.',
        'Новини науки: астрономія, археологія, здоров''я, вивчення космосу та інше.',
        'http://localhost:3000/img/Science News.jpg',
        4,
        420);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Wired',
        'На проводі',
        'Wired - is the essential source of information and ideas that make sense of a world in constant transformation',
        'На проводі - найважливіше джерело інформації та ідей, які надають сенс світу, який знаходиться в постійній трансформації.',
        'http://localhost:3000/img/Wired.jpg',
        6,
        370);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Digital Innovation',
        'Цифрові інновації',
        'Digital innovation is the use of digital technology  to improve business processes.',
        'Цифрові інновації - це використання ІТ технологій для покращення бізнес-процесів.',
        'http://localhost:3000/img/Digital Innovation.jpg',
        6,
        430);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Data Economy',
        'Економіка даних',
        'Data economy is the digital ecosystem for the producers and consumers of data.',
        'Економінка даних - це цифрова екосистема для виробників та користувачів інформації.',
        'http://localhost:3000/img/Data Economy.jpg',
        2,
        280);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Economics',
        'Економіка',
        'Explore mind-stretching insights into the world’s biggest issues including climate change.',
        'Вивчайте самі хвилюючі розум питання про світові проблеми, включаючи зміну клімату.',
        'http://localhost:3000/img/Economics.jpg',
        2,
        310);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Recycling',
        'Переробка',
        'Recycling. Global pollution problems and how to deal with them.',
        'Переробка. Глобальні проблеми забруднення середовища та способи їх вирішення.',
        'http://localhost:3000/img/Recycling.jpg',
        2,
        280);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Science',
        'Наука',
        'Magazine about impactful research, daily news and expert commentary.',
        'Журнал про результативні дослідження, щоденні новини та експертні коментарі.',
        'http://localhost:3000/img/Science.jpg',
        4,
        315);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Popular science',
        'Популярна наука',
        'Popular Science features news articles and comments about the latest scientific advances.',
        'Популярна наука - містить статті та коментарі стосовно останніх наукових досліджень.',
        'http://localhost:3000/img/Popular science.jpg',
        4,
        340);
insert into edition (title_en, title_ua, text_en, text_ua, title_image, genre_id, price)
values ('Nature',
        'Природа',
        'Nature is the world`s leading multidisciplinary science journal.',
        'Природа - це світовий мульти-дисциплінний науковий журнал.',
        'http://localhost:3000/img/Nature.jpg',
        5,
        280);