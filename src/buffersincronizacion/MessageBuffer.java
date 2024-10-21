package buffersincronizacion;

import java.util.LinkedList;
import java.util.Queue;

public class MessageBuffer {
    private final Queue<String> buffer;
    private final int capacity;

    public MessageBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    // Método para enviar (producir) mensajes
    public synchronized void send(String message) throws InterruptedException {
        while (buffer.size() == capacity) {
            // Si el buffer está lleno, espera hasta que haya espacio
            System.out.println("Buffer lleno. Esperando espacio...");
            wait();
        }
        // Añade el mensaje al buffer
        buffer.add(message);
        System.out.println("Mensaje enviado: " + message);
        // Notifica al receptor que puede leer
        notifyAll();
    }

    // Método para recibir (consumir) mensajes
    public synchronized String receive() throws InterruptedException {
        while (buffer.isEmpty()) {
            // Si el buffer está vacío, espera hasta que haya mensajes
            System.out.println("Buffer vacío. Esperando mensajes...");
            wait();
        }
        // Extrae el mensaje más antiguo del buffer (FIFO)
        String message = buffer.poll();
        System.out.println("Mensaje recibido: " + message);
        // Notifica al emisor que puede enviar más mensajes
        notifyAll();
        return message;
    }
}