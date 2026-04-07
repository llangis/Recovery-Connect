package nbcc.recovery.cache;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.service.PromiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.Collection;
import static nbcc.recovery.cache.PromiseCacheNames.*;

@Service
@Primary
public class PromiseServiceCache implements PromiseService {

    private final Logger logger = LoggerFactory.getLogger(PromiseServiceCache.class);
    private final PromiseService delegate;

    public PromiseServiceCache(@Qualifier("promiseServiceImpl") PromiseService service) { this.delegate = service; }

    @Override @Cacheable(value = PROMISE_LIST_CACHE)
    public Result<Collection<Promise>> getAll() { logger.debug("Fetching all promises - caching"); return delegate.getAll(); }

    @Override
    public Result<Collection<Promise>> getByFellowship(FellowshipType type) { return delegate.getByFellowship(type); }

    @Override @Cacheable(value = PROMISE_CACHE, key = "#id")
    public ValidatedResult<Promise> get(Long id) { logger.debug("Fetching promise {} - caching", id); return delegate.get(id); }

    @Override
    @CachePut(value = PROMISE_CACHE, key = "#result.value.id", unless = "!#result.successful")
    @CacheEvict(value = PROMISE_LIST_CACHE, allEntries = true)
    public ValidatedResult<Promise> create(Promise item) { return delegate.create(item); }

    @Override
    @CachePut(value = PROMISE_CACHE, key = "#result.value.id", unless = "!#result.successful")
    @CacheEvict(value = PROMISE_LIST_CACHE, allEntries = true)
    public ValidatedResult<Promise> update(Promise item) { return delegate.update(item); }

    @Override
    @Caching(evict = { @CacheEvict(value = PROMISE_CACHE, key = "#id"), @CacheEvict(value = PROMISE_LIST_CACHE, allEntries = true) })
    public ValidatedResult<Void> delete(Long id) { return delegate.delete(id); }
}
