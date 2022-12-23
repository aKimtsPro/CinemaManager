package be.bstorm.akimts.data.impl;

import be.bstorm.akimts.data.ReservationRepository;
import be.bstorm.akimts.data.utils.ConnectionFactory;
import be.bstorm.akimts.models.entities.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO implements ReservationRepository {

    // region SINGLETON
    private static ReservationDAO instance;
    public static ReservationDAO getInstance(){
        return instance == null ? instance = new ReservationDAO() : instance;
    }
    private ReservationDAO(){}
    // endregion

    @Override
    public List<Reservation> getByUtilisateur(String username){

        String sql = """
                SELECT
                    r.id,
                    utilisateur_id,
                    projection_id,
                    prix,
                    type_ticket
                FROM reservation r
                    JOIN utilisateur u on u.id = r.utilisateur_id
                WHERE u.username = ?
                """;

        try(
            Connection connection = ConnectionFactory.createConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        ){

            stmt.setString( 1, username );
            ResultSet rs = stmt.executeQuery();
            List<Reservation> list = new ArrayList<>();

            while( rs.next() ){
                list.add( DataConverter.convertReservation(rs) );
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Reservation reservation) {

        String sql = """
                INSERT INTO reservation(utilisateur_id, projection_id, prix, type_ticket) VALUES
                (?,?,?,?)
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setLong(1,reservation.getUtilisateurId());
            statement.setLong(2,reservation.getProjectionId());
            statement.setDouble(3,reservation.getPrix());
            statement.setString(4,reservation.getTypeTicket());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
