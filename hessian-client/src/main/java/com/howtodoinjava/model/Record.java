package com.howtodoinjava.model;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Record {
    protected long size;
    protected byte[] message;

    public Record() {
    }

    public byte[] build() {
        ByteBuffer data =  ByteBuffer.allocate((int) (size + 8));
        data.putLong(size);
        data.put(message);
        return data.array();
    }

    public Record(long size, byte[] message) {
        this.size =   size;
        this.message = message;
    }


    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Record{" +
                "size=" + size +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
