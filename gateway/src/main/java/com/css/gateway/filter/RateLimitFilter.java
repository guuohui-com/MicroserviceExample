package com.css.gateway.filter;


import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class RateLimitFilter extends ZuulFilter {

    //令牌桶每秒1000个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -4;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        ///apigateway/api/consumer/hello 对此资源路径进行限流
        if (request.getRequestURI().equalsIgnoreCase("/apigateway/api/consumer/hello")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
//        //拿令牌
//        if (!RATE_LIMITER.tryAcquire()){
//            //不能继续走下游服务
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
//            requestContext.setResponseBody(HttpStatus.TOO_MANY_REQUESTS.toString());
//        }
        return null;
    }
}
