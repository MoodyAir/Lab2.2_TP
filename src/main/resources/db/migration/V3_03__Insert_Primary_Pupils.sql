INSERT INTO pupils (surname, name, fathername, gender, nationality, height, weight,
                    birthday, phone_number, post_code, country, region, district,
                    city, street, building, flat, grade, school_id)

VALUES ('Горбач', 'Дмитрий', 'Николаевич', 'M', 'Belarusian', 182, 70,
        (TO_DATE('13.09.2001', 'DD.MM.YYYY')), '375291976500', '220137', 'Belarus', 'Minsk', 'Minsk',
        'Minsk', 'ул. Ангарская', '21', 1, '7', '80'),

       ('Врублевская', 'Екатерина', 'Александровна', 'F', 'Belarusian', 173, 50,
        (TO_DATE('29.07.2002', 'DD.MM.YYYY')), '375291972233', '220102', 'Belarus', 'Minsk', 'Minsk',
        'Dziarzhynsk', 'пер. Весенний', '23', NULL, '5', '1'),

       ('Доскоч', 'Роман', 'Дмитриевич', 'M', 'Belarusian', 130, 68,
        (TO_DATE('15.05.2002', 'DD.MM.YYYY')), '375295553535', '220047', 'Belarus', 'Mahilyow', 'Horki',
        'Horki', 'ул. Сельскохозяйственная', '3', 1, '5', '312'),

       ('Буль', 'Мартин', 'Леонидович', 'M', 'Belarusian', 165, 70,
        (TO_DATE('15.04.2003', 'DD.MM.YYYY')), '375297258444', '220047', 'Belarus', 'Minsk', 'Minsk',
        'Minsk', 'ул. Нестерова', '54/2', 47, '5', '1'),

       ('Коновалова', 'Мария', 'Валерьевна', 'F', 'Belarusian', 172, 51,
        (TO_DATE('27.09.2002', 'DD.MM.YYYY')), '375292003040', '220137', 'Belarus', 'Brest', 'Brest',
        'Brest', 'ул. Польская', '35', 121, '5', '5'),

       ('Котько', 'Пётр', 'Васильевич', 'M', 'Ukrainian', 175, 80,
        (TO_DATE('10.12.1999', 'DD.MM.YYYY')), '480291976500', '220137', 'Ukraine', 'Kyiv', 'Kyiv',
        'Kyiv', 'вул. Аеродромна', '14/2', 1, '5', '3');

