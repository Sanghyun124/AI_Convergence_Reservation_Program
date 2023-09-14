package CSS.ReservationSystem.repository;

import CSS.ReservationSystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByid(Long id);

    Optional<Member> findBystudentId(Integer studentId);
}
