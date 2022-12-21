package be.bstorm.akimts.data;

import java.awt.image.TileObserver;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<TENTITY, TID> {

    List<TENTITY> getAll();

    Optional<TENTITY> getOne(TID id);

}
