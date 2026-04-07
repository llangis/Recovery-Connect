package nbcc.recovery.repository;

import nbcc.common.exception.ConcurrencyException;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.dto.Tradition;
import nbcc.recovery.mapper.TraditionMapper;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TraditionRepositoryAdapter implements nbcc.recovery.repository.api.TraditionRepository {

    private final TraditionMapper mapper;
    private final TraditionJPARepository jpaRepository;

    public TraditionRepositoryAdapter(TraditionMapper mapper, TraditionJPARepository jpaRepository) {
        this.mapper = mapper; this.jpaRepository = jpaRepository;
    }

    @Override public List<Tradition> getAll() { return mapper.toDTO(jpaRepository.findAll()); }
    @Override public List<Tradition> getByFellowship(FellowshipType type) { return mapper.toDTO(jpaRepository.findByFellowshipTypeOrderByTraditionNumber(type)); }
    @Override public Optional<Tradition> get(Long id) { return mapper.toDTO(jpaRepository.findById(id)); }
    @Override public Tradition create(Tradition t) { return mapper.toDTO(jpaRepository.save(mapper.toEntity(t))); }
    @Override public Tradition update(Tradition t) throws ConcurrencyException {
        try { return mapper.toDTO(jpaRepository.save(mapper.toEntity(t))); }
        catch (ConcurrencyFailureException e) { throw new ConcurrencyException(e); }
    }
    @Override public void delete(Long id) { jpaRepository.deleteById(id); }
    @Override public boolean exists(Integer num, FellowshipType type) { return jpaRepository.existsByTraditionNumberAndFellowshipType(num, type); }
}
