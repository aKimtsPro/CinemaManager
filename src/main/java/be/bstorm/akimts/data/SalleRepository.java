package be.bstorm.akimts.data;

import be.bstorm.akimts.models.entities.Salle;

import java.util.List;

public interface SalleRepository extends CrudRepository<Salle, Long>{

    List<Salle> getAllFromCinema(long cinemaId);

}
