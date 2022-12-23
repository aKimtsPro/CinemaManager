package be.bstorm.akimts.models.dto;

import be.bstorm.akimts.models.entities.Utilisateur;

public class UtilisateurDTO {

    private Long id;
    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String role;

    public UtilisateurDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static UtilisateurDTO map(Utilisateur utilisateur){

        if( utilisateur == null )
            return null;

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setUsername(utilisateur.getUsername());
        utilisateurDTO.setEmail( utilisateur.getEmail() );
        utilisateurDTO.setTelephone( utilisateur.getTelephone() );
        utilisateurDTO.setRole(utilisateur.getRole() );

        return utilisateurDTO;
    }

}
