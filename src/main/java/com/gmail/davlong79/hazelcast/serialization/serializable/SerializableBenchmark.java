package com.gmail.davlong79.hazelcast.serialization.serializable;

import com.gmail.davlong79.hazelcast.serialization.ShoppingCartBenchmark;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by Casa on 04/11/2017.
 */
public class SerializableBenchmark extends ShoppingCartBenchmark {

    public SerializableBenchmark(HazelcastInstance instance) {
        super(instance, ShoppingCart.class, ShoppingCartItem.class);
    }
}
