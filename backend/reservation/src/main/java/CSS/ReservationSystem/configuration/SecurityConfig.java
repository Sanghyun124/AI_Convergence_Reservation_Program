package CSS.ReservationSystem.configuration;

import CSS.ReservationSystem.security.JwtAuthenticationFilter;
import CSS.ReservationSystem.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                // CORS 설정
                .cors(c -> {
                        CorsConfigurationSource source = request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(List.of("*"));
                            config.setAllowedMethods(List.of("*"));
                            return config;
                        };
                        c.configurationSource(source);
                    }
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 조건별 요청 허용/제한 설정
                .authorizeRequests()
                .antMatchers("/api/member/login", "/api/reservation/**", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui/index.html", "/swagger-ui.html","/webjars/**", "/swagger/**", "/favicon.ico").permitAll()
                .antMatchers("/api/member/admin/**").hasRole("ADMIN") // /admin으로 시작하는 요청은 ADMIN 권한이 있는 유저에게만 허용
                .antMatchers("/api/member/**").hasAnyRole("STUDENT", "PROFESSOR", "ADMIN")
                .anyRequest().denyAll()
                .and()
                // JWT 인증 필터
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                // error handling
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setStatus(403);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("Unauthorized User.");
                    }
                })
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setStatus(401);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("Unauthenticated User.");
                    }
                });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
