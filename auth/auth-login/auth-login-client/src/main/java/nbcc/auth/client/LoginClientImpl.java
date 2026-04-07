package nbcc.auth.client;

import nbcc.auth.config.LoginClientConfig;
import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginRequest;
import nbcc.auth.domain.UserResponse;
import nbcc.common.result.ClientResult;
import nbcc.common.result.ValidatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Primary
@Component
public class LoginClientImpl implements LoginClient {

    private static final String basepath = "/api/auth";

    private final RestClient restClient;
    private final LoginClientConfig config;

    private final Logger logger = LoggerFactory.getLogger(LoginClientImpl.class);

    public LoginClientImpl(RestClient.Builder builder, LoginClientConfig config) {
        this.restClient = builder.build();
        this.config = config;
    }

    @Override
    public ValidatedResult<BearerToken> login(LoginRequest loginRequest) {
        try {
            var uri = UriComponentsBuilder
                    .fromUriString(config.getBaseAddress())
                    .path(basepath)
                    .path("/login")
                    .build()
                    .toUri();

            logger.debug("Creating token for user {} with URI {}", loginRequest.getUsername(), uri);
            var response = restClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(loginRequest)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<BearerToken>>() {
                    });

            return ClientResult.response(response);

        } catch (HttpClientErrorException e) {
            logger.warn("Failed to create token for user {}", loginRequest.getUsername(), e);
            return ClientResult.response(e, BearerToken.class);

        } catch (Exception e) {
            logger.error("Failed to create token for user {}:", loginRequest.getUsername(), e);
            return ClientResult.error(e);
        }
    }

    @Override
    public ValidatedResult<Boolean> logout(String token) {
        try {
            var uri = UriComponentsBuilder
                    .fromUriString(config.getBaseAddress())
                    .path(basepath)
                    .path("/logout")
                    .build()
                    .toUri();

            logger.debug("logging out with URI {}", uri);
            var response = restClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(token)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<Boolean>>() {
                    });

            return ClientResult.response(response);

        } catch (HttpClientErrorException e) {
            logger.error("Failed to logout user", e);
            return ClientResult.response(e, Boolean.class);

        } catch (Exception e) {
            logger.error("Failed to logout user: {}", e.getMessage());
            return ClientResult.error(e);
        }
    }

    @Override
    public ValidatedResult<UserResponse> isAuthorized(LoginRequest loginRequest) {
        try {
            var uri = UriComponentsBuilder
                    .fromUriString(config.getBaseAddress())
                    .path(basepath)
                    .path("/authorize")
                    .build()
                    .toUri();

            logger.debug("authorising with URI {}", uri);
            var response = restClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(loginRequest)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<UserResponse>>() {
                    });

            return ClientResult.response(response);

        } catch (HttpClientErrorException e) {
            logger.warn("Failed to authorize user {}", loginRequest.getUsername(), e);
            return ClientResult.response(e, UserResponse.class);

        } catch (Exception e) {
            logger.error("Failed to authorize user {}", loginRequest.getUsername(), e);
            return ClientResult.error(e);
        }
    }
}