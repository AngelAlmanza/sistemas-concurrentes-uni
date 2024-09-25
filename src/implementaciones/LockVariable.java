package implementaciones;

/*
* El algoritmo "Variables de Candado" es una técnica utilizada para la sincronización en sistemas
* concurrentes, se utiliza una variable de control para permitir o denegar el acceso a una sección crítica.
* Esta variable de candado indica si la sección crítica está siendo utilizada por algún hilo o si está disponible
* */

/*
* Variable inUse es una variable booleana que actúa como el "candado". Si es true, significa que la
* sección crítica está ocupada por otro hilo, si es false, significa que está disponible.
*
* Busy-Waiting (espera activa): la espera activa ocurre en el ciclo while. Aqui, un hilo esperará
* activamente (sin ceder el control del procesador) hasta que el candado esté disponible. Esto es
* ineficiente, ya que el hilo consume CPU sin realizar ningún trabajo útil mientras espera.
*
* Adquisición del candado: cuando un hilo detecta que inUse es false, cambia el valor a true para
* indicar que ha adquirido el acceso exlusico a la sección crítica.
*
* Liberacion del candado: Despues de ejecutar el código de la sección crítica, el hilo liubera el
* candado cambiando inUse a false, permitiendo que otros hilos puedan entrar en la sección crítica.
* */

public class LockVariable {
    // Variable de candado (false: disponible, true: en uso)
    private volatile boolean inUse = false;

    public void seccionCritica() {
        // Busy-wait para adquirir el candado (ineficiente)
        while (inUse) {
            // Espera activa hasta que la sección crítica esté disponible
            System.out.println(Thread.currentThread().getName() + " está esperando.");
        }

        inUse = true;

        try {
            // Simulación de una sección crítica ineficiente
            System.out.println(Thread.currentThread().getName() + " entrando a la sección crítica.");
            // Simulamos una operación ineficiente
            try {
                // Simula una operación costosa
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Rehabilitar interrupciones del hilo
                Thread.currentThread().interrupt();
                System.out.println("Interrupción detectada!");
            }
            System.out.println(Thread.currentThread().getName() + " saliendo de la sección crítica.");
        } finally {
            // Liberar el candado
            inUse = false;
        }
    }
}
