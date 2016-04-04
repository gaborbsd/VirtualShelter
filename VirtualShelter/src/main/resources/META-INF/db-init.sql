SET FOREIGN_KEY_CHECKS = 0;


INSERT INTO species VALUES (1, 'kutya');
INSERT INTO species VALUES (2, 'cica');
INSERT INTO species VALUES (3, 'medve');

INSERT INTO breed VALUES (1, 'labrador');
INSERT INTO breed VALUES (2, 'nemet juhasz');
INSERT INTO breed VALUES (3, 'vizsla');
INSERT INTO breed VALUES (4, 'boxer');
INSERT INTO breed VALUES (5, 'hocipos');
INSERT INTO breed VALUES (6, 'sziami');
INSERT INTO breed VALUES (7, 'mormogo');
INSERT INTO breed VALUES (8, 'barna');


INSERT INTO species_breed VALUES (1, 1);
INSERT INTO species_breed VALUES (1, 2);
INSERT INTO species_breed VALUES (1, 3);
INSERT INTO species_breed VALUES (1, 4);
INSERT INTO species_breed VALUES (2, 5);
INSERT INTO species_breed VALUES (2, 6);
INSERT INTO species_breed VALUES (3, 7);
INSERT INTO species_breed VALUES (3, 8);

INSERT INTO handicap VALUES (1, 'vak');
INSERT INTO handicap VALUES (2, 'santa');
INSERT INTO handicap VALUES (3, 'suket');

INSERT INTO disease VALUES (1, 'bolhas');
INSERT INTO disease VALUES (2, 'nathas');
INSERT INTO disease VALUES (3, 'lazas');

INSERT INTO address VALUES (1, 'Kelemen utca 3', 'Budapest', 'Magyarorszag', 47.497912, 19.040235, 'Pest', '-', 1144);
INSERT INTO address VALUES (2, 'Újlak koz 12', 'Budapest', 'Magyarorszag', 47.349624, 18.334563, 'Pest', '-', 1173);
INSERT INTO address VALUES (3, 'Vecsei Karoly utca 4', 'Siofok', 'Magyarorszag', 45.423586, 17.742423, 'Somogy', '-', 8600);
INSERT INTO address VALUES (4, 'Pacsirta utca 15', 'Debrecen', 'Magyarorszag', 48.897912, 23.112345, 'Hajdú-Bihar', '-', 4029);
INSERT INTO address VALUES (5, 'Turk Frigyes utca 43', 'Eger', 'Magyarorszag', 48.369624, 21.427563, 'Heves', '-', 3300);
INSERT INTO address VALUES (6, 'Izsaki út 60', 'Kecskemet', 'Magyarorszag', 46.233586, 19.346723, 'Bacs-Kiskun', '-', 6000);

INSERT INTO advertiser VALUES (1, 'Tothek62@hotmail.com', '+365431223', 'Istvan Toth', NULL, 1, NULL);
INSERT INTO advertiser VALUES (2, 'Arpad.Hodasz@gmail.com', '+36201234567', 'Hodasz arpad', NULL, 2, NULL);
INSERT INTO advertiser VALUES (3, 'Tunde.Manfai@gmail.com', '+36305533228', 'Manfai Tunde', '003682552992', 3, NULL);
INSERT INTO advertiser VALUES (4, 'hajduuzem@bihar.hu', NULL, 'Hajdu Favago Zrt.', NULL, 4, NULL);
INSERT INTO advertiser VALUES (5, 'bikaver@egri.hu', '0036301454321', 'Legjobb Egri Borok Kft.', NULL, 5, NULL);
INSERT INTO advertiser VALUES (6, 'kecsegemet@kecske.com', '0036709933987', 'Kecskeleg Kft.', NULL, 6, NULL);
INSERT INTO advertiser VALUES (7, 'user@user.com', '+365441223', 'User User', NULL, 1, NULL);

INSERT INTO user VALUES ('Kedves idos hazaspar nagy kertes haz', '$2a$10$UWJlPFOqg8BrPWYvjFxaUOmHsv2FgBNdsN689eUGx8d4Ykst9D4p2', 1); -- password: csipella96
INSERT INTO user VALUES ('Szeretek vadaszni vadaszkutyat keresek', '$2a$10$6QcM1PK/Ayv4x3CbngOYQ.ncZQ0euy1eI7LQq6dIryG0BOym3l2H6', 2); -- password: asd123
INSERT INTO user VALUES ('Halakat tartok, toparti otthonomban', '$2a$10$xltIOZ.vMO0/zYqdiYljb.TwdzWwirEKou1QBZlYMuv/hyJ3I06sy', 3); -- password: skywalker24
INSERT INTO user VALUES ('user', '$2a$10$OGAmcwSQdw3BfzwcAghdSujvb0FUd1C3tH/kXppUM4s9n29kKfaRu', 7); -- password: user

INSERT INTO institution VALUES (NULL, '17136433-44432345-00000000', '235732461', 4, 1);
INSERT INTO institution VALUES (NULL, '43422643-66356353-63452452', '562452348', 5, 2);
INSERT INTO institution VALUES (NULL, '87654375-53453452-24563462', '54262452', 6, 3);

INSERT INTO animal VALUES (1, 'PERMANENT', DATE_SUB(NOW(), INTERVAL 2 YEAR), 'ADOPTER', 'jo alvo', 20, 'Alfred', 'nincs', 'MALE', FALSE, 'PARTLY', 53, 1, 1, NULL, 1);
INSERT INTO animal VALUES (2, 'BOTH', DATE_SUB(NOW(), INTERVAL 43 DAY), 'ADVERTISER', 'rossz alvo', 50, 'Cirmi', 'nincs', 'FEMALE', FALSE, 'NO', 52, 2, 6, NULL, 2);
INSERT INTO animal VALUES (3, 'PERMANENT', DATE_SUB(NOW(), INTERVAL 17 MONTH), 'ADVERTISER', 'jo alvo', 45, 'Bundas', 'feregtelenito', 'MALE', TRUE, 'YES', 45, 5, 8, NULL, 3);

INSERT INTO advertisement VALUES (1, NOW(), 'ez az 1. hirdetes', 'FIRST', 1, 1);
INSERT INTO advertisement VALUES (2, DATE_SUB(NOW(), INTERVAL 30 DAY), 'ez a 2. hirdetes', 'SECOND', 2, 2);
INSERT INTO advertisement VALUES (3, DATE_SUB(NOW(), INTERVAL 13 DAY), 'ez a 3. hirdetes', 'THIRD', 5, 3);

INSERT INTO animal_disease VALUES (1, 1);

INSERT INTO animal_handicap VALUES (2, 2);

INSERT INTO user_roles VALUES (1, 'site-administrator');

INSERT INTO institution_user VALUES (4, 1);
INSERT INTO institution_user VALUES (5, 2);
INSERT INTO institution_user VALUES (5, 3);

SET FOREIGN_KEY_CHECKS = 1;

