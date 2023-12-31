package CSS.ReservationSystem.repository;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAll();

    List<Reservation> findAllByDate(LocalDate date);

    List<Reservation> findAllByMember(Member member);

    List<Reservation> findAllByDateAndRoom(LocalDate date, Room room);

    List<Reservation> findAllByMemberAndDateBetween(Member member,LocalDate start, LocalDate end);
}
