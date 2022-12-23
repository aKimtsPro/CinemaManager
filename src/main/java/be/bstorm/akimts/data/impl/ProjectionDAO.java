package be.bstorm.akimts.data.impl;

import be.bstorm.akimts.data.ProjectionRepository;
import be.bstorm.akimts.data.utils.ConnectionFactory;
import be.bstorm.akimts.models.entities.Projection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ProjectionDAO implements ProjectionRepository {

    // region SINGLETON
    private static ProjectionDAO instance;
    public static ProjectionDAO getInstance(){
        return instance == null ? instance = new ProjectionDAO() : instance;
    }
    private ProjectionDAO(){}
    // endregion
    private final FilmDAO filmDAO = FilmDAO.getInstance();

    @Override
    public List<Projection> getAll() {

        String sql = """
                SELECT
                    p.id AS id,
                    ph.heure AS heure,
                    "date",
                    s.id AS salle_id,
                    s.numero AS salle_numero,
                    s.capacite AS salle_capacite,
                    c.id AS cinema_id,
                    c.nom AS cinema_nom,
                    c.adresse_rue AS cinema_rue,
                    c.adresse_numero AS cinema_numero,
                    c.adresse_ville AS cinema_ville,
                    c.adresse_cp AS cinema_cp,
                    c.telephone AS cinema_tel,
                    film_id
                FROM projection p
                    JOIN salle s on s.id = p.salle_id
                    JOIN cinema c on s.cinema_id = c.id
                    JOIN projection_heure ph on p.heure_id = ph.id
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {

            List<Projection> list = new ArrayList<>();

            while (rs.next()){
                Projection projection = DataConverter.convertProjection( rs );
                projection.setFilm(
                        filmDAO.getOne(rs.getLong("film_id")).orElseThrow()
                );
                list.add( projection );
            }

            return list;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Projection> getOne(Long id) {
        String sql = """
                SELECT
                    p.id AS id,
                    ph.heure AS heure,
                    "date",
                    s.id AS salle_id,
                    s.numero AS salle_numero,
                    s.capacite AS salle_capacite,
                    c.id AS cinema_id,
                    c.nom AS cinema_nom,
                    c.adresse_rue AS cinema_rue,
                    c.adresse_numero AS cinema_numero,
                    c.adresse_ville AS cinema_ville,
                    c.adresse_cp AS cinema_cp,
                    c.telephone AS cinema_tel,
                    film_id
                FROM projection p
                    JOIN salle s on s.id = p.salle_id
                    JOIN cinema c on s.cinema_id = c.id
                    JOIN projection_heure ph on p.heure_id = ph.id
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {


            if (rs.next()){
                Projection projection = DataConverter.convertProjection( rs );
                projection.setFilm(
                        filmDAO.getOne(rs.getLong("film_id")).orElseThrow()
                );
                return Optional.of( projection );
            }
            else
                return Optional.empty();

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createProjection(LocalDateTime heure, Long salle_id, Long film_id) {
        String sqlHeure = """
                    SELECT id
                    FROM projection_heure
                    WHERE heure = CAST( ? AS TIME )
                    """;

        String sql = """
                INSERT INTO projection(heure_id, "date", film_id, salle_id) VALUES
                    (?, ?, ?, ?)
                """;

        try(
            Connection connection = ConnectionFactory.createConnection();
            PreparedStatement heureStmt = connection.prepareStatement(sqlHeure);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            heureStmt.setTime( 1, Time.valueOf( heure.toLocalTime() ) );
            ResultSet rs = heureStmt.executeQuery();

            if( rs.next() ){
                statement.setLong( 1, rs.getLong("id") );
                statement.setDate( 2, Date.valueOf( heure.toLocalDate() ) );
                statement.setLong( 3, film_id );
                statement.setLong( 4, salle_id );

                statement.executeUpdate();
            }
            else {
                throw new IllegalArgumentException();
            }


        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public List<Projection> getAllFuturProjectionForMovie(Long filmId) {
        String sql = """
                SELECT
                    p.id AS id,
                    ph.heure AS heure,
                    "date",
                    s.id AS salle_id,
                    s.numero AS salle_numero,
                    s.capacite AS salle_capacite,
                    c.id AS cinema_id,
                    c.nom AS cinema_nom,
                    c.adresse_rue AS cinema_rue,
                    c.adresse_numero AS cinema_numero,
                    c.adresse_ville AS cinema_ville,
                    c.adresse_cp AS cinema_cp,
                    c.telephone AS cinema_tel,
                    film_id
                FROM projection p
                    JOIN salle s on s.id = p.salle_id
                    JOIN cinema c on s.cinema_id = c.id
                    JOIN projection_heure ph on p.heure_id = ph.id
                WHERE p.date >= cast( GETDATE() as DATE ) AND film_id = ?
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong( 1, filmId );

            ResultSet rs = stmt.executeQuery();
            List<Projection> list = new ArrayList<>();

            while (rs.next()){
                Projection projection = DataConverter.convertProjection( rs );
                projection.setFilm(
                        filmDAO.getOne(rs.getLong("film_id")).orElseThrow()
                );
                list.add( projection );
            }

            rs.close();
            return list;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
