package atl.bootcamp.e9.savorspot.repository;

import atl.bootcamp.e9.savorspot.models.FoodStall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodStallRepository extends JpaRepository<FoodStall,Long> {

}

