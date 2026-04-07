package nbcc.recovery.cache;

import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.service.TraditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.Collection;
import static nbcc.recovery.cache.TraditionCacheNames.*;

@Service
@Primary
public class TraditionServiceCache implements TraditionService {

    private final Logger logger = LoggerFactory.getLogger(TraditionServiceCache.class);
    private final TraditionService delegate;

    public TraditionServiceCache(@Qualifier("traditionServiceImpl") TraditionService service) { this.delegate = service; }

    @Override @Cacheable(value = TRADITION_LIST_CACHE)
    public Result<Collection<Tradition>> getAll() { logger.debug("Fetching all traditions - caching"); return delegate.getAll(); }

    @Override
    public Result<Collection<Tradition>> getByFellowship(FellowshipType type) { return delegate.getByFellowship(type); }

    @Override @Cacheable(value = TRADITION_CACHE, key = "#id")
    public ValidatedResult<Tradition> get(Long id) { logger.debug("Fetching tradition {} - caching", id); return delegate.get(id); }

    @Override
    @CachePut(value = TRADITION_CACHE, key = "#result.value.id", unless = "!#result.successful")
    @CacheEvict(value = TRADITION_LIST_CACHE, allEntries = true)
    public ValidatedResult<Tradition> create(Tradition item) { return delegate.create(item); }

    @Override
    @CachePut(value = TRADITION_CACHE, key = "#result.value.id", unless = "!#result.successful")
    @CacheEvict(value = TRADITION_LIST_CACHE, allEntries = true)
    public ValidatedResult<Tradition> update(Tradition item) { return delegate.update(item); }

    @Override
    @Caching(evict = { @CacheEvict(value = TRADITION_CACHE, key = "#id"), @CacheEvict(value = TRADITION_LIST_CACHE, allEntries = true) })
    public ValidatedResult<Void> delete(Long id) { return delegate.delete(id); }
}
