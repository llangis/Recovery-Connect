package nbcc.recovery.repository;

import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.entity.PromiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PromiseJPARepository extends JpaRepository<PromiseEntity, Long> {
    List<PromiseEntity> findByFellowshipTypeOrderByPromiseNumber(FellowshipType fellowshipType);
    boolean existsByPromiseNumberAndFellowshipType(Integer promiseNumber, FellowshipType fellowshipType);
}
