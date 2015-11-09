SET FOREIGN_KEY_CHECKS=0;


INSERT INTO species VALUES (1, 'kutya');
INSERT INTO species VALUES (2, 'cica');
INSERT INTO species VALUES (3, 'maci');

INSERT INTO breed VALUES (1, 'labrador', 1);
INSERT INTO breed VALUES (2, 'hócipős', 2);
INSERT INTO breed VALUES (3, 'mormogó', 3);

--INSERT INTO species_breed VALUES (1, 1);
--INSERT INTO species_breed VALUES (2, 2);
--INSERT INTO species_breed VALUES (3, 3);

INSERT INTO handicap VALUES (1, 'vak');
INSERT INTO handicap VALUES (2, 'sánta');
INSERT INTO handicap VALUES (3, 'süket');

INSERT INTO disease VALUES (1, 'bolhás');
INSERT INTO disease VALUES (2, 'náthás');
INSERT INTO disease VALUES (3, 'lázas');

INSERT INTO address VALUES (1, 'Kelemen utca 3', 'Budapest', 'Magyarorszag', 47.497912, 19.040235, 'Pest', '-', 1144);
INSERT INTO address VALUES (2, 'Újlak köz 12', 'Budapest', 'Magyarorszag', 47.349624, 18.334563, 'Pest', '-', 1173);
INSERT INTO address VALUES (3, 'Vécsei Károly utca 4', 'Siófok', 'Magyarorszag', 45.423586, 17.742423, 'Somogy', '-', 8600);
INSERT INTO address VALUES (4, 'Pacsirta utca 15', 'Debrecen', 'Magyarorszag', 48.897912, 23.112345, 'Hajdú-Bihar', '-', 4029);
INSERT INTO address VALUES (5, 'Türk Frigyes utca 43', 'Eger', 'Magyarorszag', 48.369624, 21.427563, 'Heves', '-', 3300);
INSERT INTO address VALUES (6, 'Izsáki út 60', 'Kecskemét', 'Magyarorszag', 46.233586, 19.346723, 'Bács-Kiskun', '-', 6000);

INSERT INTO advertiser VALUES (1, 'Tothek62@hotmail.com', '+365431223', 'Istvan Toth', null, 1, null);
INSERT INTO advertiser VALUES (2, 'Arpad.Hodasz@gmail.com', '+36201234567', 'Hodász Árpád', null, 2, null);
INSERT INTO advertiser VALUES (3, 'Tunde.Manfai@gmail.com', '+36305533228', 'Mánfai Tünde', '003682552992', 3, null);
INSERT INTO advertiser VALUES (4, 'hajduuzem@bihar.hu', null, 'Hajdu Favágó Zrt.', null, 4, null);
INSERT INTO advertiser VALUES (5, 'bikaver@egri.hu', '0036301454321', 'Legjobb Egri Borok Kft.', null, 5, null);
INSERT INTO advertiser VALUES (6, 'kecsegemet@kecske.com', '0036709933987', 'Kecskeleg Kft.', null, 6, null);

INSERT INTO user VALUES ('Kedves idős házaspár nagy kertes ház', 'csipella96', 1); 
INSERT INTO user VALUES ('Szeretek vadászni vadászkutyát keresek', 'asd123', 2);
INSERT INTO user VALUES ('Halakat tartok, tóparti otthonomban', 'skywalker24', 3);
 
INSERT INTO institution VALUES (null ,'17136433-44432345-00000000', '235732461', 4,1); 
INSERT INTO institution VALUES (null ,'43422643-66356353-63452452', '562452348', 5,2);
INSERT INTO institution VALUES (null ,'87654375-53453452-24563462', '54262452', 6,3);

INSERT INTO animal VALUES (1, 'PERMANENT',DATE_SUB(NOW(), INTERVAL 2 YEAR), 'ADOPTER', 'jo alvo', 20, 'Alfred', 'nincs', 'MALE', false, 'PARTLY',53, 1, 1, null);
INSERT INTO animal VALUES (2, 'BOTH', DATE_SUB(NOW(), INTERVAL 43 DAY), 'ADVERTISER', 'rossz alvo', 50, 'Cirmi', 'nincs', 'FEMALE', false, 'NO',52, 2, 2, null);
INSERT INTO animal VALUES (3, 'PERMANENT', DATE_SUB(NOW(), INTERVAL 17 MONTH), 'ADVERTISER',  'jo alvo', 45, 'Bundas', 'feregtelenito', 'MALE', true, 'YES',45, 5, 3, null);

INSERT INTO advertisement VALUES (1 , NOW(), 1, 1); 
INSERT INTO advertisement VALUES (2 , DATE_SUB(NOW(), INTERVAL 30 DAY), 2, 2);
INSERT INTO advertisement VALUES (3 , DATE_SUB(NOW(), INTERVAL 13 DAY), 5, 3);

INSERT INTO animal_disease VALUES (1, 1);

INSERT INTO animal_handicap VALUES (2, 2);

INSERT INTO user_roles VALUES (1, 'site-administrator');

INSERT INTO institution_user VALUES (4,1);
INSERT INTO institution_user VALUES (5,2);
INSERT INTO institution_user VALUES (5,3);

SET FOREIGN_KEY_CHECKS=1;

