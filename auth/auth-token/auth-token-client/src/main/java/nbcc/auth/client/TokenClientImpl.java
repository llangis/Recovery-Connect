package nbcc.auth.client;

import nbcc.auth.config.TokenClientConfig;
import nbcc.auth.domain.BearerToken;
import nbcc.common.result.ClientResult;
import nbcc.common.result.ValidatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TokenClientImpl implements TokenClient {

    private static final String basepath = "/api/auth";

    private final RestClient restClient;
    private final TokenClientConfig config;

    private final Logger logger = LoggerFactory.getLogger(TokenClientImpl.class);

    public TokenClientImpl(RestClient.Builder builder, TokenClientConfig config) {
        this.restClient = builder.build();
        this.config = config;
    }

    @Override
    public ValidatedResult<BearerToken> validateToken(String token) {
        try {
            var uri = UriComponentsBuilder
                    .fromUriString(config.getBaseAddress())
                    .path(basepath)
                    .path("/validate")
                    .build()
                    .toUri();

            logger.debug("validating token with URI {}", uri);
            var response = restClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(token)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<BearerToken>>() {
                    });

            return ClientResult.response(response);

        } catch (HttpClientErrorException e) {
            logger.warn("Failed to validate token", e);
            return ClientResult.response(e, BearerToken.class);

        } catch (Exception e) {
            logger.error("Failed to validate token: {}", e.getMessage());
            return ClientResult.error(e);
        }
    }
}
