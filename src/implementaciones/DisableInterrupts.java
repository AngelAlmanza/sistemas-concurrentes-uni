package implementaciones;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* El algoritmo "Deshabilitar Interrupciones" en sistemas concurrentes o de tiempo real es una
* técnica utilizada para proteger secciones críticas de código de ser interrumpidas por eventos asíncronos
* (como interrupciones de hardware o software). Esta técnica no es eficiente en la mayoría de los
* sistemas operativos modernos porque puede impactar negativamente el rendimiento y el escalado del sistema
* al desactivar completamente las interrupciones.
* */

/*
* La función seccionCritica simula una parte del codigo que no deberia ser interrumpida
* mientras esta en ejecucion.
*
* Utilizamos un ReentrantLock para deshabilidat las "interrupciones" de otros hilos
* mientras un hilo esta en la sección crítica. Cunado un hilo obtiene el lock, los otros que
* intentan acceder a la sección critica serán bloqueados hasta que se libere el lock.
*
* Java no permite deshabilidar interrupciones de hardware, pero podemos simular interupciones
* de hilos con Thread.sleep() y InterruptedException.
* */

public class DisableInterrupts {
    private Lock lock = new ReentrantLock();

    public void constructor () {}

    // Sección crítica
    public void seccionCritica() {
        // Deshabilitar "interrupciones" (obtener el lock)
        this.lock.lock();

        try {
            // Simulación de una sección crítica ineficiente
            System.out.println("Entrando a la sección crítica. Deshabilitando interrupciones\n");
            // Simulamos una operación ineficiente
            try {
                // Simula una operación costosa
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Rehabilidat interupciones del hilo
                Thread.currentThread().interrupt();
                System.out.println("Interrupción detectada!\n");
            }
            System.out.println("Saliendo de la sección crítica\n\n");
        } finally {
            // Habilitar interrupciones
            lock.unlock();
        }
    }
}
