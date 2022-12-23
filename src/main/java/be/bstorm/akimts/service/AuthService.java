package be.bstorm.akimts.service;

import be.bstorm.akimts.models.dto.UtilisateurDTO;

import java.util.Optional;

public interface AuthService {

    boolean validateCredentials(String username, String password);
    Optional<UtilisateurDTO> findByUsername(String username);

}
