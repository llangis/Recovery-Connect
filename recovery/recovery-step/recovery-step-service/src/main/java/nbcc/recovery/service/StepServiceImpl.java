package nbcc.recovery.service;

import nbcc.common.exception.ConcurrencyException;
import nbcc.common.result.Result;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.common.validation.ValidationError;
import nbcc.recovery.dto.Step;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.repository.api.StepRepository;
import nbcc.recovery.validation.StepValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class StepServiceImpl implements StepService {

    private final Logger logger = LoggerFactory.getLogger(StepServiceImpl.class);
    private final StepRepository repository;
    private final StepValidationService validationService;

    public StepServiceImpl(StepRepository repository, StepValidationService validationService) {
        this.repository = repository; this.validationService = validationService;
    }

    @Override
    public Result<Collection<Step>> getAll() {
        try { return ValidationResults.success(repository.getAll()); }
        catch (Exception e) { logger.error("Error retrieving all steps", e); return ValidationResults.error(e); }
    }

    @Override
    public Result<Collection<Step>> getByFellowship(FellowshipType type) {
        try { return ValidationResults.success(repository.getByFellowship(type)); }
        catch (Exception e) { logger.error("Error retrieving steps by fellowship {}", type, e); return ValidationResults.error(e); }
    }

    @Override
    public ValidatedResult<Step> get(Long id) {
        try { return ValidationResults.success(repository.get(id)); }
        catch (Exception e) { logger.error("Error retrieving step with id: {}", id, e); return ValidationResults.error(e); }
    }

    @Override
    public ValidatedResult<Step> create(Step item) {
        try {
            var errors = validationService.validate(item);
            if (errors.isEmpty()) { return ValidationResults.success(repository.create(item)); }
            else { logger.debug("Validation errors for step create: {}", errors); return ValidationResults.invalid(item, errors); }
        } catch (Exception e) { logger.error("Error creating step", e); return ValidationResults.error(item); }
    }

    @Override
    public ValidatedResult<Step> update(Step item) {
        try {
            var errors = validationService.validate(item);
            if (errors.isEmpty()) {
                try { return ValidationResults.success(repository.update(item)); }
                catch (ConcurrencyException e) { errors.add(new ValidationError("Step was modified since it was displayed, please refresh and try again")); }
            }
            logger.debug("Validation errors for step update: {}", errors);
            return ValidationResults.invalid(item, errors);
        } catch (Exception e) { logger.error("Error updating step", e); return ValidationResults.error(item); }
    }

    @Override
    public ValidatedResult<Void> delete(Long id) {
        try { repository.delete(id); logger.debug("Step with id {} deleted", id); return ValidationResults.success(); }
        catch (Exception e) { logger.error("Error deleting step with id: {}", id, e); return ValidationResults.error(e); }
    }
}
