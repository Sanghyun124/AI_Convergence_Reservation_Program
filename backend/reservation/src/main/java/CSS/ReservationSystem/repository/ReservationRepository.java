package CSS.ReservationSystem.repository;

import CSS.ReservationSystem.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAll();
}
