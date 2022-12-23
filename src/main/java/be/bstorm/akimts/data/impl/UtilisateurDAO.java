package be.bstorm.akimts.data.impl;

import be.bstorm.akimts.data.UtilisateurRepository;
import be.bstorm.akimts.data.utils.ConnectionFactory;
import be.bstorm.akimts.models.entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UtilisateurDAO implements UtilisateurRepository {

    // region SINGLETON
    private static UtilisateurDAO instance;
    public static UtilisateurDAO getInstance(){
        return instance == null ? instance = new UtilisateurDAO() : instance;
    }
    private UtilisateurDAO(){}
    // endregion

    @Override
    public Optional<Utilisateur> findByUsername(String username) {
        String sql= """
                SELECT *
                FROM utilisateur
                WHERE username = ?
                """;

        try(
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {

            stmt.setString( 1, username );

            ResultSet rs = stmt.executeQuery();
            if( rs.next() )
                return Optional.of( DataConverter.convertUtilisateur(rs) );
            else
                return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
