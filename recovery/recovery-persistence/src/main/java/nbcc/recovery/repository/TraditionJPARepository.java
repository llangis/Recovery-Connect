package nbcc.recovery.repository;

import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.entity.TraditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TraditionJPARepository extends JpaRepository<TraditionEntity, Long> {
    List<TraditionEntity> findByFellowshipTypeOrderByTraditionNumber(FellowshipType fellowshipType);
    boolean existsByTraditionNumberAndFellowshipType(Integer traditionNumber, FellowshipType fellowshipType);
}
