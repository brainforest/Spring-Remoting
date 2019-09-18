package com.howtodoinjava.mainframe;


import com.howtodoinjava.model.Record;
import com.howtodoinjava.serializer.customerservice.Dfhcommarea;
import com.howtodoinjava.serializer.customerservice.DfhcommareaTransformers;
import com.howtodoinjava.serializer.customerservice.ImportStructure;
import com.legstar.coxb.transform.HostTransformException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainframeEBCDICDataProducer {

    public static void main(String[] args) throws HostTransformException, InterruptedException {
        byte[] buffer;
        final List<Record> records = new ArrayList<>();

        DfhcommareaTransformers transformers = new DfhcommareaTransformers();
        Dfhcommarea dfhcommarea = new Dfhcommarea();
        ImportStructure importStructure = new ImportStructure();

        for (int i = 0; i< 100; i++) {
            importStructure.setComNumber(Long.valueOf(i));
            dfhcommarea.setImportStructure(importStructure);
            buffer = transformers.toHost(dfhcommarea);
            Record record = new Record(buffer.length, buffer);
            records.add(record);
        }

        int numOfThreads = 10;
        final int numberOfMessage = 10;
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new Thread(() -> {
                Transport transport;
                try {
                    transport = new Transport("localhost", 12345);
                    for (int j = 0; j < numberOfMessage; j++) {
                        int index = (int) (Math.random() * 100);
                        Dfhcommarea received = transport.send(records.get(index));
                        System.out.println(received.toString());
                    }
                } catch (IOException | HostTransformException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < numOfThreads; i++) threads[i].join();
        long end = System.currentTimeMillis();
        String msg = String.format("%s Messages has been processed @ %s sec, avarege %s req/s", (numberOfMessage * numOfThreads), (end - start) / 1000.0, (long) (numberOfMessage * numOfThreads * 1000) / (end - start));
        System.out.println(msg);
    }
}
