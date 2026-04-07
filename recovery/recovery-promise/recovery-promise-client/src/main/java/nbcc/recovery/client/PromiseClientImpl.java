package nbcc.recovery.client;

import nbcc.auth.services.TokenRetrievalService;
import nbcc.common.result.ClientResult;
import nbcc.common.result.ValidatedResult;
import nbcc.recovery.config.PromiseClientConfig;
import nbcc.recovery.dto.Promise;
import nbcc.recovery.dto.FellowshipType;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.function.Consumer;

@Component
public class PromiseClientImpl implements PromiseClient {

    private final Logger logger = LoggerFactory.getLogger(PromiseClientImpl.class);
    private static final String BASEPATH = "/api/promise";
    private final RestClient restClient;
    private final PromiseClientConfig config;
    private final TokenRetrievalService tokenService;

    public PromiseClientImpl(RestClient.Builder builder, PromiseClientConfig config, TokenRetrievalService tokenService) {
        this.restClient = builder.build(); this.config = config; this.tokenService = tokenService;
    }

    public ValidatedResult<Collection<Promise>> getAll() {
        try {
            var uri = UriComponentsBuilder.fromUriString(config.getBaseAddress()).path(BASEPATH).build().toUri();
            var response = restClient.get().uri(uri).headers(getAuthorizationHeaders()).retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<Collection<Promise>>>() {});
            return ClientResult.response(response);
        } catch (HttpClientErrorException e) { return ClientResult.response(e, new ParameterizedTypeReference<>() {}); }
        catch (Exception e) { logger.error("error on promise getAll", e); return ClientResult.error(e); }
    }

    public ValidatedResult<Collection<Promise>> getByFellowship(FellowshipType type) {
        try {
            var uri = UriComponentsBuilder.fromUriString(config.getBaseAddress()).path(BASEPATH + "/fellowship/" + type).build().toUri();
            var response = restClient.get().uri(uri).headers(getAuthorizationHeaders()).retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<Collection<Promise>>>() {});
            return ClientResult.response(response);
        } catch (HttpClientErrorException e) { return ClientResult.response(e, new ParameterizedTypeReference<>() {}); }
        catch (Exception e) { logger.error("error on promise getByFellowship", e); return ClientResult.error(e); }
    }

    public ValidatedResult<Promise> get(Long id) {
        try {
            var uri = new URI(config.getBaseAddress() + BASEPATH + "/" + id);
            var response = restClient.get().uri(uri).headers(getAuthorizationHeaders()).retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<Promise>>() {});
            return ClientResult.response(response);
        } catch (HttpClientErrorException e) { return ClientResult.response(e, new ParameterizedTypeReference<>() {}); }
        catch (Exception e) { logger.error("error on promise get {}", id, e); return ClientResult.error(e); }
    }

    public ValidatedResult<Promise> create(Promise item) {
        try {
            var uri = UriComponentsBuilder.fromUriString(config.getBaseAddress()).path(BASEPATH).build().toUri();
            var response = restClient.post().uri(uri).headers(getAuthorizationHeaders()).contentType(MediaType.APPLICATION_JSON).body(item).retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<Promise>>() {});
            return ClientResult.response(response);
        } catch (HttpClientErrorException e) { return ClientResult.response(e, new ParameterizedTypeReference<>() {}); }
        catch (Exception e) { logger.error("error on promise create", e); return ClientResult.error(e); }
    }

    public ValidatedResult<Promise> update(Promise item) {
        try {
            var response = restClient.put().uri(config.getBaseAddress() + BASEPATH).headers(getAuthorizationHeaders())
                    .contentType(MediaType.APPLICATION_JSON).body(item).retrieve()
                    .toEntity(new ParameterizedTypeReference<ClientResult<Promise>>() {});
            return ClientResult.response(response);
        } catch (HttpClientErrorException e) { return ClientResult.response(e, Promise.class); }
        catch (Exception e) { logger.error("error on promise update", e); return ClientResult.error(e); }
    }

    public ValidatedResult<Void> delete(Long id) {
        try {
            var response = restClient.delete().uri(config.getBaseAddress() + BASEPATH + "/{id}", id).headers(getAuthorizationHeaders())
                    .retrieve().toEntity(new ParameterizedTypeReference<ClientResult<Void>>() {});
            return ClientResult.response(response);
        } catch (HttpClientErrorException e) { return ClientResult.response(e, new ParameterizedTypeReference<>() {}); }
        catch (Exception e) { logger.error("error on promise delete {}", id, e); return ClientResult.error(e); }
    }

    private @NonNull Consumer<HttpHeaders> getAuthorizationHeaders() {
        return headers -> { var token = tokenService.getToken(); if (token != null && !token.isBlank()) headers.add("Authorization", "Bearer " + token); };
    }
}
