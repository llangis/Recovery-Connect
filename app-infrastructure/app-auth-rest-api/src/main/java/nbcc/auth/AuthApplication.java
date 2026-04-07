package nbcc.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"nbcc.auth", "nbcc.common"})
@EnableJpaRepositories(basePackages = {"nbcc.auth", "nbcc.common"})
@EntityScan(basePackages = {"nbcc.auth", "nbcc.common"})
public class AuthApplication {
    public static void main(String[] args) { SpringApplication.run(AuthApplication.class, args); }
}