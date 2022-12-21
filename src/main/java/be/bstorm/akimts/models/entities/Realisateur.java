package be.bstorm.akimts.models;

public class Realisateur {

    private Long id;
    private String nom;
    private String alias;

    public Realisateur() {
    }

    public Realisateur(Long id, String nom, String alias) {
        this.id = id;
        this.nom = nom;
        this.alias = alias;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Realisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
