package com.gmail.davlong79.hazelcast.serialization.portable;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

/**
 * Created by davide.longo on 05/11/2017.
 */
public class ShoppingCartPortableFactory implements PortableFactory {

    @Override
    public Portable create(int i) {
        switch (i) {
            case 1: return new ShoppingCartItem();
            case 2: return new ShoppingCart();
            default: return null;
        }
    }
}
