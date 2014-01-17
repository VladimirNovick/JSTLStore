

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String LastVisitDate;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public Cookie addNewCookie(String Name) {
        Cookie responseCookie = new Cookie(Name, new Date().toString());
        responseCookie.setMaxAge(604800);
        return responseCookie;
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setAttribute("username", request.getParameter("userName"));
			Cookie[] cookies = request.getCookies();
			 if (cookies != null) {
		            for (Cookie cookie : cookies) {
		                if (cookie.getName().equals("LastLogin")) {
		                    LastVisitDate = cookie.getValue();
		                }
		            }
		        } else {
		            response.addCookie(addNewCookie("LastLogin"));
		        }
		        request.setAttribute("lastVisit", LastVisitDate);
		        
		   if (request.getSession() != null) request.getSession().invalidate();
		   response.addCookie(addNewCookie("LastLogin"));
		   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jspx");
		   dispatcher.forward(request, response);

		
	}
	
	

}
