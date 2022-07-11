package com.geekshirt.orderservice.filter;

import com.geekshirt.orderservice.context.UserContext;
import com.geekshirt.orderservice.context.UserContextHolder;
import com.geekshirt.orderservice.util.HeadersEnum;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String correlationIdValue = httpServletRequest.getHeader(HeadersEnum.TRACKING_CORRELATION_ID.value());

        UserContext userCtx = UserContextHolder.getContext();
        userCtx.setCorrelationId(correlationIdValue);
        UserContextHolder.setContext(userCtx);

        chain.doFilter(request, response);
     }
}
