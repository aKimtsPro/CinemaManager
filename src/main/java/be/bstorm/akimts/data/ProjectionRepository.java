package be.bstorm.akimts.data;

import be.bstorm.akimts.models.entities.Projection;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectionRepository extends CrudRepository<Projection, Long> {

    void createProjection(LocalDateTime heure, Long salle_id, Long film_id);

    List<Projection> getAllFuturProjectionForMovie(Long filmId);

}
