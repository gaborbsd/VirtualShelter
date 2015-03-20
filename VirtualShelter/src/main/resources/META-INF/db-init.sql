DELETE FROM species;
DELETE FROM breed;
DELETE FROM address;
DELETE FROM animal;

INSERT INTO species VALUES (1, 'kutya');
INSERT INTO breed VALUES (1, 'német juhász', 1);
INSERT INTO breed VALUES (2, 'skót juhász', 1);
INSERT INTO breed VALUES (3, 'labrador', 1);


INSERT INTO address VALUES (6, 'Kelemen utca 3', 'Budapest', 'Magyarorszag', 47.497912, 19.040235, 'Pest', '-', 'Pest');

INSERT INTO animal VALUES (1, 'PERMANENT', CURDATE(), 'ADVERTISER',  "jo alvo", 45, "Bundas", "feregtelenito", 'MALE', true, 'YES',45, null, null, null);
INSERT INTO animal VALUES (2, 'BOTH', null, 'ADVERTISER', "rossz alvo", 50, "Cirmi", "nincs", 'FEMALE', false, 'NO',52, null, null, null);
INSERT INTO animal VALUES (3, 'PERMANENT', null, 'ADOPTIER', "jo alvo", 20, "Alfred", "nincs", 'MALE', false, 'PARTLY',53, null, null, null);

