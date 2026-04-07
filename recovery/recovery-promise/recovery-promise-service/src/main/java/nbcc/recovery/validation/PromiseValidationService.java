package nbcc.recovery.validation;

import nbcc.common.service.AnnotationValidationService;
import nbcc.common.validation.ValidationError;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.repository.api.PromiseRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PromiseValidationService {

    private final AnnotationValidationService annotationValidationService;
    private final PromiseRepository repository;

    public PromiseValidationService(AnnotationValidationService annotationValidationService, PromiseRepository repository) {
        this.annotationValidationService = annotationValidationService;
        this.repository = repository;
    }

    public Collection<ValidationError> validate(Promise item) {
        var errors = new ArrayList<ValidationError>();
        errors.addAll(annotationValidationService.validate(item));
        errors.addAll(validateUnique(item));
        return errors;
    }

    private Collection<ValidationError> validateUnique(Promise item) {
        if (item == null || item.getPromiseNumber() == null || item.getFellowshipType() == null) return List.of();

        boolean isNew = item.getId() == null || item.getId() < 1;
        boolean hasChanged = false;

        if (!isNew) {
            var existing = repository.get(item.getId());
            if (existing.isPresent()) {
                var db = existing.get();
                if (!db.getPromiseNumber().equals(item.getPromiseNumber()) || !db.getFellowshipType().equals(item.getFellowshipType())) {
                    hasChanged = true;
                }
            }
        }

        if (isNew || hasChanged) {
            if (repository.exists(item.getPromiseNumber(), item.getFellowshipType())) {
                return List.of(new ValidationError("Promise number already exists for this fellowship", "promiseNumber", item.getPromiseNumber()));
            }
        }
        return List.of();
    }
}
