package com.xg.cctv.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xg.cctv.common.StatusCode;
import com.xg.cctv.common.util.R;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 检查是否是请求了登陆URL(实际上当没有登陆时都会重定向到此URL)
        // login url : /index.html
        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            //String requestURI = this.getPathWithinApplication(request);
            // 根路径重定向到login url
            if (this.pathsMatch("/", request)){
                // 重定向到登陆页面
                this.redirectToLogin(request, response);
            }else {
                // 被拦截的请求统一响应JSON格式
                this.send(response , "你还没有登陆");
            }

            return false;
        }
    }

    private void send(ServletResponse response , String msg) throws Exception {
        R projectError = R.error()
                .put("code" , StatusCode.NOT_LOGGED_IN)
                .put("msg" , msg);
        HttpServletResponse resp = WebUtils.toHttp(response);
        resp.setContentType("text/html;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(projectError));
        out.flush();
        out.close();
    }


    /**
     * 对跨域提供支持
     */
    /*@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }*/
}
