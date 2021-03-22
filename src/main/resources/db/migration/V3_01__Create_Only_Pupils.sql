-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
-- ТАБЛИЦА 'ШКОЛЬНИК'
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

DROP TABLE IF EXISTS pupils;
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