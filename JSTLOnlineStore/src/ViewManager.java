

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewManager
 */
@WebServlet("/ViewManager")
public class ViewManager extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private ServletContext context;

	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ViewManager() {
	        super();

	        // TODO Auto-generated constructor stub
	    }

	    /**
	     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String command = request.getParameter("command");
	        switch (command) {
	            case "browseStore":
	                context.setAttribute("nextPage", "/browseStore.jspx");
	                break;
	            case "viewItemsByCategory":
	                context.setAttribute("nextPage", "/browselItems.jspx?category=" + request.getParameter("category"));
	                break;
	            case "startShopping":
	                context.setAttribute("nextPage", "/shopping.jspx");
	                break;
	            case "viewCart":
	                context.setAttribute("nextPage", "/viewCart.jspx");
	                break;
	            case "addItem":
	                context.setAttribute("nextPage", "/viewCart.jspx");
	                break;
	            case "buy":
	                context.setAttribute("nextPage", "/buy.jspx");
	                break;
	        }


	        context.getRequestDispatcher((String) context.getAttribute("nextPage")).forward(request, response);

	    }

	    @Override
	    public void init(ServletConfig config) throws ServletException {
	        context = config.getServletContext();
	        context.setAttribute("nextPage", new String());
	    }


	}