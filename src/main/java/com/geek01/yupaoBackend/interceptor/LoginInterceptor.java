package com.geek01.yupaoBackend.interceptor;

import com.geek01.yupaoBackend.common.ErrorCode;
import com.geek01.yupaoBackend.constant.UserConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在调用后端业务逻辑方法前进行拦截处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(UserConstant.USER_LOGIN_INFO) != null) {
            return true;
        }
        response.setStatus(ErrorCode.NOT_LOGIN.getCode());
        response.setContentType("application/json;charset=utf-8");
        String errorMessage = "\"error\":\"用户未登录，请先登录!\"";
        response.getWriter().write(errorMessage);
        /* 立即将缓冲区数据返回给前端 response.getWriter().flush();*/
        return false;
    }
}
