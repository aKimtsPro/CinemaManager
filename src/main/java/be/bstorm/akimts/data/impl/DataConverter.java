package be.bstorm.akimts.data.utils;

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
}
