package org.security.headers;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class GlobalResponseHeaderFilter implements Filter {

    private Map<String, String> headersToCheck = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig!=null && filterConfig.getInitParameterNames()!=null){
            Enumeration<String> paramNames = filterConfig.getInitParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = filterConfig.getInitParameter(paramName);
                headersToCheck.put(paramName,paramValue);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse headers = (HttpServletResponse) response;

        // Iterate through each header and set it if missing or incorrect
        for (Map.Entry<String, String> entry : headersToCheck.entrySet()) {
            String headerName = entry.getKey();
            String expectedValue = entry.getValue();

            if (!headers.containsHeader(headerName)) {
                headers.setHeader(headerName, expectedValue);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
