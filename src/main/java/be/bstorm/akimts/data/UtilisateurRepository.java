package be.bstorm.akimts.data;

import be.bstorm.akimts.models.entities.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository {

    Optional<Utilisateur> findByUsername(String username);

}
