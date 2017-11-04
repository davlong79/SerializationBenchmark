package com.gmail.davlong79.hazelcast.serialization.serializable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;

import java.io.Serializable;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCartItem implements IShoppingCartItem, Serializable {
    public long cost;
    public int quantity;
    public String itemName;
    public boolean inStock;
    public String url;

    @Override
    public long getCost() {
        return cost;
    }

    @Override
    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int qty) {
        this.quantity = qty;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}
