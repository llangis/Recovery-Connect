package nbcc.auth.domain;

import java.time.LocalDateTime;

public class LoginDetails {

    private Long id;

    private UserResponse user;

    private String token;

    private LocalDateTime loginAt;

    private LocalDateTime expireAt;

    private LocalDateTime logoutAt;

    public LoginDetails() {
    }

    public Long getId() {
        return id;
    }

    public LoginDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public UserResponse getUser() {
        return user;
    }

    public LoginDetails setUser(UserResponse user) {
        this.user = user;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginDetails setToken(String token) {
        this.token = token;
        return this;
    }

    public LocalDateTime getLoginAt() {
        return loginAt;
    }

    public LoginDetails setLoginAt(LocalDateTime loginAt) {
        this.loginAt = loginAt;
        return this;
    }

    public LocalDateTime getLogoutAt() {
        return logoutAt;
    }

    public LoginDetails setLogoutAt(LocalDateTime logoutAt) {
        this.logoutAt = logoutAt;
        return this;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public LoginDetails setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
        return this;
    }
}
