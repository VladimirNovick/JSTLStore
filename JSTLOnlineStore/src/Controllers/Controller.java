package Controllers;

import helpers.ShoppingCart;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import beans.PurchaseBean;
import db.Item;
import db.ItemNotFoundException;
import db.StoreItems;

/**
 * Servlet implementation class Controller
 */
@WebServlet(
		urlPatterns = { "/Controller" }, 
		initParams = { 
				@WebInitParam(name = "email", value = "support@online.store")
		})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String supportMail;
    private int hitCounter;
    private StoreItems store;
    private ServletContext context;
    private static final String COMMAND_PARAM = "command";
    private static final String START_SHOPPING_CMD = "startShopping";
    private static final String ADD_CMD = "addItem";
    private static final String CLEAR_CMD = "clear";
    private static final String BUY_CMD = "buy";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	public void init(ServletConfig config) throws ServletException {
		 context = config.getServletContext();
	     context.setAttribute("storeItems", new StoreItems());
	     hitCounter = 0;
	     context.setAttribute("hits",hitCounter);
	     supportMail = config.getInitParameter("email");
		// TODO Auto-generated method stub
		super.init();
	}



	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter(COMMAND_PARAM);
		if (command != null) {
		switch (command) {
		case START_SHOPPING_CMD:
			{
			HttpSession session = request.getSession(true);
			if (session.isNew()) {
                ShoppingCart cart = new ShoppingCart();
                session.setAttribute(session.getId(), cart);
            }
			}
			break;

		case CLEAR_CMD:{
			HttpSession session = request.getSession();
            if (session != null) {
                ShoppingCart cart = (ShoppingCart) session.getAttribute(session.getId());
                cart.clear();
            }
			}
			break;
			
		case ADD_CMD: {
			 HttpSession session = request.getSession();
             if (session != null) {
                 ShoppingCart cart = (ShoppingCart) session.getAttribute(session.getId());

                 Map<String, String[]> params = request.getParameterMap();
                 for (String parameter : params.keySet()) {
                     try {
                         if (!parameter.equals("command")) {
                             store = (StoreItems) context.getAttribute("storeItems");
                             Item item = store.getItem(request.getParameter(parameter));
                             cart.addItem(item);
                         }
                     } catch (ItemNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }

                 }

             }
         	break;
		}
	
		
		case BUY_CMD:{
			HttpSession session = request.getSession();
            if (session != null) {
                ShoppingCart cart = (ShoppingCart) session.getAttribute(session.getId());
                PurchaseBean purchase = new PurchaseBean();
                purchase.setItems(cart.getItems());
				for(Item item : cart.getItems()){
					purchase.setTotal(purchase.getTotal() + item.getPrice());
				}
				++hitCounter;
				context.setAttribute("hits",hitCounter);
				request.setAttribute("purchase", purchase);
            }
			}
			break;
			
		}
		
		}
		context.getRequestDispatcher("/ViewManager").forward(request, response);
	}

}
