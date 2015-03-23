

INSERT INTO species VALUES (1, 'kutya');
INSERT INTO species VALUES (2, 'cica');
INSERT INTO species VALUES (3, 'maci');

INSERT INTO breed VALUES (1, 'labrador', 1);
INSERT INTO breed VALUES (2, 'hócipős', 2);
INSERT INTO breed VALUES (3, 'mormogó', 3);

INSERT INTO species_breed VALUES (1, 1);
INSERT INTO species_breed VALUES (2, 2);
INSERT INTO species_breed VALUES (3, 3);

INSERT INTO role VALUES (1, 'site-administrator');

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

INSERT INTO advertiser VALUES (1, 'Tothek62@hotmail.com', '70/543 1223', 'Istvan Toth', null, 1);
INSERT INTO advertiser VALUES (2, 'Arpad.Hodasz@gmail.com', '+36201234567', 'Hodász Árpád', null, 2);
INSERT INTO advertiser VALUES (3, 'Tunde.Manfai@gmail.com', '06305533228', 'Mánfai Tünde', '0682552992', 3);
INSERT INTO advertiser VALUES (4, 'hajduuzem@bihar.hu', null, 'Hajdu Favágó Zrt.', null, 4);
INSERT INTO advertiser VALUES (5, 'bikaver@egri.hu', '30/1454321', 'Legjobb Egri Borok Kft.', null, 5);
INSERT INTO advertiser VALUES (6, 'kecsegemet@kecske.com', '709933987', 'Kecskeleg Kft.', null, 6);

INSERT INTO user VALUES ('Kedves idős házaspár nagy kertes ház', 'csipella96', 1); 
INSERT INTO user VALUES ('Szeretek vadászni vadászkutyát keresek', 'asd123', 2);
INSERT INTO user VALUES ('Halakat tartok, tóparti otthonomban', 'skywalker24', 3);

INSERT INTO animal VALUES (1, 'PERMANENT', CURDATE(), 'ADVERTISER',  "jo alvo", 45, "Bundas", "feregtelenito", 'MALE', true, 'YES',45, null, null, null);
INSERT INTO animal VALUES (2, 'BOTH', null, 'ADVERTISER', "rossz alvo", 50, "Cirmi", "nincs", 'FEMALE', false, 'NO',52, null, null, null);
INSERT INTO animal VALUES (3, 'PERMANENT', null, 'ADOPTIER', "jo alvo", 20, "Alfred", "nincs", 'MALE', false, 'PARTLY',53, null, null, null);

