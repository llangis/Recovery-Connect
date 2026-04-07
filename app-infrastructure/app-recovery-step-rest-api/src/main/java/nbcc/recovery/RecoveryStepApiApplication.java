package nbcc.recovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication @EnableCaching
@ComponentScan(basePackages = {"nbcc.recovery", "nbcc.common", "nbcc.auth"})
public class RecoveryStepApiApplication {
    public static void main(String[] args) { SpringApplication.run(RecoveryStepApiApplication.class, args); }
}
