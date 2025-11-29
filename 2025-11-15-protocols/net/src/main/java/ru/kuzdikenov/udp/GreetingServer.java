package ru.kuzdikenov.udp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class GreetingServer extends Thread implements Closeable {

    public static final int PORT = 5556;
    private DatagramSocket socket;
    private volatile boolean alive = true;
    private byte[] buffer = new byte[512];

    public GreetingServer() {
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (alive) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress address = packet.getAddress();
                int port = packet.getPort(); // получаем порт из пришедшего пакета,
                // может быть полезно, если сервер слушает на нескольких портах

                byte[] data = message.getBytes(StandardCharsets.UTF_8);
                packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);

                if ("bye".equalsIgnoreCase(message.trim())) {
                    alive = false;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        close();
    }

    @Override
    public void close() {
        socket.close();
    }
}
