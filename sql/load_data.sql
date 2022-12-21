USE cinema_manager;

INSERT INTO cinema(nom, adresse_rue, adresse_numero, adresse_ville, adresse_cp, telephone) VALUES
    ('EL CINEMAO', 'rue de la bobine', 1, 'New Gray England', 3010, NULL);

INSERT INTO salle(numero, capacite, cinema_id) VALUES
   (1, 200, 1),
   (2, 300, 1),
   (3, 50, 1),
   (4, 300, 1);

INSERT INTO equipement (nom, "description") VALUES
    ('3D', 'avec lunette :('),
    ('IMAX', 'bon son :)');

INSERT INTO salle_equipement(equipement_id, salle_id) VALUES
    (1,1),
    (2,1),
    (2,2),
    (2,3),
    (1,4),
    (2,4);

INSERT INTO realisateur(nom) VALUES
    ('Park Chan-wook'),
    ('Hideaki Ano'),
    ('David Lowery');

INSERT INTO rating(nom) VALUES
    ('6'),
    ('9'),
    ('12'),
    ('14'),
    ('16'),
    ('18'),
    ('tous');

INSERT INTO film(titre, duree, rating_id) VALUES
    ('Old Boy', 120, 5),
    ('The End of Evangelion', 87, 4),
    ('Green Knight', 130, 3);

INSERT INTO film_realisateur(film_id, realisateur_id) VALUES
    (1,1),
    (2,2),
    (3,3);

INSERT INTO genre(nom) VALUES
    ('fantastique'),
    ('drame'),
    ('action'),
    ('aventure'),
    ('comedie'),
    ('thriller'),
    ('horreur'),
    ('romance'),
    ('animation'),
    ('alternatif');

INSERT INTO film_genre (film_id, genre_id) VALUES
    (1,2),
    (1,3),
    (1,6),
    (2,2),
    (2,3),
    (2,9),
    (3,1),
    (3,4),
    (3,10);

INSERT INTO projection_heure(heure) VALUES
    ('14:30'),
    ('17:00'),
    ('19:30'),
    ('22:00');

INSERT INTO projection (heure_id, film_id, salle_id, "date") VALUES
    (1, 1, 3, '2022-12-25'),
    (2, 1, 2, '2022-12-25'),
    (3, 1, 1, '2022-12-25'),
    (4, 1, 3, '2022-12-25'),
    (2, 2, 1, '2022-12-25'),
    (3, 2, 3, '2022-12-25'),
    (3, 3, 2, '2022-12-25'),
    (4, 3, 3, '2022-12-25');

INSERT INTO projection_equipement (projection_id, equipement_id) VALUES
    (1,2),
    (2,2),
    (3,1),
    (3,2),
    (4,2),
    (5,1),
    (5,2),
    (6,2),
    (7,2),
    (8,2);

INSERT INTO utilisateur(username, "password", nom, prenom, email, telephone, "role") VALUES
    ('user', 'pass', 'Luc', 'Lussere', 'l.lussere@email.cine', NULL, 'CUSTOMER'),
    ('admin', 'pass', 'Marie', 'De La Demine', 'marie.dldemine@email.cine', NULL, 'ADMIN');

INSERT INTO reservation (utilisateur_id, projection_id, prix, type_ticket) VALUES
    (1, 3, 10, 'ETUDIANT'),
    (1, 5, 10, 'ETUDIANT'),
    (1, 8, 5, 'ETUDIANT');
