package channels;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Message {
    String content;

    public Message(String content) {
        this.content = content;
    }
}

public class PassByReferenceChannel {
    public static void main(String[] args) {
        BlockingQueue<Message> channel = new LinkedBlockingQueue<>();

        // Hilo productor
        new Thread(() -> {
            try {
                Message msg = new Message("Mensaje original");
                channel.put(msg);
                System.out.println("Mensaje enviado: " + msg.content);
                msg.content = "Mensaje modificado despues de enviar";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Hilo consumidor
        new Thread(() -> {
            try {
                Message receivedMsg = channel.take();
                System.out.println("Mensaje recibido: " + receivedMsg.content);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
