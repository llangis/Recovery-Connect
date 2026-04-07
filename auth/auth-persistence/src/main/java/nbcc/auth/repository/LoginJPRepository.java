package nbcc.auth.repository;

import nbcc.auth.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoginJPRepository extends JpaRepository<LoginEntity, Long> {
    Optional<LoginEntity> findByToken(String token);
}
