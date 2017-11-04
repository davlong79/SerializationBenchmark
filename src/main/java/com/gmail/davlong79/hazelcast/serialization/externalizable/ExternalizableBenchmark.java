package com.gmail.davlong79.hazelcast.serialization.externalizable;

import com.gmail.davlong79.hazelcast.serialization.ShoppingCartBenchmark;

/**
 * Created by Casa on 04/11/2017.
 */
public class ExternalizableBenchmark extends ShoppingCartBenchmark {

    public ExternalizableBenchmark() {
        super(ShoppingCart.class, ShoppingCartItem.class);
    }
}
