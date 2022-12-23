package be.bstorm.akimts.data.impl;

import be.bstorm.akimts.models.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DataConverter {
    static Film convertFilm(ResultSet rs) throws SQLException {
        Film film = new Film();

        film.setTitre( rs.getString("titre") );
        film.setDuree( rs.getInt("duree") );
        film.setRating( rs.getString("rating") );
        film.setId( rs.getLong("id") );

        return film;
    }

    static Realisateur convertReal(ResultSet rs) throws SQLException {
        Realisateur realisateur = new Realisateur();

        realisateur.setId( rs.getLong("id") );
        realisateur.setNom( rs.getString("nom") );
        realisateur.setAlias( rs.getString("alias") );

        return realisateur;
    }

    static Projection convertProjection(ResultSet rs) throws SQLException {

        Projection projection = new Projection();

        projection.setId(rs.getLong("id"));

        LocalDateTime heure = rs.getDate("date")
                .toLocalDate().atTime(
                        rs.getTime("heure").toLocalTime()
                );
        projection.setHeure( heure );

        Salle salle = new Salle();

        salle.setId( rs.getLong("salle_id") );
        salle.setCapacite( rs.getInt("salle_capacite") );
        salle.setNumero( rs.getInt("salle_numero") );

        Cinema cinema = new Cinema();

        cinema.setId( rs.getLong("cinema_id") );
        cinema.setNom( rs.getString("cinema_nom") );
        cinema.setAdresseRue( rs.getString("cinema_rue") );
        cinema.setAdresseNumero( rs.getInt("cinema_numero") );
        cinema.setAdresseVille( rs.getString("cinema_ville") );
        cinema.setAdresseCP( rs.getInt("cinema_cp") );
        cinema.setTelephone( rs.getString("cinema_tel") );

        salle.setCinema( cinema );
        projection.setSalle( salle );

        return projection;
    }

    static Salle convertSalle(ResultSet rs) throws SQLException {
        Salle salle = new Salle();

        salle.setId( rs.getLong("salle_id") );
        salle.setCapacite( rs.getInt("capacite") );
        salle.setNumero( rs.getInt("numero") );

        Cinema cinema = new Cinema();

        cinema.setId( rs.getLong("cinema_id") );
        cinema.setNom( rs.getString("cinema_nom") );
        cinema.setAdresseRue( rs.getString("cinema_rue") );
        cinema.setAdresseNumero( rs.getInt("cinema_numero") );
        cinema.setAdresseVille( rs.getString("cinema_ville") );
        cinema.setAdresseCP( rs.getInt("cinema_cp") );
        cinema.setTelephone( rs.getString("cinema_telephone") );

        salle.setCinema( cinema );

        return salle;
    }

    static Utilisateur convertUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId( rs.getLong( "id" ) );
        utilisateur.setUsername( rs.getString( "username" ) );
        utilisateur.setPassword( rs.getString("password") );
        utilisateur.setPrenom( rs.getString("prenom") );
        utilisateur.setNom( rs.getString("nom") );
        utilisateur.setEmail( rs.getString("email") );
        utilisateur.setTelephone( rs.getString("telephone") );
        utilisateur.setRole( rs.getString("role") );

        return utilisateur;
    }

    static Reservation convertReservation(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();

        reservation.setId( rs.getLong("id") );
        reservation.setPrix( rs.getDouble("prix") );
        reservation.setTypeTicket( rs.getString("type_ticket") );
        reservation.setProjectionId( rs.getLong("projection_id") );
        reservation.setUtilisateurId( rs.getLong("utilisateur_id") );

        return reservation;
    }
}
