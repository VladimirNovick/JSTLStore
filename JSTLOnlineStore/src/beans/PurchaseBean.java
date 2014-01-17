package beans;

import db.Item;

public class PurchaseBean {

	
	private Item[] Items;
	private float total;
	
	public Item[] getItems() {
		return Items;
	}
	public void setItems(Item[] items) {
		Items = items;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	
	
}
