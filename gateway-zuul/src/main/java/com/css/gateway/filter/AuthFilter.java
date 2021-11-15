package com.css.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class AuthFilter extends ZuulFilter {
    /*
     * 过滤器类型：前置（pre）
     * */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /*
     * 过滤器顺序（越小越先执行）
     * */
    @Override
    public int filterOrder() {
        return 4;
    }

    /*
     * 过滤器是否生效逻辑
     * true是拦截，进入run方法
     * false是放行，进行后续逻辑
     * */
    @Override
    public boolean shouldFilter() {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getRequestURL());
//        if ("/apigateway/api/consumer/hello".equalsIgnoreCase(request.getRequestURI())){
//            return true;
//        }
        return true;
    }

    /*
     * 业务逻辑
     * */
    @Override
    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        HttpServletResponse response = requestContext.getResponse();
//        response.setContentType("text/html;charset=UTF-8");
//        String requestURI = request.getRequestURI();
//        System.out.println(requestURI+"拦截");
//        String authorization = request.getHeader("Authorization");
//        //校验失败
//        if (StringUtils.isEmpty(authorization)){
//            //请求不继续走了
//            requestContext.setSendZuulResponse(false);
//            //设置请求上下文的返回信息
//            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            requestContext.setResponseBody("需要身份验证");
//            return null;
//        }
        return null;
    }
}
