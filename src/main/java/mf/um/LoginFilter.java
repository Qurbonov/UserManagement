package mf.um;

import mf.um.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qurbonov on 9/28/2015.
 */
public class LoginFilter implements Filter {
    public static final String USER_KEY = "USER_KEY";
    private static List<String> publicUrls = Arrays.asList(
            "/login", "/css/", "/fonts/", "/img/", "/js/", "/logout"
    );
    private ApplicationContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UsersService usersService = context.getBean(UsersService.class);
        if (!isPublicUrl(req.getRequestURI()) && session.getAttribute(USER_KEY) == null) {
            res.sendRedirect("/login?from=" + req.getRequestURI());
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isPublicUrl(String url) {
        for (String publicUrl : publicUrls) {
            if (url.startsWith(publicUrl)) return true;
        }
        return false;
    }
}
