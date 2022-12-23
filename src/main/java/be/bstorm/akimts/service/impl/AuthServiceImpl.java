package be.bstorm.akimts.service.impl;

import be.bstorm.akimts.data.impl.UtilisateurDAO;
import be.bstorm.akimts.exceptions.BadCredentialsException;
import be.bstorm.akimts.models.dto.UtilisateurDTO;
import be.bstorm.akimts.service.AuthService;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    // region SINGLETON
    private static AuthServiceImpl instance;
    public static AuthServiceImpl getInstance(){
        return instance == null ? instance = new AuthServiceImpl() : instance;
    }
    private AuthServiceImpl(){}
    // endregion

    private final UtilisateurDAO utilisateurDAO = UtilisateurDAO.getInstance();

    @Override
    public boolean validateCredentials(String username, String password) {
        return utilisateurDAO.findByUsername(username)
                .map( user -> user.getPassword().equals(password) )
                .orElse(false);
    }

    public Optional<UtilisateurDTO> findByUsername(String username){
        return utilisateurDAO.findByUsername(username)
                .map( UtilisateurDTO::map );
    }
}
