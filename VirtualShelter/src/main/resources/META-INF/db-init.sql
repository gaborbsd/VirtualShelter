INSERT INTO address VALUES (1, "Petofi u. 16/A", "Szekszard", "Magyarorszag", "Tolna", 7100);
INSERT INTO address VALUES (2, "Petofi u. 16/A", "Kaposvar", "Magyarorszag", "Somogy", 7400);
INSERT INTO address VALUES (3, "Honved u. 12/B", "Pecs", "Magyarorszag", "Baranya", 7630);
INSERT INTO address VALUES (4, "Deak u. 16/B", "Kecskemet", "Magyarorszag", "Bacs-Kiskun", 6000);
INSERT INTO address VALUES (5, "Virag u. 23", "Kaposvar", "Magyarorszag", "Somogy", 7400);
INSERT INTO address VALUES (6, "Dozsa Gyorgy u. 33", "Kecskemet", "Magyarorszag", "Bacs-Kiskun", 6000);
INSERT INTO address VALUES (7, "Kolcsei Ferenc utca 4", "Miskolc", "Magyarorszag", "Borsod-Abauj-Zemplen", 3532);
INSERT INTO address VALUES (8, "Hargitai utca 2", "Szekszard", "Magyarorszag", "Tolna", 7100);
INSERT INTO address VALUES (9, "Iszak utca 1", "Kaposvar", "Magyarorszag", "Somogy", 7400);

INSERT INTO user VALUES (1, 34, "andras1@gmail.com",true,false,"Andras vagyok",false,false, "Andras", "42cc48a7dc3274de66d5a7f4e27c43f728c9abd8", "+36301234567","00D4454C92F295EC2A47C153DCF78FAD",NULL,1);
INSERT INTO user VALUES (2, 23, "bela2@gmail.com",false,false,"Bela vagyok",false,false, "Bela", "7747a1955b6609dce457630a73b535f26895756f","+36201234567","86986EAE6B5308814EA045A65C622E9E",NULL,2);
INSERT INTO user VALUES (3, 27, "cecil3@gmail.com",true,false,"Cecil vagyok",false,false, "Cecil", "2170be9ab68f3917e411a94fe0f10869f4ad5429", "+36701234567","559AE3DB9DDBCD02E7B7A0012ED29CAB",NULL,3);
INSERT INTO user VALUES (4, 27, "david4@gmail.com",true,false,"David vagyok",false,false, "David", "dec55448cbd8acd06fce8c017d2f67989237ab2b", "+36707654321","536FABC95D5C6CC8634337C7ADA8BBE7",NULL,4);
INSERT INTO user VALUES (5, 12, "elemer5@gmail.com",false,false,"Elemer vagyok",false,false, "Elemer", "2fde8a81f558e798ea4eca651269505e67d6b460", "+36706254193","6DE750806F582756B772432FEA1512C8",NULL,5);
INSERT INTO user VALUES (6, 44, "ferenc6@gmail.com",true,true,"Ferenc vagyok",false,false, "Ferenc", "0535c92335763c29cfff14a3944d655641dffc3a", "+36205291403","1E63AD9C52FEDD735D3E1D461C92C74C","Ne karomkodjal az oldalon",6);
INSERT INTO user VALUES (7, 36, "gabor7@gmail.com",false,false,"Gabor vagyok",false,false, "Gabor", "926fdd0d52c83023f68b08f22296769ff09a57de", "+36304954720","A7E313F0000419D5887BE0916C003D36",NULL,7);
INSERT INTO user VALUES (8, 35, "admin@gmail.com",false,false, "Foadmin boss vagyok",true,false, "Admin", "b16cc3d9540dcc1c1991b72b13f500e4012e23ff", "+36204213735","6C911BC410E75AA07BC3AE127CE8498E",NULL,5)

INSERT INTO sportevent VALUES (1, '2016-08-18 10:00:00', "tenisz", true,true, 4,6, 2, 1, "Tennis event 1" ,"TENNIS", 8, 1);
INSERT INTO sportevent VALUES (2, '2016-08-19 16:00:00', "tenisz", true,true, 4,6, 4, 1, "Tennis event 2", "TENNIS", 8, 1);
INSERT INTO sportevent VALUES (3, '2016-07-13 16:00:00', "foci", true,true, 8,8,11, 1, "football event","FOOTBALL", 9, 2);
INSERT INTO sportevent VALUES (4, '2016-09-15 16:00:00', "kezi", true,true, 3,7,8, 2, "kezikezijeee","HANDBALL", 9, 4);
INSERT INTO sportevent VALUES (5, '2016-11-16 16:00:00', "focijeee", false,true, 1,10,3, 3, "focicsumicsa","FOOTBALL", 9, 4);

INSERT INTO comment VALUES(1, '2016-10-18 10:44:00', "Labdat hozol?", 1, 4);
INSERT INTO comment VALUES(2, '2016-11-03 14:44:00', "Lehet esni fog", 3, 2);

INSERT INTO members_of_sportevent VALUES (1,1);
INSERT INTO members_of_sportevent VALUES (2,1);
INSERT INTO members_of_sportevent VALUES (3,2);
INSERT INTO members_of_sportevent VALUES (4,4);
INSERT INTO members_of_sportevent VALUES (4,7);
INSERT INTO members_of_sportevent VALUES (5,1);
INSERT INTO members_of_sportevent VALUES (5,4);
INSERT INTO members_of_sportevent VALUES (5,6);

INSERT INTO friendship VALUES (1, true, true, 1, 2);
INSERT INTO friendship VALUES (2, true, true, 1, 4);
INSERT INTO friendship VALUES (3, false,true, 1, 5);
INSERT INTO friendship VALUES (4, true,true, 6, 7);

INSERT INTO conversation VALUES (1,true,"Mizu?",'2016-08-20 16:00:00',1,2);
INSERT INTO conversation VALUES (2,true,"Szeva",'2016-08-18 14:00:00',1,3);
INSERT INTO conversation VALUES (3,true,"Szia",'2016-08-21 14:00:00',4,1);
INSERT INTO conversation VALUES (4,true,"Szervusz",'2016-08-21 16:00:00',5,6);
INSERT INTO conversation VALUES (5,false,NULL,'2016-08-22 16:00:00',1,7);

INSERT INTO message VALUES (1,"Csa",'2016-08-18 10:00:00',1,1);
INSERT INTO message VALUES (2,"Csa",'2016-08-18 12:00:00',1,2);
INSERT INTO message VALUES (3,"Szeva",'2016-08-18 14:00:00',2,1);
INSERT INTO message VALUES (4,"Csovaz",'2016-08-18 15:00:00',3,4);
INSERT INTO message VALUES (5,"Hali",'2016-08-18 16:00:00',4,5);
INSERT INTO message VALUES (6,"Mizu?",'2016-08-20 16:00:00',1,1);
INSERT INTO message VALUES (7,"Szia",'2016-08-21 14:00:00',3,1);
INSERT INTO message VALUES (8,"Szervusz",'2016-08-21 16:00:00',4,6);

INSERT INTO friend_request_notification VALUES (1, false,'2016-11-12 18:10:45',3,1,NULL);
INSERT INTO friend_request_notification VALUES (2, false,'2016-11-12 18:10:45',1,6,NULL);
INSERT INTO event_simple_notification VALUES (3, false,'2016-11-13 18:10:45',1,4,"created a new event",4);
INSERT INTO event_request_notification VALUES (4, false,'2016-11-13 19:10:45',1,4,NULL,1);
INSERT INTO message_notification VALUES (5, false,'2016-11-15 19:10:45',1,2,1);
INSERT INTO event_rate_notification VALUES (6, false, '2016-11-17 16:00:00',1,4,5);
INSERT INTO event_rate_notification VALUES (7, false, '2016-11-17 16:00:00',4,4,5);
INSERT INTO event_rate_notification VALUES (8, false, '2016-11-17 16:00:00',6,4,5);

INSERT INTO rating VALUES (1, 1, "FOOTBALL", 3, 4);
INSERT INTO rating VALUES (2, 3, "TENNIS", 23, 1);
INSERT INTO rating VALUES (3, 0, "HANDBALL", 0, 1);
INSERT INTO rating VALUES (4, 1, "FOOTBALL", 5, 1);

INSERT INTO id_gen VALUES ("Notification",9);

