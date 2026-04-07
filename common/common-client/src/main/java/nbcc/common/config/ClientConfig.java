package nbcc.common.config;

public interface ClientConfig {
    String getBaseUrl();
    String getPort();
    default String getBaseAddress() { return getBaseUrl() + ":" + getPort(); }
}
