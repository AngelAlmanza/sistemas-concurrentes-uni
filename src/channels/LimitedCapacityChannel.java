package channels;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LimitedCapacityChannel {
    public static void main(String[] args) {
        BlockingQueue<String> channel = new LinkedBlockingQueue<>(2);

        // Hilo productor
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Produciendo mensaje " + i);
                    channel.put("Mensaje " + i);
                    System.out.println("Mensaje " + i + " puesto en canal");
                }
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
