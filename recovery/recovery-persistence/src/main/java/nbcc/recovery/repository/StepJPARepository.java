package nbcc.recovery.repository;

import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.entity.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StepJPARepository extends JpaRepository<StepEntity, Long> {
    List<StepEntity> findByFellowshipTypeOrderByStepNumber(FellowshipType fellowshipType);
    boolean existsByStepNumberAndFellowshipType(Integer stepNumber, FellowshipType fellowshipType);
}
