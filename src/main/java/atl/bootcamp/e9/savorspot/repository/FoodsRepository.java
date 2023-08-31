package atl.bootcamp.e9.savorspot.repository;

import atl.bootcamp.e9.savorspot.models.Foods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodsRepository extends CrudRepository<Foods, Long> {

    List<Foods> findFoodsByFoodStall_Id(Long foodStallId);
    Optional<Foods> findByIdAndFoodStall_Id(Long foodsId, Long foodStallId);
}
