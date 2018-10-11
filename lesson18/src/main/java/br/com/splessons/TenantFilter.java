package br.com.splessons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TenantFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(TenantFilter.class);

    public static final String TENANT_HTTP_HEADER = "X-Tenant";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        logger.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());

        String targetTenantId = req.getHeader(TENANT_HTTP_HEADER);

        TenantContext.set(targetTenantId);

        try {
            filterChain.doFilter(req, res);
        } finally {
            TenantContext.set(null);
        }


        logger.info(
                "Logging Response :{}",
                res.getContentType());
    }
}
