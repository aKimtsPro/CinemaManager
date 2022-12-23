package be.bstorm.akimts.data;

import java.time.LocalTime;
import java.util.List;

public interface HoraireRepository {

    List<LocalTime> getHoraire();

}
