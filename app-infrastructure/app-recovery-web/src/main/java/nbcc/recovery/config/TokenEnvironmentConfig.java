package nbcc.recovery.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
@Configuration
public class TokenEnvironmentConfig implements nbcc.auth.config.BearerConfig {
    private final Environment environment;
    public TokenEnvironmentConfig(Environment environment) { this.environment = environment; }
    @Override public long getTimeout() { return Long.parseLong(environment.getProperty("token.timeout", "3600")); }
}
