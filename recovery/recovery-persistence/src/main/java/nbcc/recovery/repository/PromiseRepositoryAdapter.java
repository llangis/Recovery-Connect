package nbcc.recovery.repository;

import nbcc.common.exception.ConcurrencyException;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.mapper.PromiseMapper;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PromiseRepositoryAdapter implements nbcc.recovery.repository.api.PromiseRepository {

    private final PromiseMapper mapper;
    private final PromiseJPARepository jpaRepository;

    public PromiseRepositoryAdapter(PromiseMapper mapper, PromiseJPARepository jpaRepository) {
        this.mapper = mapper; this.jpaRepository = jpaRepository;
    }

    @Override public List<Promise> getAll() { return mapper.toDTO(jpaRepository.findAll()); }
    @Override public List<Promise> getByFellowship(FellowshipType type) { return mapper.toDTO(jpaRepository.findByFellowshipTypeOrderByPromiseNumber(type)); }
    @Override public Optional<Promise> get(Long id) { return mapper.toDTO(jpaRepository.findById(id)); }
    @Override public Promise create(Promise p) { return mapper.toDTO(jpaRepository.save(mapper.toEntity(p))); }
    @Override public Promise update(Promise p) throws ConcurrencyException {
        try { return mapper.toDTO(jpaRepository.save(mapper.toEntity(p))); }
        catch (ConcurrencyFailureException e) { throw new ConcurrencyException(e); }
    }
    @Override public void delete(Long id) { jpaRepository.deleteById(id); }
    @Override public boolean exists(Integer num, FellowshipType type) { return jpaRepository.existsByPromiseNumberAndFellowshipType(num, type); }
}
