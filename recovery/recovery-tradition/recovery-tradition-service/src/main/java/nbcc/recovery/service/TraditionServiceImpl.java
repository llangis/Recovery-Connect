package nbcc.recovery.service;

import nbcc.common.exception.ConcurrencyException;
import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.common.validation.ValidationError;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.repository.api.TraditionRepository;
import nbcc.recovery.validation.TraditionValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class TraditionServiceImpl implements TraditionService {

    private final Logger logger = LoggerFactory.getLogger(TraditionServiceImpl.class);
    private final TraditionRepository repository;
    private final TraditionValidationService validationService;

    public TraditionServiceImpl(TraditionRepository repository, TraditionValidationService validationService) {
        this.repository = repository; this.validationService = validationService;
    }

    @Override
    public Result<Collection<Tradition>> getAll() {
        try { return ValidationResults.success(repository.getAll()); }
        catch (Exception e) { logger.error("Error retrieving all traditions", e); return ValidationResults.error(e); }
    }

    @Override
    public Result<Collection<Tradition>> getByFellowship(FellowshipType type) {
        try { return ValidationResults.success(repository.getByFellowship(type)); }
        catch (Exception e) { logger.error("Error retrieving traditions by fellowship {}", type, e); return ValidationResults.error(e); }
    }

    @Override
    public ValidatedResult<Tradition> get(Long id) {
        try { return ValidationResults.success(repository.get(id)); }
        catch (Exception e) { logger.error("Error retrieving tradition with id: {}", id, e); return ValidationResults.error(e); }
    }

    @Override
    public ValidatedResult<Tradition> create(Tradition item) {
        try {
            var errors = validationService.validate(item);
            if (errors.isEmpty()) { return ValidationResults.success(repository.create(item)); }
            else { logger.debug("Validation errors for tradition create: {}", errors); return ValidationResults.invalid(item, errors); }
        } catch (Exception e) { logger.error("Error creating tradition", e); return ValidationResults.error(item); }
    }

    @Override
    public ValidatedResult<Tradition> update(Tradition item) {
        try {
            var errors = validationService.validate(item);
            if (errors.isEmpty()) {
                try { return ValidationResults.success(repository.update(item)); }
                catch (ConcurrencyException e) { errors.add(new ValidationError("Tradition was modified since it was displayed, please refresh and try again")); }
            }
            logger.debug("Validation errors for tradition update: {}", errors);
            return ValidationResults.invalid(item, errors);
        } catch (Exception e) { logger.error("Error updating tradition", e); return ValidationResults.error(item); }
    }

    @Override
    public ValidatedResult<Void> delete(Long id) {
        try { repository.delete(id); logger.debug("Tradition with id {} deleted", id); return ValidationResults.success(); }
        catch (Exception e) { logger.error("Error deleting tradition with id: {}", id, e); return ValidationResults.error(e); }
    }
}
