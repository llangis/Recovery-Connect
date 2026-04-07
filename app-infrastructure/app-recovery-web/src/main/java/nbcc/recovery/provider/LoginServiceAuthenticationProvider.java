package nbcc.recovery.provider;
import nbcc.auth.domain.LoginRequest;
import nbcc.auth.service.LoginService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class LoginServiceAuthenticationProvider implements AuthenticationProvider {
    private final LoginService loginService;
    public LoginServiceAuthenticationProvider(LoginService loginService) { this.loginService = loginService; }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        var password = authentication.getCredentials().toString();
        var result = loginService.login(new LoginRequest(username, password));
        if (!result.isSuccessful() || result.getValue() == null) throw new BadCredentialsException("Invalid credentials");
        return new UsernamePasswordAuthenticationToken(username, password, List.of());
    }
    @Override public boolean supports(Class<?> authentication) { return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication); }
}
