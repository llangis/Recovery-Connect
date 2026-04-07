package nbcc.recovery.repository;

import nbcc.common.exception.ConcurrencyException;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.dto.Step;
import nbcc.recovery.mapper.StepMapper;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class StepRepositoryAdapter implements nbcc.recovery.repository.api.StepRepository {

    private final StepMapper mapper;
    private final StepJPARepository jpaRepository;

    public StepRepositoryAdapter(StepMapper mapper, StepJPARepository jpaRepository) {
        this.mapper = mapper; this.jpaRepository = jpaRepository;
    }

    @Override public List<Step> getAll() { return mapper.toDTO(jpaRepository.findAll()); }
    @Override public List<Step> getByFellowship(FellowshipType type) { return mapper.toDTO(jpaRepository.findByFellowshipTypeOrderByStepNumber(type)); }
    @Override public Optional<Step> get(Long id) { return mapper.toDTO(jpaRepository.findById(id)); }
    @Override public Step create(Step step) { var e = mapper.toEntity(step); return mapper.toDTO(jpaRepository.save(e)); }
    @Override public Step update(Step step) throws ConcurrencyException {
        try { var e = mapper.toEntity(step); return mapper.toDTO(jpaRepository.save(e)); }
        catch (ConcurrencyFailureException e) { throw new ConcurrencyException(e); }
    }
    @Override public void delete(Long id) { jpaRepository.deleteById(id); }
    @Override public boolean exists(Integer stepNumber, FellowshipType type) { return jpaRepository.existsByStepNumberAndFellowshipType(stepNumber, type); }
}
