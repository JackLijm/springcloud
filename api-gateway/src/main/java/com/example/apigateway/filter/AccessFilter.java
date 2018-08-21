package com.example.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul的请求过滤功能，简单的过滤器据称ZuulFilter即可
 */
public class AccessFilter  extends ZuulFilter{
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {
        //过滤器拦截生命周期
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤器执行顺序,值越小优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //过滤器是否需要被执行
        return true;
    }

    @Override
    //过滤逻辑
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("Send {} request to {} ",request.getMethod(),request.getRequestURL().toString());

        Object accessToken =request.getParameter("accessToken");
        if(accessToken == null){
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
