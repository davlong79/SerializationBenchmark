package com.gmail.davlong79.hazelcast.serialization;

/**
 * Created by Casa on 04/11/2017.
 */
public interface IShoppingCartItem {
    long getCost();
    void setCost(long cost);
    int getQuantity();
    void setQuantity(int qty);
    void setItemName(String itemName);
    void setInStock(boolean inStock);
    void setUrl(String url);
}
