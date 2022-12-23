package be.bstorm.akimts.models.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private Long id;
    private String titreFilm;
    private LocalDateTime heure;
    private String type;
    private double prix;

    public ReservationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public LocalDateTime getHeure() {
        return heure;
    }

    public void setHeure(LocalDateTime heure) {
        this.heure = heure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", titreFilm='" + titreFilm + '\'' +
                ", heure=" + heure +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                '}';
    }
}
