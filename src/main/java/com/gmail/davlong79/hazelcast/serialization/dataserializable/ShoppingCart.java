package com.gmail.davlong79.hazelcast.serialization.dataserializable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCart;
import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCart implements IShoppingCart, DataSerializable {
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
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeLong(total);
        out.writeLong(date.getTime());
        out.writeLong(cartId);
        out.writeInt(items.size());
        items.forEach(item -> {
            try {
                item.writeData(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        total = in.readLong();
        date = new Date(in.readLong());
        cartId = in.readLong();
        int count = in.readInt();
        items = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ShoppingCartItem item = new ShoppingCartItem();
            item.readData(in);
            items.add(item);
        }

    }
}
