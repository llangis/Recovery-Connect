package nbcc.common.result;

import nbcc.common.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Collection;
import java.util.List;

public class ClientResult<T> implements ValidatedResult<T> {

    private static final Logger logger = LoggerFactory.getLogger(ClientResult.class);

    private int statusCode;
    private T value;
    private boolean successful;
    private boolean error;
    private Collection<ValidationError> validationErrors;

    public ClientResult() {}

    ClientResult(int statusCode, T value, boolean successful, boolean error,
                 Collection<ValidationError> validationErrors) {
        this();
        this.statusCode = statusCode; this.value = value;
        this.successful = successful; this.error = error;
        this.validationErrors = validationErrors;
    }

    public int getStatusCode() { return statusCode; }
    public ClientResult<T> setStatusCode(int statusCode) { this.statusCode = statusCode; return this; }
    @Override public T getValue() { return value; }
    public ClientResult<T> setValue(T value) { this.value = value; return this; }
    @Override public boolean isSuccessful() { return successful; }
    public ClientResult<T> setSuccessful(boolean successful) { this.successful = successful; return this; }
    @Override public boolean isError() { return error; }
    public ClientResult<T> setError(boolean error) { this.error = error; return this; }
    @Override public Collection<ValidationError> getValidationErrors() { return validationErrors; }
    public ClientResult<T> setValidationErrors(Collection<ValidationError> validationErrors) {
        this.validationErrors = validationErrors; return this;
    }

    public static <T> ClientResult<T> error() { return response(HttpStatus.INTERNAL_SERVER_ERROR); }
    public static <T> ClientResult<T> error(Exception e) { return response(HttpStatus.INTERNAL_SERVER_ERROR); }

    public static <T> ClientResult<T> response(ResponseEntity<ClientResult<T>> responseEntity) {
        var result = responseEntity.getBody();
        if (result != null) { result.setStatusCode(responseEntity.getStatusCode().value()); }
        return result;
    }

    public static <T> ClientResult<T> response(HttpClientErrorException e, Class<T> targetType) {
        return response(e, new ParameterizedTypeReference<>() {});
    }

    public static <T> ClientResult<T> response(HttpClientErrorException e, ParameterizedTypeReference<ClientResult<T>> bodyType) {
        try {
            if (!e.getResponseBodyAsString().isEmpty()) {
                var clientResult = e.getResponseBodyAs(bodyType);
                if (clientResult != null) { return clientResult.setStatusCode(e.getStatusCode().value()); }
            }
            return response(e.getStatusCode());
        } catch (Exception convertException) {
            logger.error("Error parsing response to body type {}", bodyType.getType().getTypeName(), convertException);
            return error(convertException);
        }
    }

    public static <T> ClientResult<T> response(HttpStatusCode statusCode) {
        var success = statusCode.is2xxSuccessful();
        var err = statusCode.is5xxServerError();
        return new ClientResult<>(statusCode.value(), null, success, err, List.of());
    }
}
