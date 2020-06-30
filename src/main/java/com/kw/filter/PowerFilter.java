package com.kw.filter;

import com.kw.pojo.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PowerFilter implements Filter {
    Set<String> noturls = new HashSet<String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String notCheckUrl = filterConfig.getInitParameter("notCheckUrl");
        for (String url : notCheckUrl.split(",")) {
            noturls.add(url.trim());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req =  (HttpServletRequest)request;
        String uri = req.getRequestURI();
        if(noturls.contains(uri)){
            chain.doFilter(request, response);
        }else{
            UserBean ub = (UserBean)req.getSession().getAttribute("login");
            if(ub!=null){
                chain.doFilter(request, response);
            }else{
                req.setAttribute("msg", "请先去登录！！！！");
                request.getRequestDispatcher("index.jsp").forward(req, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
