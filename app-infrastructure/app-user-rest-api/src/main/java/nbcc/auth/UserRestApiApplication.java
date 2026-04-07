package nbcc.auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"nbcc.auth", "nbcc.common"})
public class UserRestApiApplication {
    public static void main(String[] args) { SpringApplication.run(UserRestApiApplication.class, args); }
}
