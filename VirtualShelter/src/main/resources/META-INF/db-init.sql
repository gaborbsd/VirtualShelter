INSERT INTO address VALUES (1, "Petofi u. 16/A", "Szekszard", "Magyarorszag", "Tolna", 7100);
INSERT INTO address VALUES (2, "Petofi u. 16/A", "Kaposvar", "Magyarorszag", "Somogy", 7400);
INSERT INTO address VALUES (3, "Honved u. 12/B", "Pecs", "Magyarorszag", "Baranya", 7630);
INSERT INTO address VALUES (4, "Deak u. 16/B", "Kecskemet", "Magyarorszag", "Bacs-Kiskun", 6000);
INSERT INTO address VALUES (5, "Virag u. 23", "Kaposvar", "Magyarorszag", "Somogy", 7400);
INSERT INTO address VALUES (6, "Dozsa Gyorgy u. 33", "Kecskemet", "Magyarorszag", "Bacs-Kiskun", 6000);
INSERT INTO address VALUES (7, "Kolcsei Ferenc utca 4", "Miskolc", "Magyarorszag", "Borsod-Abauj-Zemplen", 3532);
INSERT INTO address VALUES (8, "Hargitai utca 2", "Szekszard", "Magyarorszag", "Tolna", 7100);
INSERT INTO address VALUES (9, "Iszak utca 1", "Kaposvar", "Magyarorszag", "Somogy", 7400);

INSERT INTO user VALUES (1, 34, "andras1@gmail.com", "Andras", "a", "+36301234567",1);
INSERT INTO user VALUES (2, 23, "bela2@gmail.com", "Bela", "b","+36201234567",2);
INSERT INTO user VALUES (3, 27, "cecil3@gmail.com", "Cecil", "c", "+36701234567",3);
INSERT INTO user VALUES (4, 27, "david4@gmail.com", "David", "d", "+36707654321",4);
INSERT INTO user VALUES (5, 12, "elemer5@gmail.com", "Elemer", "e", "+36706254193",5);
INSERT INTO user VALUES (6, 44, "ferenc6@gmail.com", "Ferenc", "f", "+36205291403",6);
INSERT INTO user VALUES (7, 36, "gabor7@gmail.com", "Gabor", "g", "+36304954720",7);

INSERT INTO sportevent VALUES (1, '2016-08-18 10:00:00', "tenisz", true,true, 4,6, 2, 1, "Tennis event 1" ,"TENNIS", 8, 1);
INSERT INTO sportevent VALUES (2, '2016-08-19 16:00:00', "tenisz", true,true, 4,6, 4, 1, "Tennis event 2", "TENNIS", 8, 1);
INSERT INTO sportevent VALUES (3, '2016-07-13 16:00:00', "foci", true,true, 8,8,11, 1, "football event","FOOTBALL", 9, 2);
INSERT INTO sportevent VALUES (4, '2016-09-15 16:00:00', "kezi", true,true, 3,7,8, 2, "kezikezijeee","HANDBALL", 9, 4);

INSERT INTO comment VALUES(1, '2016-10-18 10:44:00', "Labdat hozol?", 1, 4);
INSERT INTO comment VALUES(2, '2016-11-03 14:44:00', "Lehet esni fog", 3, 2);

INSERT INTO members_of_sportevent VALUES (1,1);
INSERT INTO members_of_sportevent VALUES (2,1);
INSERT INTO members_of_sportevent VALUES (3,2);
INSERT INTO members_of_sportevent VALUES (4,4);
INSERT INTO members_of_sportevent VALUES (4,1);

INSERT INTO friendship VALUES (1, true, 1, 2);
INSERT INTO friendship VALUES (2, true, 2, 1);
INSERT INTO friendship VALUES (3, true, 1, 4);
INSERT INTO friendship VALUES (4, false, 4, 1);
INSERT INTO friendship VALUES (5, false, 1, 5);
INSERT INTO friendship VALUES (6, true, 5, 1);
INSERT INTO friendship VALUES (7, true, 6, 7);
INSERT INTO friendship VALUES (8, true, 7, 6);

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

INSERT INTO friend_request_notification VALUES (1,"I would like to meet you",'2016-11-12 18:10:45',3,NULL,"SENT",1);
