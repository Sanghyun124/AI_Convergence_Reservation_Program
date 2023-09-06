package CSS.ReservationSystem.repository;

import CSS.ReservationSystem.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {

    Room findByid(Long id);
}
