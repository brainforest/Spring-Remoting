package com.howtodoinjava.mainframe;

import com.howtodoinjava.exception.ExceptionHander;
import com.howtodoinjava.model.Record;
import com.howtodoinjava.serializer.customerservice.Dfhcommarea;
import com.howtodoinjava.serializer.customerservice.DfhcommareaTransformers;
import com.legstar.coxb.transform.HostTransformException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Transport {

    private Socket socket;
    protected DataOutputStream dataWriter ;
    protected DataInputStream dataReader;

    public Transport(String hostName, int port) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHander());
        try {
            socket = new Socket(hostName, port);
        } catch (java.net.ConnectException e) {
            System.out.println("Cannot connect Kafka Connect Server : " + e.getMessage());
            System.exit(-1);
        }
        dataWriter = new DataOutputStream(socket.getOutputStream());
        dataReader = new DataInputStream(socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public Dfhcommarea send(Record record) throws IOException, HostTransformException {
        DfhcommareaTransformers transformers = new DfhcommareaTransformers();
        byte[] response = new byte[54];
        dataWriter.write(record.build());
        dataReader.read(response);
        Dfhcommarea dfhcommarea = transformers.toJava(response);
        return dfhcommarea;
    }


}
