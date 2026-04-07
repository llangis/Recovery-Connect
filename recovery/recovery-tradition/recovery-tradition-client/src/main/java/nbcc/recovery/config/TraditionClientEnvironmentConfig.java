package nbcc.recovery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TraditionClientEnvironmentConfig implements TraditionClientConfig {
    private final Environment environment;
    public TraditionClientEnvironmentConfig(Environment environment) { this.environment = environment; }
    @Override public String getBaseUrl() { return environment.getProperty("tradition.rest.api.url"); }
    @Override public String getPort() { return environment.getProperty("tradition.rest.api.port"); }
}
