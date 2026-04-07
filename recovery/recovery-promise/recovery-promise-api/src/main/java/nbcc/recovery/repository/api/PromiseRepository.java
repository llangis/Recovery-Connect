package nbcc.recovery.repository.api;

import nbcc.common.exception.ConcurrencyException;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.dto.FellowshipType;
import java.util.List;
import java.util.Optional;

public interface PromiseRepository {
    List<Promise> getAll();
    List<Promise> getByFellowship(FellowshipType type);
    Optional<Promise> get(Long id);
    Promise create(Promise item);
    Promise update(Promise item) throws ConcurrencyException;
    void delete(Long id);
    boolean exists(Integer number, FellowshipType type);
}
