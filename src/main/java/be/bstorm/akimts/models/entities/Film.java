package be.bstorm.akimts.models;

import java.util.ArrayList;
import java.util.List;

public class Film {

    private Long id;
    private String titre;
    private int duree;
    private List<String> genre = new ArrayList<>();
    private List<Realisateur> realisateurs = new ArrayList<>();
    private String rating;

    public Film() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", duree=" + duree +
                ", genre=" + genre +
                ", realisateurs=" + realisateurs +
                ", rating='" + rating + '\'' +
                '}';
    }
}
