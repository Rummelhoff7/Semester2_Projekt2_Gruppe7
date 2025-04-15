
DROP DATABASE IF EXISTS gaveListen;

CREATE DATABASE gaveListen;

USE gaveListen;

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(50) NOT NULL
);


-- Dummy data, Det er de samme objekter som bruges i InitData.
INSERT INTO user (name, password) VALUES
    ('Mads', '1234'),
    ('Admin', '1234'),
    ('Joakim', '1234'),
    ('Guney', '1234'),
    ('Thamied', '1234')
;


CREATE TABLE wishList(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT NOT NULL ,
                         name VARCHAR(50) NOT NULL ,
                         img VARCHAR(1000) ,
                         FOREIGN KEY (user_id)
                         REFERENCES user(id)
);

INSERT INTO wishList(user_id, name, img) VALUES
    (1, 'Gavertilmig', 'pikachu.jpg'),
    (2, 'Juleønsker', 'solbriller.jpg'),
    (3, 'Rejseønsker', 'sommerhus.jpg'),
    (4, 'Fylveøsnker', 'tivolitur.jpg'),
    (5, 'Katteønsker', 'ur.jpg'),
    (1, 'Hundeønsker', 'badebold.jpg'),
    (2, 'Frihedsønsker', 'bog.jpg'),
    (3, 'Fødselsdagønsker', 'chelsea.jpg'),
    (4, 'Danseønsker', 'diamant.jpg'),
    (5, 'Pjatteønsker', 'ferie.webp')
;

CREATE TABLE wish(
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     wishlist_id int NOT NULL ,
                     name VARCHAR(50) NOT NULL ,
                     description VARCHAR(100),
                     price DOUBLE,
                     img VARCHAR(1000),
                     FOREIGN KEY (wishlist_id)
                     REFERENCES wishList(id)
);

INSERT INTO wish(wishlist_id, name, description,price, img) VALUES
    (1,'Landskamps-billetter','En tur i Parken og se landsholdet spille for 2 personer',1150.00, 'landsholdet.jpg'),
    (2,'Badebold','Flot og rund',50, 'badebold.jpg'),
    (3,'Bog','Så man kan se klog ud',150.00, 'bog.jpg'),
    (4,'Diamant','I har da pengene til sådan en ikke?',10000.00, 'diamant.jpg'),
    (5,'Ferie','Jeg trænger til det',11500.00, 'ferie.webp'),
    (1,'Ferrari','WROOM WROOM',1000000, 'ferrari.jpg'),
    (2,'Hund','Den har fire poter på hvert sit ben',700, 'hund.jpg'),
    (3,'Kjole','Den skal være blod rød',1000.00, 'kjole.jpg'),
    (4,'Maleri','Hvad som helst er fint',1000000000.00, 'maleri.jpg'),
    (5,'Løbe kursus','Har du set hvor fed jeg er!?',1150.00, 'løbekursus.jpg')
;



CREATE TABLE wishidea(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         wishlist_id int NOT NULL ,
                         title VARCHAR(100),
                         description VARCHAR(500)
);

INSERT INTO wishidea(wishlist_id,title, description) VALUES
                                                                   (1,'Majokrukke', 'En krukke til din majonæse'),
                                                                   (2,'Remokrukke', 'En krukke til din Remo'),
                                                                   (3,'Ketchupkrukke', 'En krukke til din ketchup'),
                                                                   (4,'Sennepkrukke', 'En krukke til din sennep'),
                                                                   (5,'Sennepkrukke', 'En krukke til din sennep'),
                                                                   (6,'Majokrukke', 'En krukke til din majonæse'),
                                                                   (7,'Remokrukke', 'En krukke til din Remo'),
                                                                   (8, 'Ketchupkrukke', 'En krukke til din ketchup'),
                                                                   (9,'Sennepkrukke', 'En krukke til din sennep'),
                                                                   (10,'Sennepkrukke', 'En krukke til din sennep'),
                                                                   (2,'Majokrukke', 'En krukke til din majonæse'),
                                                                   (5,'Remokrukke', 'En krukke til din Remo'),
                                                                   (1,'Ketchupkrukke', 'En krukke til din ketchup'),
                                                                   (3,'Sennepkrukke', 'En krukke til din sennep'),
                                                                   (9,'Sennepkrukke', 'En krukke til din sennep')
;