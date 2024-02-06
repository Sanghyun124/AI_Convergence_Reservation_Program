package CSS.ReservationSystem.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);

//        if(token != null && jwtTokenProvider.validateToken(token)) {
//            token = token.split(" ")[1].trim();
//            Authentication auth = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
        try {
            if(token != null && jwtTokenProvider.validateToken(token)) {
                token = token.split(" ")[1].trim();
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch(SecurityException | MalformedJwtException | IllegalArgumentException e) {
            request.setAttribute("exception", ErrorCode.WRONG_TYPE_TOKEN.getStatus());
            log.error("Exception [Err_Msg]: {}", e.getMessage());
        } catch(ExpiredJwtException e) {
            request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.getStatus());
            log.error("Exception [Err_Msg]: {}", e.getMessage());
        } catch(UnsupportedJwtException e) {
            request.setAttribute("exception", ErrorCode.UNSUPPORTED_TOKEN.getStatus());
            log.error("Exception [Err_Msg]: {}", e.getMessage());
        } catch(Exception e) {
            request.setAttribute("exception", ErrorCode.UNKNOWN_ERROR.getStatus());
            log.error("Exception [Err_Msg]: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
