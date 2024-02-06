package CSS.ReservationSystem.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Integer exception = (Integer) request.getAttribute("exception");

        if (exception == null) {
            setResponse(response, ErrorCode.UNKNOWN_ERROR);
        } else if (exception == 4002) {
            setResponse(response, ErrorCode.WRONG_TYPE_TOKEN);
        } else if (exception == 4003) {
            setResponse(response, ErrorCode.EXPIRED_TOKEN);
        } else if (exception == 4004) {
            setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
        } else {
            setResponse(response, ErrorCode.ACCESS_DENIED);
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json; charset=UTF-8");

        JSONObject responseJson = new JSONObject();
        responseJson.put("status code", errorCode.getStatus());
        responseJson.put("error message", errorCode.getMessage());

        response.getWriter().print(responseJson);
    }
}
