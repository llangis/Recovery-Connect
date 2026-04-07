package nbcc.recovery.service;

import nbcc.common.exception.ConcurrencyException;
import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.common.validation.ValidationError;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.repository.api.PromiseRepository;
import nbcc.recovery.validation.PromiseValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class PromiseServiceImpl implements PromiseService {

    private final Logger logger = LoggerFactory.getLogger(PromiseServiceImpl.class);
    private final PromiseRepository repository;
    private final PromiseValidationService validationService;

    public PromiseServiceImpl(PromiseRepository repository, PromiseValidationService validationService) {
        this.repository = repository; this.validationService = validationService;
    }

    @Override
    public Result<Collection<Promise>> getAll() {
        try { return ValidationResults.success(repository.getAll()); }
        catch (Exception e) { logger.error("Error retrieving all promises", e); return ValidationResults.error(e); }
    }

    @Override
    public Result<Collection<Promise>> getByFellowship(FellowshipType type) {
        try { return ValidationResults.success(repository.getByFellowship(type)); }
        catch (Exception e) { logger.error("Error retrieving promises by fellowship {}", type, e); return ValidationResults.error(e); }
    }

    @Override
    public ValidatedResult<Promise> get(Long id) {
        try { return ValidationResults.success(repository.get(id)); }
        catch (Exception e) { logger.error("Error retrieving promise with id: {}", id, e); return ValidationResults.error(e); }
    }

    @Override
    public ValidatedResult<Promise> create(Promise item) {
        try {
            var errors = validationService.validate(item);
            if (errors.isEmpty()) { return ValidationResults.success(repository.create(item)); }
            else { logger.debug("Validation errors for promise create: {}", errors); return ValidationResults.invalid(item, errors); }
        } catch (Exception e) { logger.error("Error creating promise", e); return ValidationResults.error(item); }
    }

    @Override
    public ValidatedResult<Promise> update(Promise item) {
        try {
            var errors = validationService.validate(item);
            if (errors.isEmpty()) {
                try { return ValidationResults.success(repository.update(item)); }
                catch (ConcurrencyException e) { errors.add(new ValidationError("Promise was modified since it was displayed, please refresh and try again")); }
            }
            logger.debug("Validation errors for promise update: {}", errors);
            return ValidationResults.invalid(item, errors);
        } catch (Exception e) { logger.error("Error updating promise", e); return ValidationResults.error(item); }
    }

    @Override
    public ValidatedResult<Void> delete(Long id) {
        try { repository.delete(id); logger.debug("Promise with id {} deleted", id); return ValidationResults.success(); }
        catch (Exception e) { logger.error("Error deleting promise with id: {}", id, e); return ValidationResults.error(e); }
    }
}
