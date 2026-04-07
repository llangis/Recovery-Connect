package nbcc.recovery.cache;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Step;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.service.StepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.Collection;
import static nbcc.recovery.cache.StepCacheNames.*;

@Service
@Primary
public class StepServiceCache implements StepService {

    private final Logger logger = LoggerFactory.getLogger(StepServiceCache.class);
    private final StepService delegate;

    public StepServiceCache(@Qualifier("stepServiceImpl") StepService service) { this.delegate = service; }

    @Override @Cacheable(value = STEP_LIST_CACHE)
    public Result<Collection<Step>> getAll() { logger.debug("Fetching all steps - caching"); return delegate.getAll(); }

    @Override
    public Result<Collection<Step>> getByFellowship(FellowshipType type) { return delegate.getByFellowship(type); }

    @Override @Cacheable(value = STEP_CACHE, key = "#id")
    public ValidatedResult<Step> get(Long id) { logger.debug("Fetching step {} - caching", id); return delegate.get(id); }

    @Override
    @CachePut(value = STEP_CACHE, key = "#result.value.id", unless = "!#result.successful")
    @CacheEvict(value = STEP_LIST_CACHE, allEntries = true)
    public ValidatedResult<Step> create(Step item) { return delegate.create(item); }

    @Override
    @CachePut(value = STEP_CACHE, key = "#result.value.id", unless = "!#result.successful")
    @CacheEvict(value = STEP_LIST_CACHE, allEntries = true)
    public ValidatedResult<Step> update(Step item) { return delegate.update(item); }

    @Override
    @Caching(evict = { @CacheEvict(value = STEP_CACHE, key = "#id"), @CacheEvict(value = STEP_LIST_CACHE, allEntries = true) })
    public ValidatedResult<Void> delete(Long id) { return delegate.delete(id); }
}
