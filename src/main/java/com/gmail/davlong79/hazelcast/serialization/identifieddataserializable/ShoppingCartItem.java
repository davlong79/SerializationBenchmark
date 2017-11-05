package com.gmail.davlong79.hazelcast.serialization.identifieddataserializable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCartItem implements IShoppingCartItem, IdentifiedDataSerializable {
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
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeLong(cost);
        out.writeInt(quantity);
        out.writeUTF(itemName);
        out.writeBoolean(inStock);
        out.writeUTF(url);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        cost = in.readLong();
        quantity = in.readInt();
        itemName = in.readUTF();
        inStock = in.readBoolean();
        url = in.readUTF();
    }

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getId() {
        return 1;
    }
}
