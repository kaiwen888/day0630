package com.kw.interceptor;

import com.kw.pojo.UserBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PowerInterceptor implements HandlerInterceptor {

    private List<String> exceptUrls;

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        if(exceptUrls.contains(uri)){
            return true;
        }else{
            UserBean ub = (UserBean) request.getSession().getAttribute("login");
            if(ub!=null){
                return true;
            }else{
                request.setAttribute("msg", "请先去登录！！！！");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
