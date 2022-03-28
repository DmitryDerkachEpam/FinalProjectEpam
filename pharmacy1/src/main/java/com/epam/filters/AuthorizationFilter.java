package com.epam.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.entity.User;
import com.epam.pagemanager.PageManager;
import com.epam.pagemanager.PageMapper;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final List <String> PUBLIC_PATH = Arrays.asList("/jsp/login.jsp", "/jsp/registration.jsp");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(request)) {
            chain.doFilter(request, response);
        } else {
            String prevPage = ((HttpServletResponse) response).getHeader("referer");
            ((HttpServletResponse)response).sendRedirect(prevPage != null ? prevPage : PageManager.getValue(PageMapper.LOGIN_PAGE_KEY.getPageName()));
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

	private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(path -> uri.startsWith(path));
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}