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
(1,'Ur','Det er vintage og dyrt',20000,'ur.jpg'),
(1,'Bog','Det er en god klassisk roman',150,'bog.jpg'),
(1,'Chelsea','Frank Lampard Match worn',4500,'chelsea.jpg'),
(1,'Sommerhus','Dejligt sommerhus som ligger ud til vandet',1450000,'sommerhus.jpg'),
(1,'Pikachu','Bamse af den mest bedste pokemon',30,'pikachu.jpg'),
(1,'Bil','En rød Ferrari',500000,'ferrari.jpg'),
(1,'Diamant','En meget sjælden diamant fra Sydafrika',999999,'diamant.jpg'),
(1,'Kjole','Flot rød kjole',1450,'kjole.jpg'),
(1,'Maleri','Et maleri af mester Egon Schiele',300000,'maleri.jpg'),
(1,'Solbriller','Lækre Rayban-solbriller',250,'solbriller.jpg'),
(1,'Badebold','En badebold i flotte farver',20,'badebold.jpg'),
(1,'Labrador','En sød hundehvalp meget trofast',5000,'hund.jpg'),
(1,'Ferie','En varm ferie sydpå',20000,'ferie.webp'),
(1,'Løbekursus','Et kursus til at komme i god form',850,'løbekursus.jpg'),
(1,'Tivoli-tur','En dejlig tur i tivoli for 2 med turpas',750,'tivolitur.jpg'),
(1,'Landskamps-billetter','En tur i Parken og se landsholdet spille for 2 personer',1150,'landsholdet.jpg')




