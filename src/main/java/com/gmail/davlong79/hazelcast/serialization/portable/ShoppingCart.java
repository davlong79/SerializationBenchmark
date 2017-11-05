package com.gmail.davlong79.hazelcast.serialization.portable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCart;
import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCart implements IShoppingCart, Portable {
    public long total = 0;
    public Date date;
    public long cartId;
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
    public long getCartId() {
        return cartId;
    }

    @Override
    public void setCartId(int id) {
        this.cartId = id;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void addItem(IShoppingCartItem item) {
        items.add((ShoppingCartItem) item);
        total += item.getCost() * item.getQuantity();
    }


    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 2;
    }

    @Override
    public void writePortable(PortableWriter out) throws IOException {
        out.writeLong("total", total);
        out.writeLong("date", date.getTime());
        out.writeLong("id", cartId);
        Portable[] portables = items.toArray(new Portable[] {});
        out.writePortableArray("items", portables);
    }

    @Override
    public void readPortable(PortableReader in) throws IOException {
        Portable[] portables = in.readPortableArray("items");
        items = new ArrayList<>(portables.length);
        for (Portable portable : portables) {
            items.add((ShoppingCartItem) portable);
        }
        cartId = in.readLong("id");
        total = in.readLong("total");
        date = new Date(in.readLong("date"));
    }

}
