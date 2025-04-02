DROP DATABASE IF EXISTS wishlist;

CREATE DATABASE wishlist;

USE wishlist;

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(50) NOT NULL
);


-- Dummy data, Det er de samme objekter som bruges i InitData.
INSERT INTO user (name, email, password) VALUES
    ('Mads', 'Mads@spillerbas', '1234');