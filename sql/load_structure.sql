-- CREATE DATABASE cinema_manager;

USE cinema_manager;

CREATE TABLE cinema (
                        id INTEGER IDENTITY PRIMARY KEY,
                        nom VARCHAR(50) NOT NULL,
                        adresse_rue VARCHAR(50) NOT NULL,
                        adresse_numero INTEGER NOT NULL,
                        adresse_ville VARCHAR(50) NOT NULL,
                        adresse_cp INTEGER NOT NULL,
                        telephone VARCHAR(50),
);


CREATE TABLE equipement (
                            id INTEGER IDENTITY PRIMARY KEY,
                            nom VARCHAR(50) NOT NULL,
                            "description" VARCHAR(50)
);

CREATE TABLE salle (
                       id INTEGER IDENTITY PRIMARY KEY,
                       numero INTEGER NOT NULL,
                       capacite INTEGER NOT NULL CHECK (capacite > 0),
                       cinema_id INTEGER NOT NULL,
                       CONSTRAINT FK_salle_cinema FOREIGN KEY (cinema_id) REFERENCES cinema(id)
);

CREATE TABLE salle_equipement (
                                  equipement_id INTEGER NOT NULL,
                                  salle_id INTEGER NOT NULL,
                                  CONSTRAINT PK_salle_equipement PRIMARY KEY (equipement_id, salle_id),
                                  CONSTRAINT FK_salle_equipement_equipement FOREIGN KEY (equipement_id) REFERENCES equipement(id),
                                  CONSTRAINT FK_salle_equipement_salle FOREIGN KEY (salle_id) REFERENCES salle(id)
);

CREATE TABLE genre (
                       id INTEGER IDENTITY PRIMARY KEY,
                       nom VARCHAR(50) NOT NULL,
                       "description" VARCHAR(50)
);

CREATE TABLE realisateur (
                             id INTEGER IDENTITY PRIMARY KEY,
                             nom VARCHAR(50) NOT NULL,
                             alias VARCHAR(50)
);

CREATE TABLE rating (
                        id INTEGER IDENTITY PRIMARY KEY,
                        nom VARCHAR(50) NOT NULL,
                        "description" VARCHAR(50)
);

CREATE TABLE film (
                      id INTEGER IDENTITY PRIMARY KEY,
                      titre VARCHAR(50) NOT NULL,
                      duree INTEGER NOT NULL,
                      rating_id INTEGER NOT NULL,
                      CONSTRAINT FK_film_rating FOREIGN KEY (rating_id) REFERENCES rating(id)
);

CREATE TABLE film_realisateur (
                                  film_id INTEGER NOT NULL,
                                  realisateur_id INTEGER NOT NULL,
                                  CONSTRAINT PK_film_realisateur PRIMARY KEY (film_id, realisateur_id),
                                  CONSTRAINT FK_film_realisateur_film FOREIGN KEY (film_id) REFERENCES film(id),
                                  CONSTRAINT FK_film_realisateur_realisateur FOREIGN KEY (realisateur_id) REFERENCES realisateur(id)
);

CREATE TABLE film_genre (
                            film_id INTEGER NOT NULL,
                            genre_id INTEGER NOT NULL,
                            CONSTRAINT PK_film_genre PRIMARY KEY (film_id, genre_id),
                            CONSTRAINT FK_film_genre_film FOREIGN KEY (film_id) REFERENCES film(id),
                            CONSTRAINT FK_film_genre_genre FOREIGN KEY (genre_id) REFERENCES genre(id)
);

CREATE TABLE projection_heure (
                                  id INTEGER IDENTITY PRIMARY KEY,
                                  heure TIME NOT NULL
);

CREATE TABLE projection (
                            id INTEGER IDENTITY PRIMARY KEY,
                            heure_id INTEGER NOT NULL,
                            film_id INTEGER NOT NULL,
                            salle_id INTEGER NOT NULL,
                            "date" DATE NOT NULL,
                            CONSTRAINT FK_projection_heure FOREIGN KEY (heure_id) REFERENCES  projection_heure(id),
                            CONSTRAINT FK_projection_film FOREIGN KEY (film_id) REFERENCES  film(id),
                            CONSTRAINT FK_projection_salle FOREIGN KEY (salle_id) REFERENCES  salle(id)
);

CREATE TABLE projection_equipement (
                                       projection_id INTEGER NOT NULL,
                                       equipement_id INTEGER NOT NULL,
                                       CONSTRAINT PK_projection_equipement PRIMARY KEY (projection_id,equipement_id),
                                       CONSTRAINT FK_projection_equipement_projection FOREIGN KEY (projection_id) REFERENCES projection(id),
                                       CONSTRAINT FK_projection_equipement_equipement FOREIGN KEY (equipement_id) REFERENCES equipement(id),
);

CREATE TABLE utilisateur (
                             id INTEGER IDENTITY PRIMARY KEY,
                             username VARCHAR(50) NOT NULL,
                             "password" VARCHAR(50) NOT NULL,
                             nom VARCHAR(50) NOT NULL,
                             prenom VARCHAR(50) NOT NULL,
                             email VARCHAR(50),
                             telephone VARCHAR(50),
                             "role" VARCHAR(50) NOT NULL CHECK ("role" = 'ADMIN' OR "role" = 'CUSTOMER')
);

CREATE TABLE reservation (
                             id INTEGER IDENTITY PRIMARY KEY,
                             utilisateur_id INTEGER NOT NULL,
                             projection_id INTEGER NOT NULL,
                             prix DECIMAL NOT NULL,
                             type_ticket VARCHAR(50) NOT NULL,
                             CONSTRAINT FK_reservation_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id),
                             CONSTRAINT FK_reservation_projection FOREIGN KEY (projection_id) REFERENCES projection(id)
);
