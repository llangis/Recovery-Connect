package nbcc.recovery.service;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.recovery.client.StepClient;
import nbcc.recovery.dto.Step;
import nbcc.recovery.dto.FellowshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class StepServiceImpl implements StepService {

    private final Logger logger = LoggerFactory.getLogger(StepServiceImpl.class);
    private final StepClient client;
    public StepServiceImpl(StepClient client) { this.client = client; }

    @Override public Result<Collection<Step>> getAll() {
        try { return client.getAll(); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public Result<Collection<Step>> getByFellowship(FellowshipType type) {
        try { return client.getByFellowship(type); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Step> get(Long id) {
        try { return client.get(id); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Step> create(Step item) {
        try { return client.create(item); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Step> update(Step item) {
        try { return client.update(item); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Void> delete(Long id) {
        try { return client.delete(id); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
}
