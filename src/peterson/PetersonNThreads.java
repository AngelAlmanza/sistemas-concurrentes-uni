/*
* La version original del algoritmo de Peterson estaba pensada para 2 hilos
* para hacerlo con N cantidad de hilos se requiere hacer uso de una jerarquia de niveles
* similar al algoritmo de Lamport
*
* Cada hilo tiene un nivel en la jerarquia, indicando su itencion de ingresar a la seccion critica
* Para cada nivel, hay un "hilo victima" que puede esperar a que los demas abandonen ese nivel
* Cada hilo pasa por niveles desde 0 hasta N-1, esperando mientras haya conflicto con otros hilos
* Al salir de la seccion critica, el hilo abandona todos los niveles (level[threadId] = -1)
* */

package peterson;

import java.util.concurrent.atomic.AtomicInteger;

public class PetersonNThreads {
    private final int numThreads; // Número de hilos
    private final AtomicInteger[] level; // Nivel del hilo en la jerarquía
    private final AtomicInteger[] victim; // Víctima de cada nivel

    public PetersonNThreads(int numThreads) {
        this.numThreads = numThreads;
        level = new AtomicInteger[numThreads];
        victim = new AtomicInteger[numThreads - 1]; // Niveles necesarios: N-1

        for (int i = 0; i < numThreads; i++) {
            level[i] = new AtomicInteger(-1); // Nivel inicial de cada hilo
        }
        for (int i = 0; i < numThreads - 1; i++) {
            victim[i] = new AtomicInteger(-1); // Ninguna víctima inicial
        }
    }

    public void lock(int threadId) {
        for (int i = 0; i < numThreads - 1; i++) {
            level[threadId].set(i); // Anunciar nivel actual
            victim[i].set(threadId); // Declararse como víctima

            // Esperar mientras haya otro hilo en el mismo o mayor nivel y soy la víctima
            while (isConflict(threadId, i)) {
                // Espera activa
            }
        }
    }

    public void unlock(int threadId) {
        level[threadId].set(-1); // Salir de la jerarquía
    }

    private boolean isConflict(int threadId, int currentLevel) {
        for (int j = 0; j < numThreads; j++) {
            if (j != threadId && level[j].get() >= currentLevel && victim[currentLevel].get() == threadId) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final int numThreads = 3;
        PetersonNThreads peterson = new PetersonNThreads(numThreads);

        Runnable criticalSection = () -> {
            int threadId = Integer.parseInt(Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                peterson.lock(threadId);

                System.out.println("Thread " + threadId + " is in critical section.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Thread " + threadId + " is leaving critical section.");

                peterson.unlock(threadId);
            }
        };

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(criticalSection, String.valueOf(i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
