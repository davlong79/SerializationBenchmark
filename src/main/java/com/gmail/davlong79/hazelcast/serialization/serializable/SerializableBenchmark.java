package com.gmail.davlong79.hazelcast.serialization.serializable;

import com.gmail.davlong79.hazelcast.serialization.ShoppingCartBenchmark;

/**
 * Created by Casa on 04/11/2017.
 */
public class SerializableBenchmark extends ShoppingCartBenchmark {

    public SerializableBenchmark() {
        super(ShoppingCart.class, ShoppingCartItem.class);
    }
}
