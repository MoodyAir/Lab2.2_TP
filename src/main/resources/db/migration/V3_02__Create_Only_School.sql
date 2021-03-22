-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
-- ТАБЛИЦА 'ШКОЛА'
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

DROP TABLE IF EXISTS school;
CREATE TABLE school(
                       id INT,
                       sch_name VARCHAR(50) NOT NULL,
                       sch_description VARCHAR(50),
                       UNIQUE(id)
);