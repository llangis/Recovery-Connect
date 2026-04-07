package nbcc.recovery.validation;

import nbcc.common.service.AnnotationValidationService;
import nbcc.common.validation.ValidationError;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.repository.api.TraditionRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TraditionValidationService {

    private final AnnotationValidationService annotationValidationService;
    private final TraditionRepository repository;

    public TraditionValidationService(AnnotationValidationService annotationValidationService, TraditionRepository repository) {
        this.annotationValidationService = annotationValidationService;
        this.repository = repository;
    }

    public Collection<ValidationError> validate(Tradition item) {
        var errors = new ArrayList<ValidationError>();
        errors.addAll(annotationValidationService.validate(item));
        errors.addAll(validateUnique(item));
        return errors;
    }

    private Collection<ValidationError> validateUnique(Tradition item) {
        if (item == null || item.getTraditionNumber() == null || item.getFellowshipType() == null) return List.of();

        boolean isNew = item.getId() == null || item.getId() < 1;
        boolean hasChanged = false;

        if (!isNew) {
            var existing = repository.get(item.getId());
            if (existing.isPresent()) {
                var db = existing.get();
                if (!db.getTraditionNumber().equals(item.getTraditionNumber()) || !db.getFellowshipType().equals(item.getFellowshipType())) {
                    hasChanged = true;
                }
            }
        }

        if (isNew || hasChanged) {
            if (repository.exists(item.getTraditionNumber(), item.getFellowshipType())) {
                return List.of(new ValidationError("Tradition number already exists for this fellowship", "traditionNumber", item.getTraditionNumber()));
            }
        }
        return List.of();
    }
}
