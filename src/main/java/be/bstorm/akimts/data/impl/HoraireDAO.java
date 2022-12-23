package be.bstorm.akimts.data.impl;

import be.bstorm.akimts.data.HoraireRepository;
import be.bstorm.akimts.data.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HoraireDAO implements HoraireRepository {

    // region SINGLETON
    private static HoraireDAO instance;
    public static HoraireDAO getInstance(){
        return instance == null ? instance = new HoraireDAO() : instance;
    }
    private HoraireDAO(){}
    // endregion
    @Override
    public List<LocalTime> getHoraire() {
        String sql = """
                SELECT DISTINCT heure
                FROM projection_heure
                """;

        try (
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ){

            List<LocalTime> list = new ArrayList<>();

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add( rs.getTime("heure").toLocalTime() );
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
