package nbcc.recovery.mapper;

import nbcc.recovery.dto.Tradition;
import nbcc.recovery.entity.TraditionEntity;
import org.springframework.stereotype.Component;

@Component
public class TraditionMapper implements EntityMapper<Tradition, TraditionEntity> {
    public Tradition toDTO(TraditionEntity e) {
        if (e == null) return null;
        return new Tradition().setId(e.getId()).setTraditionNumber(e.getTraditionNumber()).setTitle(e.getTitle())
                .setDescription(e.getDescription()).setFellowshipType(e.getFellowshipType()).setVersion(e.getVersion());
    }
    public TraditionEntity toEntity(Tradition d) {
        if (d == null) return null;
        return new TraditionEntity().setId(d.getId()).setTraditionNumber(d.getTraditionNumber()).setTitle(d.getTitle())
                .setDescription(d.getDescription()).setFellowshipType(d.getFellowshipType()).setVersion(d.getVersion());
    }
}
