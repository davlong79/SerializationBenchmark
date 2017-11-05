package com.gmail.davlong79.hazelcast.serialization.dataserializable;

import com.gmail.davlong79.hazelcast.serialization.ShoppingCartBenchmark;
import com.gmail.davlong79.hazelcast.serialization.identifieddataserializable.ShoppingCartDSFactory;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by Casa on 04/11/2017.
 */
public class DataSerializableBenchmark extends ShoppingCartBenchmark {

    public DataSerializableBenchmark(HazelcastInstance instance) {
        super(instance, ShoppingCart.class, ShoppingCartItem.class);
    }
}
