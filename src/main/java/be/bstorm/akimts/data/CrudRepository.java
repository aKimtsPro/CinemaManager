package be.bstorm.akimts.data;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<TENTITY, TID> extends Repository<TENTITY,TID> {

    List<TENTITY> getAll();

    Optional<TENTITY> getOne(TID id);

}
