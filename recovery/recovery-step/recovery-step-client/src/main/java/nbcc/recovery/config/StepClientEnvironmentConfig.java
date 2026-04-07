package nbcc.recovery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class StepClientEnvironmentConfig implements StepClientConfig {
    private final Environment environment;
    public StepClientEnvironmentConfig(Environment environment) { this.environment = environment; }
    @Override public String getBaseUrl() { return environment.getProperty("step.rest.api.url"); }
    @Override public String getPort() { return environment.getProperty("step.rest.api.port"); }
}
