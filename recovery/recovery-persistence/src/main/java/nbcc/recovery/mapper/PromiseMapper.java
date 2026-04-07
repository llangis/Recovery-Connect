package nbcc.recovery.mapper;

import nbcc.recovery.dto.Promise;
import nbcc.recovery.entity.PromiseEntity;
import org.springframework.stereotype.Component;

@Component
public class PromiseMapper implements EntityMapper<Promise, PromiseEntity> {
    public Promise toDTO(PromiseEntity e) {
        if (e == null) return null;
        return new Promise().setId(e.getId()).setPromiseNumber(e.getPromiseNumber()).setText(e.getText())
                .setFellowshipType(e.getFellowshipType()).setVersion(e.getVersion());
    }
    public PromiseEntity toEntity(Promise d) {
        if (d == null) return null;
        return new PromiseEntity().setId(d.getId()).setPromiseNumber(d.getPromiseNumber()).setText(d.getText())
                .setFellowshipType(d.getFellowshipType()).setVersion(d.getVersion());
    }
}
