package com.gmail.davlong79.hazelcast.serialization.identifieddataserializable;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

/**
 * Created by davide.longo on 05/11/2017.
 */
public class ShoppingCartDSFactory implements DataSerializableFactory {

    @Override
    public IdentifiedDataSerializable create(int i) {
        switch (i) {
            case 1:
                return new ShoppingCartItem();
            case 2:
                return new ShoppingCart();
            default:
                return null;
        }
    }
}
