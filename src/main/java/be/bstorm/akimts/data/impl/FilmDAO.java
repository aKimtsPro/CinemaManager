package be.bstorm.akimts.data.impl;

import be.bstorm.akimts.data.FilmRepository;
import be.bstorm.akimts.data.utils.ConnectionFactory;
import be.bstorm.akimts.models.entities.Film;
import be.bstorm.akimts.models.entities.Realisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmDAO implements FilmRepository {

    // region SINGLETON
    private static FilmDAO instance;
    public static FilmDAO getInstance(){
        return instance == null ? instance = new FilmDAO() : instance;
    }
    private FilmDAO(){}
    // endregion

    @Override
    public List<Film> getAll() {

        String sql = """
                SELECT f.id, titre, duree, r.nom AS rating
                FROM film f
                    JOIN rating r on r.id = f.rating_id
                """;


        try(
                Connection connection = ConnectionFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
        ){

            List<Film> films = new ArrayList<>();

            while (rs.next()){
                Film film = DataConverter.convertFilm(rs);

                film.setRealisateurs( findRealisateurs(film.getId(), connection) );
                film.setGenre( findGenre(film.getId(), connection) );

                films.add(film);
            }

            return films;

        }
        catch (SQLException ex) {
            throw new RuntimeException("error db: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Film> getOne(Long id) {

        String sql = """
                SELECT f.id, titre, duree, r.nom AS rating
                FROM film f
                    JOIN rating r on r.id = f.rating_id
                WHERE f.id = ?
                """;

        try(
            Connection connection = ConnectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setLong( 1, id );

            ResultSet rs = statement.executeQuery();
            Film film = null;

            if( rs.next() ){
                film = DataConverter.convertFilm(rs);
                film.setRealisateurs( findRealisateurs(id, connection) );
                film.setGenre( findGenre(id, connection) );
            }

            return Optional.ofNullable( film );

        }
        catch (SQLException ex){
            throw new RuntimeException("error db: " + ex.getMessage(), ex);
        }
    }

    private List<Realisateur> findRealisateurs(long filmId, Connection connection) throws SQLException {
        String realSql = """
                SELECT r.id [id], r.nom [nom], r.alias [alias]
                FROM film_realisateur fr
                    JOIN realisateur r on r.id = fr.realisateur_id
                WHERE film_id = ?
                """;

        List<Realisateur> realisateurs = new ArrayList<>();

        try ( PreparedStatement realStmt = connection.prepareStatement(realSql) ){
            realStmt.setLong(1, filmId);
            ResultSet realSet = realStmt.executeQuery();
            while(realSet.next())
                realisateurs.add(DataConverter.convertReal(realSet));
        }

        return realisateurs;
    }

    private List<String> findGenre(long filmId, Connection connection) throws SQLException {

        String genreSql = """
                SELECT nom
                FROM film_genre fg
                    JOIN genre g on g.id = fg.genre_id
                WHERE film_id = ?
                """;

        List<String> genres = new ArrayList<>();

        try ( PreparedStatement genreStmt = connection.prepareStatement(genreSql) ){
            genreStmt.setLong(1, filmId);
            ResultSet genreSet = genreStmt.executeQuery();
            while ( genreSet.next() )
                genres.add( genreSet.getString("nom") );
        }

        return genres;
    }

}
