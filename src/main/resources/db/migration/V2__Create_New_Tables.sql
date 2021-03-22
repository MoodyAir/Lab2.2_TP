-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
-- ТАБЛИЦА 'ШКОЛЬНИК'
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

DROP TABLE IF EXISTS pupils;
DROP TABLE IF EXISTS school;
-- создание таблицы (create)
CREATE TABLE pupils(
                       id SERIAL PRIMARY KEY,
                       surname VARCHAR(50) NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       fathername VARCHAR(50),
                       gender VARCHAR(1) NOT NULL CHECK (gender IN ('M', 'F')),
                       nationality VARCHAR(50) NOT NULL,
                       height INT NOT NULL,
                       weight INT NOT NULL,
                       birthday DATE NOT NULL,
                       phone_number VARCHAR(15) NOT NULL,
                       post_code VARCHAR(10) NOT NULL,
                       country VARCHAR(30) NOT NULL,
                       region VARCHAR(30),
                       district VARCHAR(30),
                       city VARCHAR(30) NOT NULL,
                       street VARCHAR(30) NOT NULL,
                       building VARCHAR(10) NOT NULL,
                       flat INT,
                       grade INT CHECK (grade IN (1,2,3,4,5,6,7,8,9,10,11)),
                       school_id INT
);

-- Создать таблицу school (id, sch_name, sch_description).
CREATE TABLE school(
                       id INT,
                       sch_name VARCHAR(50) NOT NULL,
                       sch_description VARCHAR(50),
                       UNIQUE(id)
);



INSERT INTO pupils (surname, name, fathername, gender, nationality, height, weight,
                    birthday, phone_number, post_code, country, region, district,
                    city, street, building, flat, grade, school_id)
VALUES ('Горбач', 'Дмитрий', 'Николаевич', 'M', 'Belarusian', 182, 70,
        (TO_DATE('13.09.2001', 'DD.MM.YYYY')), '375291976500', '220137', 'Belarus', 'Minsk', 'Minsk',
        'Minsk', 'ул. Ангарская', '21', 1, '7', '80'),

       ('Врублевская', 'Екатерина', 'Александровна', 'F', 'Belarusian', 173, 50,
        (TO_DATE('29.07.2002', 'DD.MM.YYYY')), '375291972233', '220102', 'Belarus', 'Minsk', 'Minsk',
        'Dziarzhynsk', 'пер. Весенний', '23', NULL, '2', '10'),

       ('Доскоч', 'Роман', 'Дмитриевич', 'M', 'Belarusian', 183, 68,
        (TO_DATE('15.05.2002', 'DD.MM.YYYY')), '375295553535', '220047', 'Belarus', 'Mahilyow', 'Horki',
        'Horki', 'ул. Сельскохозяйственная', '3', 1, '7', '20'),

       ('Буль', 'Константин', 'Леонидович', 'M', 'Belarusian', 165, 70,
        (TO_DATE('15.04.2003', 'DD.MM.YYYY')), '375297258444', '220047', 'Belarus', 'Minsk', 'Minsk',
        'Minsk', 'ул. Нестерова', '54/2', 47, '7', '110'),

       ('Уехавшая', 'Елизавета', 'Валерьевна', 'F', 'Belarusian', 172, 51,
        (TO_DATE('27.09.2002', 'DD.MM.YYYY')), '375292003040', '220137', 'Belarus', 'Brest', 'Brest',
        'Brest', 'ул. Польская', '35', 121, '7', '20'),

       ('Котько', 'Пётр', 'Васильевич', 'M', 'Ukrainian', 175, 80,
        (TO_DATE('10.12.1999', 'DD.MM.YYYY')), '480291976500', '220137', 'Ukraine', 'Kyiv', 'Kyiv',
        'Kyiv', 'вул. Аеродромна', '14/2', 1, '5', '3');

