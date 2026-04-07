package nbcc.recovery.repository.api;

import nbcc.common.exception.ConcurrencyException;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.dto.FellowshipType;
import java.util.List;
import java.util.Optional;

public interface TraditionRepository {
    List<Tradition> getAll();
    List<Tradition> getByFellowship(FellowshipType type);
    Optional<Tradition> get(Long id);
    Tradition create(Tradition item);
    Tradition update(Tradition item) throws ConcurrencyException;
    void delete(Long id);
    boolean exists(Integer number, FellowshipType type);
}
