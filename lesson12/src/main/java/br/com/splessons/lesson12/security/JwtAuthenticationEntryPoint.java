package br.com.splessons.lesson12.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.splessons.lesson12.exception.BasicHttpStatusExceptionAdvice;
import br.com.splessons.lesson12.exception.ExceptionInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to

        LOGGER.error("Responding with unauthorized error. Message - {}", e.getMessage());
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String path = httpServletRequest.getRequestURI();

        br.com.splessons.lesson12.security.exception.AuthenticationException exception =
                new br.com.splessons.lesson12.security.exception.AuthenticationException();

        ExceptionInfo unauthorized =
                BasicHttpStatusExceptionAdvice.createAppExceptionInfo(path, HttpStatus.UNAUTHORIZED, exception);

        String output = OBJECT_MAPPER.writeValueAsString(unauthorized);

        PrintWriter pw = httpServletResponse.getWriter();
        pw.print(output);
        pw.flush();
        pw.close();
    }
}