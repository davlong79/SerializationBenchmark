package com.gmail.davlong79.hazelcast.serialization.portable;

import com.gmail.davlong79.hazelcast.serialization.ShoppingCartBenchmark;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by Casa on 04/11/2017.
 */
public class PortableBenchmark extends ShoppingCartBenchmark {

    public PortableBenchmark(HazelcastInstance instance) {
        super(instance, ShoppingCart.class, ShoppingCartItem.class);

//        instance.getConfig().getSerializationConfig().addDataSerializableFactory(1, new ShoppingCartPortableFactory());
    }
}
