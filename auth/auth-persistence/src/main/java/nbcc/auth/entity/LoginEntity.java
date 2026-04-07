package nbcc.auth.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_login",
        indexes = @Index(name = "idx_token", columnList = "token", unique = true)
)
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_login"))
    private UserEntity user;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime loginAt;

    private LocalDateTime logoutAt;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    public LoginEntity() {
    }

    public Long getId() {
        return id;
    }

    public LoginEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public LoginEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginEntity setToken(String token) {
        this.token = token;
        return this;
    }

    public LocalDateTime getLoginAt() {
        return loginAt;
    }

    public LoginEntity setLoginAt(LocalDateTime loginAt) {
        this.loginAt = loginAt;
        return this;
    }

    public LocalDateTime getLogoutAt() {
        return logoutAt;
    }

    public LoginEntity setLogoutAt(LocalDateTime logoutAt) {
        this.logoutAt = logoutAt;
        return this;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public LoginEntity setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
        return this;
    }
}
