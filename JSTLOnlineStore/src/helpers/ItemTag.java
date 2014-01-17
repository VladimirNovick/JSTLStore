package helpers;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import db.StoreItems;

public class ItemTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemName;
	
	public String getitemName() {
		return itemName;
	}

	
	public void setitemName(String name) {
		this.itemName = name;
	}

	
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		try{
			JspWriter out = pageContext.getOut();
			StoreItems store = (StoreItems) pageContext.getServletContext().getAttribute("storeItems");
			StringBuilder sb = new StringBuilder();
			
			sb.append("<tr><td><label class='ItemName'>" + store.getItem(itemName).getName() + "</label></td>");
			sb.append("<td><label class='ItemPrice'>" + store.getItem(itemName).getPrice() + "$</label></td></tr>");
			out.print(sb);
			
		}
		catch (Exception ex) {
			
			
		}
		
			return SKIP_BODY;
	}



	
	
}
