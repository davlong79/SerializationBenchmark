package com.gmail.davlong79.hazelcast.serialization;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by Casa on 04/11/2017.
 */
public class ShoppingCartBenchmark {

    public static final int OPERATIONS_PER_INVOCATION = 10000;
    private int maxOrders;
    private int maxCartItems;
    private Map<Long, IShoppingCart> cartMap;
    private Class shoppingCartClass;
    private Class shoppingCartItemClass;

    public ShoppingCartBenchmark(Class _shoppingCartClass, Class _shoppingCartItemClass) {
        maxOrders = 1000;
        maxCartItems = 1000;

        shoppingCartClass = _shoppingCartClass;
        shoppingCartItemClass = _shoppingCartItemClass;
    }

    public void setUp() {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        cartMap = instance.getMap("shoppingCart");
    }

    public void tearDown() {
        Hazelcast.shutdownAll();
    }

    //@Override
    public int writePerformance() {
        Random random = new Random();
        int totalSize = 0;
        for (int k = 0; k < OPERATIONS_PER_INVOCATION; k++) {
            IShoppingCart cart = createNewShoppingCart(random);
            totalSize += cart.getSize();
            cartMap.put(cart.getId(), cart);
        }
        return totalSize / OPERATIONS_PER_INVOCATION;
    }

    //@Override
    public int readPerformance() {
        Random random = new Random();
        int totalSize = 0;
        for (int k = 0; k < OPERATIONS_PER_INVOCATION; k++) {
            long orderId = random.nextInt(maxOrders);
            IShoppingCart cart = cartMap.get(orderId);
            totalSize += cart.getSize();
        }
        return totalSize / OPERATIONS_PER_INVOCATION;
    }

    private IShoppingCart createNewShoppingCart(Random random) {
        IShoppingCart cart = null;
        try {
            cart = (IShoppingCart)shoppingCartClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        cart.setId(random.nextInt(maxOrders));
        cart.setDate(new Date());
        int count = random.nextInt(maxCartItems);
        for (int k = 0; k < count; k++) {
            IShoppingCartItem item = createNewShoppingCartItem(random);
            cart.addItem(item);
        }
        return cart;
    }

    private IShoppingCartItem createNewShoppingCartItem(Random random) {
        int i = random.nextInt(10);
        IShoppingCartItem item = null;
        try {
            item = (IShoppingCartItem)shoppingCartItemClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        item.setCost(i * 9);
        item.setQuantity(i % 2);
        item.setItemName("item_" + i);
        item.setInStock(i == 9);
        item.setUrl("http://www.amazon.com/gp/product/" + i);
        return item;
    }
}
