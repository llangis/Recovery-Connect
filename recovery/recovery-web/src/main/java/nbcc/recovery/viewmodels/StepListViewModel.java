package nbcc.recovery.viewmodels;

import nbcc.recovery.dto.Step;
import java.util.Collection;
import java.util.List;

public class StepListViewModel {
    private final Collection<Step> steps;
    private final boolean showManage;
    public StepListViewModel(Collection<Step> steps, boolean showManage) {
        this.steps = steps != null ? steps : List.of(); this.showManage = showManage;
    }
    public Collection<Step> getSteps() { return steps; }
    public boolean isShowManage() { return showManage; }
}
