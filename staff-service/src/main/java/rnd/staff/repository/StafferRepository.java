package rnd.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.staff.entity.Staffer;

public interface StafferRepository extends JpaRepository<Staffer, Long> {
}
