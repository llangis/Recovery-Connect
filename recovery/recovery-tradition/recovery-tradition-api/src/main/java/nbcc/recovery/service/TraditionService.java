package nbcc.recovery.service;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.dto.FellowshipType;
import java.util.Collection;

public interface TraditionService {
    Result<Collection<Tradition>> getAll();
    Result<Collection<Tradition>> getByFellowship(FellowshipType type);
    ValidatedResult<Tradition> get(Long id);
    ValidatedResult<Tradition> create(Tradition item);
    ValidatedResult<Tradition> update(Tradition item);
    ValidatedResult<Void> delete(Long id);
}
