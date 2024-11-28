/*
* Se sigue un esquema basado en el orden de marcas de tiempo (timestamps)
* Si dos subprocesos tienen el mismo numero de registro (marca de tiempo)
* se utiliza un citerio de desempate, como el identificador de subproceso (ID) mas pequeño
* */

package lamport;

import java.util.PriorityQueue;
import java.util.Comparator;

class Request {
    int timestamp;
    int threadId;

    public Request(int timestamp, int threadId) {
        this.timestamp = timestamp;
        this.threadId = threadId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "timestamp=" + timestamp +
                ", threadId=" + threadId +
                '}';
    }
}

public class LamportQueue {
    // Cola de prioridad con comparador custom para el algoritmo de lamport
    private final PriorityQueue<Request> queue;

    public LamportQueue() {
        queue = new PriorityQueue<>(new Comparator<Request>() {
            @Override
            public int compare(Request r1, Request r2) {
                // Ordenado por timestamp, luego por threadId
                if (r1.timestamp != r2.timestamp) {
                    return Integer.compare(r1.timestamp, r2.timestamp);
                }
                return Integer.compare(r1.threadId, r2.threadId);
            }
        });
    }

    // Añadir request a la cola
    public synchronized void addRequest(int timestamp, int threadId) {
        queue.offer(new Request(timestamp, threadId));
    }

    // Obtenemos el siguiente request (timestamp y threadId mas pequeños)
    public synchronized Request getNextRequest() {
        return queue.poll(); // Removemos y retornamos la cabeza de la cola
    }

    public static void main(String[] args) {
        LamportQueue lamportQueue = new LamportQueue();

        // Simulando varios hilos añadiendo requests
        lamportQueue.addRequest(10, 2);
        lamportQueue.addRequest(5, 1);
        lamportQueue.addRequest(10, 1);

        System.out.println("Processing requests in order:");
        while (true) {
            Request next = lamportQueue.getNextRequest();
            if (next == null) break;
            System.out.println("Processing: " + next);
        }
    }
}
