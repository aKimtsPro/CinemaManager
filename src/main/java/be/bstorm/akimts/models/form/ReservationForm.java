package be.bstorm.akimts.models.form;

public class ReservationForm {

    private Long projectionId;
    private Long utilisateurId;

    public ReservationForm() {
    }

    public Long getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Long projectionId) {
        this.projectionId = projectionId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
