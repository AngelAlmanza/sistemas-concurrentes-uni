package implementaciones;

/*
* El algoritmo Alternancia Estricta es una técnica clásica de sincronización en progrmación cocnucrente.
* La idea básica es que dos procesos (o hilos) se alternen estricatamente en su acceso a
* una sección crítica. Se utiliza una variable de control (flag) que indica a quién le toca ejecutar
* garantizando que solo uno de los procesos esté en la sección crítica a la vez.
* */

/*
* Variable turn: La variable turn indica a quién le corresponde ejecutar en la sección crítica. Si
* turn es 0, toca al Hilo 1; si es 1, le toca al Hilo 2.
*
* Espera activa (Busy-Waiting): Cada hilo entra en un ciclo while que se ejecuta hasta que sea su turno.
* Esto representa una forma de espera activa, donde el hilo sigue consumiendo CPU sin hacer nada
* útil, lo cual es ineficiente.
*
* Sección crítica: Dentro del método seccionCriticaHilo1 y seccionCriticaHilo2, los hilos entran
* en la sección critica (simulada con Thread.sleep() para simular una operación costosa). Cuando
* un hilo termina su ejecución en la sección crítica, cede el control al otro hilo cambiando el
* valor de turn.
*
* Iniciación de los hilos, los hilos t5 y t6 (ver main) se alternan en la ejecución de sus
* secciones críticas basadas en el valor de turn.
* */

public class StrictAlternance {
    // Variable para controlar de quién es el turno (0 o 1)
    // 0: Hilo 1, 1: Hilo 2
    private volatile int turn = 0;

    // Sección crítica para el hilo 1
    public void seccionCriticaHilo1 () {
        while (turn != 0) {
            // Busy-wait: el hilo 1 espera hasta que turno sea 0
            System.out.println(Thread.currentThread().getName() + " está esperando.");
        }

        // Simulación de una sección crítica ineficiente
        try {
            System.out.println(Thread.currentThread().getName() + " entrando a la sección crítica");
            // Simula una operación costosa
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " saliendo de la sección crítica");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        turn = 1;
    }

    public void seccionCriticaHilo2 () {
        // Espera activa hasta que sea el turno del hilo 2
        while (turn != 1) {
            // Busy-wait: el hilo 2 espera hasta que turn sea 1
            System.out.println(Thread.currentThread().getName() + " está esperando.");
        }

        // Simulación de una sección crítica ineficiente
        try {
            System.out.println(Thread.currentThread().getName() + " entrando a la sección crítica");
            // Simula una operación costosa
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " saliendo de la sección crítica");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        turn = 0;
    }
}
