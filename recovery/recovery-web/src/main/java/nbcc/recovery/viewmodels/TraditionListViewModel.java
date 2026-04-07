package nbcc.recovery.viewmodels;

import nbcc.recovery.dto.Tradition;
import java.util.Collection;
import java.util.List;

public class TraditionListViewModel {
    private final Collection<Tradition> traditions;
    private final boolean showManage;
    public TraditionListViewModel(Collection<Tradition> traditions, boolean showManage) {
        this.traditions = traditions != null ? traditions : List.of(); this.showManage = showManage;
    }
    public Collection<Tradition> getTraditions() { return traditions; }
    public boolean isShowManage() { return showManage; }
}
