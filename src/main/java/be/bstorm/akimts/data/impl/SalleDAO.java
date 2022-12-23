package be.bstorm.akimts.data.impl;

import be.bstorm.akimts.data.SalleRepository;
import be.bstorm.akimts.data.utils.ConnectionFactory;
import be.bstorm.akimts.models.entities.Salle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalleDAO implements SalleRepository {

    // region SINGLETON
    private static SalleDAO instance;
    public static SalleDAO getInstance(){
        return instance == null ? instance = new SalleDAO() : instance;
    }
    private SalleDAO(){}
    // endregion
    @Override
    public List<Salle> getAll() {

        String sql = """
                SELECT
                    s.id AS salle_id, numero, capacite,
                    c.nom AS cinema_nom,
                    c.adresse_rue AS cinema_rue,
                    c.adresse_numero AS cinema_numero,
                    c.adresse_ville AS cinema_ville,
                    c.adresse_cp AS cinema_cp,
                    c.telephone AS cinema_telephone,
                    c.id AS cinema_id
                FROM salle s
                    JOIN cinema c on c.id = s.cinema_id
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            List<Salle> salles = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                salles.add( DataConverter.convertSalle( rs ) );
            }
            return salles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Salle> getAllFromCinema(long cinemaId) {
        String sql = """
                SELECT
                    s.id AS salle_id, numero, capacite,
                    c.nom AS cinema_nom,
                    c.adresse_rue AS cinema_rue,
                    c.adresse_numero AS cinema_numero,
                    c.adresse_ville AS cinema_ville,
                    c.adresse_cp AS cinema_cp,
                    c.telephone AS cinema_telephone,
                    c.id AS cinema_id
                FROM salle s
                    JOIN cinema c on c.id = s.cinema_id
                WHERE c.id = ?
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            List<Salle> salles = new ArrayList<>();

            stmt.setLong(1, cinemaId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                salles.add( DataConverter.convertSalle( rs ) );
            }
            return salles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Salle> getOne(Long id) {
        String sql = """
                SELECT
                    s.id AS salle_id, numero, capacite,
                    c.nom AS cinema_nom,
                    c.adresse_rue AS cinema_rue,
                    c.adresse_numero AS cinema_numero,
                    c.adresse_ville AS cinema_ville,
                    c.adresse_cp AS cinema_cp,
                    c.telephone AS cinema_telephone,
                    c.id AS cinema_id
                FROM salle s
                    JOIN cinema c on c.id = s.cinema_id
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return Optional.of( DataConverter.convertSalle(rs) );
            }
            else
                return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
