package com.gmail.davlong79.hazelcast.serialization;

import com.gmail.davlong79.hazelcast.serialization.externalizable.ExternalizableBenchmark;
import com.gmail.davlong79.hazelcast.serialization.serializable.SerializableBenchmark;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Casa on 04/11/2017.
 */
public class Benchmark {

    public static void main(String[] args) throws IOException/*, RunnerException*/ {
        doBenchmark(new SerializableBenchmark());
        doBenchmark(new ExternalizableBenchmark());
        // doBenchmark(new DataSerializableBenchmark());
        // doBenchmark(new IdentifiedDataSerializableBenchmark(false));
        // doBenchmark("Unsafe ", new IdentifiedDataSerializableBenchmark(true));
        // doBenchmark(new PortableBenchmark(false));
        // doBenchmark("Unsafe ", new PortableBenchmark(true));
        // doBenchmark(new KryoBenchmark());
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
                    replace("Benchmark", "") + " Read Performance", sb, () -> sb.readPerformance());
            sb.tearDown();
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void doBenchmark(String name, ShoppingCartBenchmark sb, Callable callable) {
        System.out.println(name);
        //sb.setUp();
        long total = 0;
        int count = 5;
        int avgSize = 0;
        for (int i = 1; i <= count; i++) {
            System.out.print(name + " :: " + i + " iteration: ");
            long start = System.currentTimeMillis();
            try {
                avgSize = (Integer)callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            long ops = (long) ShoppingCartBenchmark.OPERATIONS_PER_INVOCATION / (end - start);
            total += ops;
            System.out.println(ops + " ops in ms - average size " + avgSize);
        }
        //sb.tearDown();
        System.out.println(name + ":: average " + total / count + " ops in ms");
    }
}
