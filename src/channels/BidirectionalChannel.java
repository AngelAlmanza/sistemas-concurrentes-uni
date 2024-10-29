package channels;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BidirectionalChannel {
    public static void main(String[] args) {
        BlockingQueue<String> channel1 = new LinkedBlockingQueue<String>();
        BlockingQueue<String> channel2 = new LinkedBlockingQueue<>();

        // Hilo productor
        new Thread(() -> {
            try {
                channel1.put("Mensaje de productor a consumidor");
                String response = channel2.take();
                System.out.println("Respuesta recibida en productor " + response);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Hilo consumidor
        new Thread(() -> {
            try {
                String message = channel1.take();
                System.out.println("Mensaje recibido en consumidor: " + message);
                channel2.put("Respuesta de consumidor a productor");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
