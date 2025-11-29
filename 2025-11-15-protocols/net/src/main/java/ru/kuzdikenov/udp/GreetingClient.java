package ru.kuzdikenov.udp;

import java.io.Closeable;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static ru.kuzdikenov.udp.GreetingServer.PORT;

public class GreetingClient implements Closeable {

    private final DatagramSocket socket;
    private final InetAddress address;
    private byte[] buffer;

    public GreetingClient() {
        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getLocalHost();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public String send(String message) {

        try {
            buffer = message.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);

            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            return new String(packet.getData(), 0, packet.getLength());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void close() {
        socket.close();
    }
}
