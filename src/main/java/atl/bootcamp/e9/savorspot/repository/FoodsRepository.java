package atl.bootcamp.e9.savorspot.repository;

import atl.bootcamp.e9.savorspot.models.Foods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodsRepository extends CrudRepository<Foods, Long> {
}
