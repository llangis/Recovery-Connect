package nbcc.recovery.service;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.recovery.client.TraditionClient;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.dto.FellowshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class TraditionServiceImpl implements TraditionService {

    private final Logger logger = LoggerFactory.getLogger(TraditionServiceImpl.class);
    private final TraditionClient client;
    public TraditionServiceImpl(TraditionClient client) { this.client = client; }

    @Override public Result<Collection<Tradition>> getAll() {
        try { return client.getAll(); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public Result<Collection<Tradition>> getByFellowship(FellowshipType type) {
        try { return client.getByFellowship(type); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Tradition> get(Long id) {
        try { return client.get(id); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Tradition> create(Tradition item) {
        try { return client.create(item); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Tradition> update(Tradition item) {
        try { return client.update(item); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
    @Override public ValidatedResult<Void> delete(Long id) {
        try { return client.delete(id); } catch (Exception e) { logger.error("Error", e); return ValidationResults.error(e); }
    }
}
