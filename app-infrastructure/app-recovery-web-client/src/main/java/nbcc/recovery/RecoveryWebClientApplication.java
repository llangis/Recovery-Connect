package nbcc.recovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"nbcc.recovery", "nbcc.common", "nbcc.auth"})
public class RecoveryWebClientApplication {
    public static void main(String[] args) { SpringApplication.run(RecoveryWebClientApplication.class, args); }
}
