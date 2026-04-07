
package nbcc.auth.client;

import nbcc.auth.config.UserClientConfig;
import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.services.TokenRetrievalService;
import nbcc.common.result.ClientResult;
import nbcc.common.result.ValidatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class UserClientImpl implements UserClient {

    private static final String basepath = "/api/user";

    private final RestClient restClient;
    private final UserClientConfig config;
    private final TokenRetrievalService tokenRetrievalService;

    private final Logger logger = LoggerFactory.getLogger(UserClientImpl.class);

    public UserClientImpl(RestClient.Builder builder, UserClientConfig config,  TokenRetrievalService tokenRetrievalService) {
        this.restClient = builder.build();
        this.config = config;
        this.tokenRetrievalService = tokenRetrievalService;
    }

    @Override
    public ValidatedResult<UserResponse> register(UserRegistration userRegistration) {
        try {
            var uri = new URI(config.getBaseAddress() + basepath + "/register");
            logger.debug("registering with URI {}", uri);
            var response = restClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userRegistration)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<UserResponse>>() {
                    });

            return ClientResult.response(response);

        } catch (HttpClientErrorException e) {
            logger.info("Failed to register user {} with status {}", userRegistration.getUsername(), e.getStatusCode());
            return ClientResult.response(e, UserResponse.class);

        } catch (Exception e) {
            logger.error("Failed to register user: {}", e.getMessage());
            return ClientResult.error(e);
        }
    }

    @Override
    public ValidatedResult<UserResponse> getProfile() {
        try {
            var uri = UriComponentsBuilder
                    .fromUriString(config.getBaseAddress())
                    .path(basepath)
                    .path("/profile")
                    .build()
                    .toUri();

            logger.debug("retrieving profile with URI {}", uri);
            var response = restClient.get()
                    .uri(uri)
                    .header("Authorization", "Bearer " + tokenRetrievalService.getToken())
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<UserResponse>>() {
                    });


            return ClientResult.response(response);

        } catch (HttpClientErrorException e) {
            logger.info("unsuccessful profile retrieval");
            return ClientResult.response(e, UserResponse.class);

        } catch (Exception e) {
            logger.error("Failed to retrieve profile", e);
            return ClientResult.error(e);
        }
    }
}