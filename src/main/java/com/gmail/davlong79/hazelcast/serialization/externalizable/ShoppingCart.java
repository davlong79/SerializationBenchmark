package com.gmail.davlong79.hazelcast.serialization.externalizable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCart;
import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCart implements IShoppingCart, Externalizable {
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
        items.add((ShoppingCartItem) item);
        total += item.getCost() * item.getQuantity();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(total);
        out.writeLong(date.getTime());
        out.writeLong(id);
        out.writeInt(items.size());
        items.forEach(item -> {
            try {
                item.writeExternal(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        total = in.readLong();
        date = new Date(in.readLong());
        id = in.readLong();
        int count = in.readInt();
        items = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ShoppingCartItem item = new ShoppingCartItem();
            item.readExternal(in);
            items.add(item);
        }
    }
}
