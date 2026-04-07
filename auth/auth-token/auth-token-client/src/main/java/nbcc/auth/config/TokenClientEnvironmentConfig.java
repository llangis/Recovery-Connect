package nbcc.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TokenClientEnvironmentConfig implements TokenClientConfig {

    private final Environment environment;

    public TokenClientEnvironmentConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getBaseUrl() {
        return environment.getProperty("token.rest.api.url");
    }

    @Override
    public String getPort() {
        return environment.getProperty("token.rest.api.port");
    }
}
