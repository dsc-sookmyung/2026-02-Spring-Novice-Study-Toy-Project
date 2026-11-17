package team.gdgocspring.config;

import team.gdgocspring.config.oauth.GoogleOAuth2UserService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
            ObjectProvider<ClientRegistrationRepository> registrations, GoogleOAuth2UserService google) throws Exception {
        boolean googleEnabled = registrations.getIfAvailable() != null;
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/login", "/css/**", "/img/**").permitAll()
                .anyRequest().authenticated());
        http.exceptionHandling(errors -> errors.authenticationEntryPoint(
                new LoginUrlAuthenticationEntryPoint("/login")));
        if (googleEnabled) {
            http.oauth2Login(login -> login.loginPage("/login").defaultSuccessUrl("/studies", true)
                    .userInfoEndpoint(info -> info.oidcUserService(google)));
        }
        http.logout(logout -> logout.logoutSuccessUrl("/login"));
        return http.build();
    }
}
