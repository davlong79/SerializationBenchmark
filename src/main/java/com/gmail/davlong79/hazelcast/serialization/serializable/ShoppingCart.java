package com.gmail.davlong79.hazelcast.serialization.serializable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCart;
import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCart implements IShoppingCart, Serializable {
    public long total = 0;
    public Date date;
    public long id;
    private List<ShoppingCartItem> items = new ArrayList<>();

    public void removeItem(int index) {
        ShoppingCartItem item = items.remove(index);
        total -= item.cost * item.quantity;
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void addItem(IShoppingCartItem item) {
        items.add((ShoppingCartItem)item);
        total += item.getCost() * item.getQuantity();
    }
}
