package com.gmail.davlong79.hazelcast.serialization.externalizable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;

import java.io.*;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCartItem implements IShoppingCartItem, Externalizable {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(cost);
        out.writeInt(quantity);
        out.writeUTF(itemName);
        out.writeBoolean(inStock);
        out.writeUTF(url);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        cost = in.readLong();
        quantity = in.readInt();
        itemName = in.readUTF();
        inStock = in.readBoolean();
        url = in.readUTF();
    }
}
