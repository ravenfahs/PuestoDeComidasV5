package atl.bootcamp.e9.savorspot.repository;

import atl.bootcamp.e9.savorspot.models.Reviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Reviews, Long> {

}
