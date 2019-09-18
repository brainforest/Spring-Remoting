package com.howtodoinjava.server;

import com.howtodoinjava.serializer.customerservice.Dfhcommarea;
import com.howtodoinjava.serializer.customerservice.DfhcommareaTransformers;
import com.howtodoinjava.serializer.customerservice.ExportStructure;
import com.howtodoinjava.util.HexUtil;
import com.legstar.coxb.transform.HostTransformException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
   1. Mainframe Transaction invokes a Socket Call with input & output strcuture
   2. Socket Listener gets message
   3. Parse message with copybook structure
   4. Make RPC call to business method and gets response
   5. Put response into export (bean/POJO mapping)
   6. Return response to Mainframe
 */

public class EBCDICSocketReader {
    static int CHUNKSIZE = 10;

    public static byte[] PacketReader(DataInputStream dataReader) throws IOException {
        long size;
        try {
            size = dataReader.readLong();
        } catch (java.io.EOFException e) {
            return null;
        }
        long chunks = size / CHUNKSIZE;
        int lastChunk = (int) (size - (chunks * CHUNKSIZE));
        byte[] buf = new byte[(int) size];
        int off = 0;
        for (long i = 0; i < chunks; i++) {
            int read = dataReader.read(buf, off, CHUNKSIZE);
            if (read == -1) return null;
            off += CHUNKSIZE;
        }
        dataReader.read(buf, off, lastChunk);
        return buf;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        while (true) {
            Socket socket = server.accept(); //Server will be using this instance of Socket
            Thread thread = new Thread(new Reader(socket));
            thread.start();
        }
    }

    public static class Reader implements Runnable {
        Socket socket;
        int counter = 0;
        DfhcommareaTransformers transformers;
        private boolean debug = false;

        public Reader(Socket socket) {
            this.socket = socket;
            this.transformers = new DfhcommareaTransformers();
        }
        @Override
        public void run() {
            try {
                DataInputStream dataReader = new DataInputStream(socket.getInputStream());
                DataOutputStream dataWriter = new DataOutputStream(socket.getOutputStream());
                ExportStructure exportStructure = new ExportStructure();

                byte[] packet;
                while ((packet = PacketReader(dataReader)) != null) {
                    Dfhcommarea dfhcommarea = transformers.toJava(packet);
                    if (counter % 1 == 0) System.out.println("Request from Client : " + dfhcommarea.getImportStructure().toString());
                    if (debug) {
                        String format = String.format("Size=%04d Read=%s", packet.length, HexUtil.encodeHexString(packet));
                        System.out.println(format);
                    }
                    exportStructure.setComAddress(HexUtil.generateRandomChars(20));
                    exportStructure.setComName(HexUtil.generateRandomChars(20));
                    exportStructure.setComPhone(HexUtil.generateRandomChars(8));

                    dfhcommarea.setExportStructure(exportStructure);
                    dataWriter.write(transformers.toHost(dfhcommarea));
                    System.out.println("Response from Server : " + exportStructure.toString());
                    counter++;
                }
            } catch (IOException | HostTransformException e) {
                e.printStackTrace();
            }
            System.out.println("Read : " + counter);
        }
    }
}


