package helpers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/Controller")
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        if ((httpReq.getSession(false) == null) && (stringContainsItemFromList(request.getParameter("command"), new String[]{"removeItem", "clear", "showItems", "buy", ""}))) {
            System.out.println("Redirect to Home");
            httpRes.sendRedirect("/JSTLOnlineStore");
        }
        else{
		// pass the request along the filter chain
		chain.doFilter(request, response);
        }
	}
	
	 public static boolean stringContainsItemFromList(String inputString, String[] items) {


	        for (int i = 0; i < items.length; i++) {
	            if (inputString.equals(items[i])) {
	                return true;
	            }
	        }
	        return false;
	    }
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
