package com.gmail.davlong79.hazelcast.serialization;

import java.util.Date;

/**
 * Created by Casa on 04/11/2017.
 */
public interface IShoppingCart {
    long getCartId();
    void setCartId(int id);

    void setDate(Date date);

    void addItem(IShoppingCartItem item);

    int getSize();
}
