package CSS.ReservationSystem.repository;

import CSS.ReservationSystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByid(Long id);
}
