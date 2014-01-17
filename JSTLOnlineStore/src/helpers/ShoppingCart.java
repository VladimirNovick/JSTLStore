package helpers;

import java.util.ArrayList;

import db.Item;

public class ShoppingCart implements IShoppingCart{

	
	  private ArrayList<Item> Items = new ArrayList<Item>();

	    @Override
	    public void addItem(Item item) {
	        // TODO Auto-generated method stub
	        Items.add(item);
	    }

	    @Override
	    public void removeItem(Item item) {
	        // TODO Auto-generated method stub
	        Items.remove(item);
	    }

	    @Override
	    public Item[] getItems() {
	        // TODO Auto-generated method stub
	        Item[] i = new Item[Items.size()];
	        Items.toArray(i);
	        return i;
	    }

	    @Override
	    public void clear() {
	        // TODO Auto-generated method stub
	        if (Items != null) {
	            Items.clear();
	        }
	    }
	    
	    public int getSize() {
	    	
	    	return Items.size();
	    	
	    	}


}
