DROP DATABASE IF EXISTS gaveListen;

CREATE DATABASE gaveListen;

USE gaveListen;

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      password VARCHAR(50) NOT NULL
);


-- Dummy data, Det er de samme objekter som bruges i InitData.
INSERT INTO user (name, password) VALUES
    ('Mads', '1234');

CREATE TABLE wishList(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    img VARCHAR(1000)
);

INSERT INTO wishList(user_id, name, img) VALUES
    (2, 'Juleønsker', 'COOLPIC.png');

CREATE TABLE wish(
    id INT AUTO_INCREMENT PRIMARY KEY,
    wishlist_id int NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    description VARCHAR(100),
    price DOUBLE,
    img VARCHAR(1000)
);

INSERT INTO wish(wishlist_id, name, description,price, img) VALUES
    (2,'Ur','Den er lavet af guld og dyr',50000.00, 'ur.jpg')