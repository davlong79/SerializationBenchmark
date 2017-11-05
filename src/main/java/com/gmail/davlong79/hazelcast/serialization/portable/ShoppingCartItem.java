package com.gmail.davlong79.hazelcast.serialization.portable;

import com.gmail.davlong79.hazelcast.serialization.IShoppingCartItem;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCartItem implements IShoppingCartItem, Portable {
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
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 1;
    }

    @Override
    public void writePortable(PortableWriter out) throws IOException {
        out.writeLong("cost", cost);
        out.writeInt("quantity", quantity);
        out.writeUTF("name", itemName);
        out.writeBoolean("stock", inStock);
        out.writeUTF("url", url);
    }

    @Override
    public void readPortable(PortableReader in) throws IOException {
        url = in.readUTF("url");
        quantity = in.readInt("quantity");
        cost = in.readLong("cost");
        inStock = in.readBoolean("stock");
        itemName = in.readUTF("name");
    }

}
