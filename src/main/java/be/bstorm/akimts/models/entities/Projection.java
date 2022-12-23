package be.bstorm.akimts.models.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Projection {

    private Long id;
    private LocalDateTime heure;
    private Salle salle;
    private Film film;

    public Projection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHeure() {
        return heure;
    }

    public void setHeure(LocalDateTime heure) {
        this.heure = heure;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Projection{" +
                "id=" + id +
                ", heure=" + heure +
                ", salle=" + salle +
                ", film=" + film +
                '}';
    }
}
