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
    (1,'Landskamps-billetter','En tur i Parken og se landsholdet spille for 2 personer',1150.00, 'landsholdet.jpg'),
(1,'YEAawdawdaw','En tur i Parken og se landsholdet spille for 2 personer',1150.00, 'badebold.jpg'),
(1,'Landskamps-billetter','En tur i Parken og se landsholdet spille for 2 personer',1150.00, 'landsholdet.jpg'),
(1,'Landskamps-billetter','En tur i Parken og se landsholdet spille for 2 personer',1150.00, 'landsholdet.jpg'),
(1,'YEA','En tur i Parken og se landsholdet spille for 2 personer',1150.00, 'landsholdet.jpg');



CREATE TABLE wishidea(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         wishlist_id int NOT NULL ,
                         user_id int NOT NULL ,
                         title VARCHAR(100),
                         description VARCHAR(500)
);

INSERT INTO wishidea(wishlist_id, user_id, title, description) VALUES
                                                                   (1, 1, 'Majokrukke', 'En krukke til din majonæse'),
                                                                   (1, 1, 'Remokrukke', 'En krukke til din Remo'),
                                                                   (1, 1, 'Ketchupkrukke', 'En krukke til din ketchup'),
                                                                   (1, 1, 'Sennepkrukke', 'En krukke til din sennep')

;

