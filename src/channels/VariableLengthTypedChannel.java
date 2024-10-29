package channels;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class VariableLengthTypedChannel {
    public static void main(String[] args) {
        BlockingQueue<String> channel = new LinkedBlockingQueue<>();

        // Hilo productor
        new Thread(() -> {
            try {
                channel.put("Mensaje corto");
                channel.put("Este es un mensaje mucho mas largo que el anterior");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Hilo consumidor
        new Thread(() -> {
            try {
                while (true) {
                    String message = channel.take();
                    System.out.println("Mensaje recibido: " + message);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
