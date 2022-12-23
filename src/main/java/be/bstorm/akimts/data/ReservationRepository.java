package be.bstorm.akimts.data;

import be.bstorm.akimts.models.entities.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ReservationRepository extends Repository<Reservation, Long> {

    List<Reservation> getByUtilisateur(String username);

    void create(Reservation reservation);

}
