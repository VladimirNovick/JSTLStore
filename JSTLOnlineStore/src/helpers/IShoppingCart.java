package helpers;

import db.Item;

import java.util.ArrayList;

public interface IShoppingCart {

    ArrayList<Item> Items = null;

    public void addItem(Item item);

    public void removeItem(Item item);

    public Item[] getItems();

    public void clear();

}

