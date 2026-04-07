package nbcc.recovery.mapper;

import nbcc.recovery.dto.Step;
import nbcc.recovery.entity.StepEntity;
import org.springframework.stereotype.Component;

@Component
public class StepMapper implements EntityMapper<Step, StepEntity> {
    public Step toDTO(StepEntity e) {
        if (e == null) return null;
        return new Step().setId(e.getId()).setStepNumber(e.getStepNumber()).setTitle(e.getTitle())
                .setDescription(e.getDescription()).setFellowshipType(e.getFellowshipType()).setVersion(e.getVersion());
    }
    public StepEntity toEntity(Step d) {
        if (d == null) return null;
        return new StepEntity().setId(d.getId()).setStepNumber(d.getStepNumber()).setTitle(d.getTitle())
                .setDescription(d.getDescription()).setFellowshipType(d.getFellowshipType()).setVersion(d.getVersion());
    }
}
