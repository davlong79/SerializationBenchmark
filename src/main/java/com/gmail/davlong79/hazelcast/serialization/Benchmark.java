package com.gmail.davlong79.hazelcast.serialization;

import com.gmail.davlong79.hazelcast.serialization.dataserializable.DataSerializableBenchmark;
import com.gmail.davlong79.hazelcast.serialization.externalizable.ExternalizableBenchmark;
import com.gmail.davlong79.hazelcast.serialization.identifieddataserializable.IdentifiedDataSerializableBenchmark;
import com.gmail.davlong79.hazelcast.serialization.portable.PortableBenchmark;
import com.gmail.davlong79.hazelcast.serialization.serializable.SerializableBenchmark;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Casa on 04/11/2017.
 */
public class Benchmark {

    public static void main(String[] args) throws IOException/*, RunnerException*/ {
        HazelcastInstance instance = null;
        try {
            instance = HazelcastClient.newHazelcastClient(new XmlClientConfigBuilder("./hazelcast-client.xml").build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        doBenchmark(new SerializableBenchmark(instance));
        doBenchmark(new ExternalizableBenchmark(instance));
        doBenchmark(new DataSerializableBenchmark(instance));
        doBenchmark(new IdentifiedDataSerializableBenchmark(instance));
        doBenchmark(new PortableBenchmark(instance));
        // doBenchmark("Unsafe ", new PortableBenchmark(true));
        // doBenchmark("Unsafe ", new PortableBenchmark(true));
        // doBenchmark(new KryoBenchmark());
        instance.shutdown();
    }

    private static void doBenchmark(ShoppingCartBenchmark sb) {
        doBenchmark("", sb);
    }

    private static void doBenchmark(String prefix, ShoppingCartBenchmark sb) {
        try {
            sb.setUp();
            TimeUnit.SECONDS.sleep(2);
            doBenchmark(prefix + sb.getClass().getSimpleName().
                    replace("Benchmark", "") + " Write Performance ", sb, () -> sb.writePerformance());
            doBenchmark(prefix + sb.getClass().getSimpleName().
                    replace("Benchmark", "") + " Read Performance  ", sb, () -> sb.readPerformance());
            sb.tearDown();
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void doBenchmark(String name, ShoppingCartBenchmark sb, Callable callable) {
        //System.out.println(name);
        //sb.setUp();
        double total = 0;
        int count = 5;
        int avgSize = 0;
        for (int i = 1; i <= count; i++) {
//            System.out.print(name + " :: " + i + " iteration: ");
            long start = System.currentTimeMillis();
            try {
                avgSize = (Integer)callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            double ops = (double)ShoppingCartBenchmark.OPERATIONS_PER_INVOCATION / (end - start);
            total += ops;
//            System.out.println(ops + " ops in ms"); // - average size " + avgSize);
        }
        //sb.tearDown();
        System.out.println(name + ":: average " + (total / count) * 1000 + " ops in sec");
    }
}
