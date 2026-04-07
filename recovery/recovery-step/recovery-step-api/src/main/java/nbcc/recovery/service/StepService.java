package nbcc.recovery.service;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Step;
import nbcc.recovery.dto.FellowshipType;
import java.util.Collection;

public interface StepService {
    Result<Collection<Step>> getAll();
    Result<Collection<Step>> getByFellowship(FellowshipType type);
    ValidatedResult<Step> get(Long id);
    ValidatedResult<Step> create(Step item);
    ValidatedResult<Step> update(Step item);
    ValidatedResult<Void> delete(Long id);
}
