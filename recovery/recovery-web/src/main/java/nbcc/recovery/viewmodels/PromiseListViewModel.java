package nbcc.recovery.viewmodels;

import nbcc.recovery.dto.Promise;
import java.util.Collection;
import java.util.List;

public class PromiseListViewModel {
    private final Collection<Promise> promises;
    private final boolean showManage;
    public PromiseListViewModel(Collection<Promise> promises, boolean showManage) {
        this.promises = promises != null ? promises : List.of(); this.showManage = showManage;
    }
    public Collection<Promise> getPromises() { return promises; }
    public boolean isShowManage() { return showManage; }
}
