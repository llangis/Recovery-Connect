package nbcc.recovery.repository.api;

import nbcc.common.exception.ConcurrencyException;
import nbcc.recovery.dto.Step;
import nbcc.recovery.dto.FellowshipType;
import java.util.List;
import java.util.Optional;

public interface StepRepository {
    List<Step> getAll();
    List<Step> getByFellowship(FellowshipType type);
    Optional<Step> get(Long id);
    Step create(Step item);
    Step update(Step item) throws ConcurrencyException;
    void delete(Long id);
    boolean exists(Integer number, FellowshipType type);
}
