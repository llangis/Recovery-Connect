package nbcc.recovery.client;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.dto.FellowshipType;
import java.util.Collection;

public interface PromiseClient {
    Result<Collection<Promise>> getAll();
    Result<Collection<Promise>> getByFellowship(FellowshipType type);
    ValidatedResult<Promise> get(Long id);
    ValidatedResult<Promise> create(Promise item);
    ValidatedResult<Promise> update(Promise item);
    ValidatedResult<Void> delete(Long id);
}
