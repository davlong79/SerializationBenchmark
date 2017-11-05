package com.gmail.davlong79.hazelcast.serialization.identifieddataserializable;

import com.gmail.davlong79.hazelcast.serialization.ShoppingCartBenchmark;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by Casa on 04/11/2017.
 */
public class IdentifiedDataSerializableBenchmark extends ShoppingCartBenchmark {

    public IdentifiedDataSerializableBenchmark(HazelcastInstance instance) {
        super(instance, ShoppingCart.class, ShoppingCartItem.class);

//        instance.getConfig().getSerializationConfig().addDataSerializableFactory(1, new ShoppingCartPortableFactory());
    }
}
