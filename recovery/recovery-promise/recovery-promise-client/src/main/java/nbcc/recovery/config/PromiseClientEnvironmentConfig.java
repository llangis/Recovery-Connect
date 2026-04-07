package nbcc.recovery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PromiseClientEnvironmentConfig implements PromiseClientConfig {
    private final Environment environment;
    public PromiseClientEnvironmentConfig(Environment environment) { this.environment = environment; }
    @Override public String getBaseUrl() { return environment.getProperty("promise.rest.api.url"); }
    @Override public String getPort() { return environment.getProperty("promise.rest.api.port"); }
}
