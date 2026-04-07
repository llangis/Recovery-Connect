package nbcc.recovery.validation;

import nbcc.common.service.AnnotationValidationService;
import nbcc.common.validation.ValidationError;
import nbcc.recovery.dto.Step;
import nbcc.recovery.repository.api.StepRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StepValidationService {

    private final AnnotationValidationService annotationValidationService;
    private final StepRepository repository;

    public StepValidationService(AnnotationValidationService annotationValidationService, StepRepository repository) {
        this.annotationValidationService = annotationValidationService;
        this.repository = repository;
    }

    public Collection<ValidationError> validate(Step item) {
        var errors = new ArrayList<ValidationError>();
        errors.addAll(annotationValidationService.validate(item));
        errors.addAll(validateUnique(item));
        return errors;
    }

    private Collection<ValidationError> validateUnique(Step item) {
        if (item == null || item.getStepNumber() == null || item.getFellowshipType() == null) return List.of();

        boolean isNew = item.getId() == null || item.getId() < 1;
        boolean hasChanged = false;

        if (!isNew) {
            var existing = repository.get(item.getId());
            if (existing.isPresent()) {
                var db = existing.get();
                if (!db.getStepNumber().equals(item.getStepNumber()) || !db.getFellowshipType().equals(item.getFellowshipType())) {
                    hasChanged = true;
                }
            }
        }

        if (isNew || hasChanged) {
            if (repository.exists(item.getStepNumber(), item.getFellowshipType())) {
                return List.of(new ValidationError("Step number already exists for this fellowship", "stepNumber", item.getStepNumber()));
            }
        }
        return List.of();
    }
}
