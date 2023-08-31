package atl.bootcamp.e9.savorspot.repository;

import atl.bootcamp.e9.savorspot.models.FoodStall;
import atl.bootcamp.e9.savorspot.models.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodStallRepository extends JpaRepository<FoodStall,Long> {

    Page<FoodStall> findByUserStatusNot(UserStatus userStatus, Pageable pageable);

    Optional<FoodStall> findByIdAndUserStatusNot(Long id, UserStatus userStatus);

}

