package nbcc.recovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "nbcc.recovery",
        "nbcc.auth",
        "nbcc.common"
})
@EntityScan(basePackages = {
        "nbcc.recovery",
        "nbcc.auth",
        "nbcc.common"
})
@EnableJpaRepositories(basePackages = {
        "nbcc.recovery",
        "nbcc.auth",
        "nbcc.common"
})
public class RecoveryWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecoveryWebApplication.class, args);
    }
}
