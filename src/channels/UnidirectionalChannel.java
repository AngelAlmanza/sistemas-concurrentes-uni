package channels;

import java.util.concurrent.SynchronousQueue;

public class UnidirectionalChannel {
    public static void main(String[] args) {
        SynchronousQueue<String> channel = new SynchronousQueue<>();

        // Hilo productor
        new Thread(() -> {
            try {
                System.out.println("Produciendo mensaje...");
                channel.put("Mensaje síncrono");
                System.out.println("Mensaje enviado");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Hilo consumidor
        new Thread(() -> {
            try {
                String message = channel.take();
                System.out.println("Mensaje recibido " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
