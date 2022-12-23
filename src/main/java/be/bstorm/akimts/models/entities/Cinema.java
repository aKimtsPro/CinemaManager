package be.bstorm.akimts.models.entities;

public class Cinema {

    private Long id;
    private String nom;
    private String adresseRue;
    private int adresseNumero;
    private String adresseVille;
    private int adresseCP;
    private String telephone;

    public Cinema() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseRue() {
        return adresseRue;
    }

    public void setAdresseRue(String adresseRue) {
        this.adresseRue = adresseRue;
    }

    public int getAdresseNumero() {
        return adresseNumero;
    }

    public void setAdresseNumero(int adresseNumero) {
        this.adresseNumero = adresseNumero;
    }

    public String getAdresseVille() {
        return adresseVille;
    }

    public void setAdresseVille(String adresseVille) {
        this.adresseVille = adresseVille;
    }

    public int getAdresseCP() {
        return adresseCP;
    }

    public void setAdresseCP(int adresseCP) {
        this.adresseCP = adresseCP;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
