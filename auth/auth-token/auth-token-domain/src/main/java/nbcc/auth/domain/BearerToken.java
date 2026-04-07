package nbcc.auth.domain;

import java.security.Principal;

public class BearerToken implements Principal {

    private long userId;
    private String name;

    private boolean valid;
    private long expiresIn;

    private String token;
    private String tokenType;

    public long getUserId() {
        return userId;
    }

    public BearerToken setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public BearerToken setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isValid() {
        return !isExpired();
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public BearerToken setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public boolean isExpired() {
        return getExpiresIn() < 1;
    }

    public String getToken() {
        return token;
    }

    public BearerToken setToken(String token) {
        this.token = token;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public BearerToken setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }
}
