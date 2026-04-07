package nbcc.recovery.service;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.recovery.client.PromiseClient;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.dto.FellowshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class PromiseServiceImpl implements PromiseService {

    private final Logger logger = LoggerFactory.getLogger(PromiseServiceImpl.class);
    private final PromiseClient client;
    public PromiseServiceImpl(PromiseClient client) { this.client = client; }

    @Override public Result<Collection<Promise>> getAll() {
        try { return client.getAll(); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public Result<Collection<Promise>> getByFellowship(FellowshipType type) {
        try { return client.getByFellowship(type); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Promise> get(Long id) {
        try { return client.get(id); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Promise> create(Promise item) {
        try { return client.create(item); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Promise> update(Promise item) {
        try { return client.update(item); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Void> delete(Long id) {
        try { return client.delete(id); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
}
