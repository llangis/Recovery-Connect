package nbcc.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class LoginClientEnvironmentConfig implements LoginClientConfig {

    private final Environment environment;

    public LoginClientEnvironmentConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getBaseUrl() {
        return environment.getProperty("login.rest.api.url");
    }

    @Override
    public String getPort() {
        return environment.getProperty("login.rest.api.port");
    }
}
