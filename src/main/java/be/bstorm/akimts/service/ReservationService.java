package be.bstorm.akimts.service;

import be.bstorm.akimts.models.dto.ReservationDTO;
import be.bstorm.akimts.models.form.ReservationForm;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getReservationOfUtilisateur(String username);

    void createReservation(ReservationForm form);

}
