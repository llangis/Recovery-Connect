package nbcc.recovery.config;
import nbcc.recovery.provider.LoginServiceAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
    private final LoginServiceAuthenticationProvider authenticationProvider;
    public SecurityConfig(LoginServiceAuthenticationProvider authenticationProvider) { this.authenticationProvider = authenticationProvider; }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/register", "/login", "/step", "/step/**", "/tradition", "/tradition/**", "/promise", "/promise/**", "/css/**", "/js/**", "/favicon.ico", "/nbcclogo.png", "/aa/**", "/na/**", "/ca/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll())
                .build();
    }
}
