import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.dto.Step;
import nbcc.recovery.service.StepServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StepServiceTests {
    // TODO: Add tests with mock repository and validation service
    @Test
    void stepCreation() {
        var step = new Step().setStepNumber(1).setTitle("Admitted powerlessness")
                .setDescription("We admitted we were powerless...").setFellowshipType(FellowshipType.AA);
        assertEquals(1, step.getStepNumber());
        assertEquals(FellowshipType.AA, step.getFellowshipType());
    }
}
