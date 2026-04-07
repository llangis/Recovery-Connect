package nbcc.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class UserClientEnvironmentConfig implements UserClientConfig {

    private final Environment environment;

    public UserClientEnvironmentConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getBaseUrl() {
        return environment.getProperty("user.rest.api.url");
    }

    @Override
    public String getPort() {
        return environment.getProperty("user.rest.api.port");
    }
}
