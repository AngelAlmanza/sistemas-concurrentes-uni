import implementaciones.DisableInterrupts;
import implementaciones.LockVariable;
import implementaciones.StrictAlternance;

public class Main {
    public static void main(String[] args) {
        // DisableInterrupts
        DisableInterrupts disableInterrupts = new DisableInterrupts();

        // Crear varios hilos que intentan entrar a la sección crítica
        Thread t1 = new Thread(disableInterrupts::seccionCritica);
        Thread t2 = new Thread(disableInterrupts::seccionCritica);

        t1.start();
        t2.start();

        // Variables candado
        LockVariable lockVariable = new LockVariable();

        // Crear varios hilos que intentan entrar a la sección crítica
        Thread t3 = new Thread(lockVariable::seccionCritica);
        Thread t4 = new Thread(lockVariable::seccionCritica);

        t3.start();
        t4.start();

        // Alternancia estricta
        StrictAlternance strictAlternance = new StrictAlternance();

        // Crear 2 hilos que intentan entrar a la sección crítica
        Thread t5 = new Thread(() -> {
            while (true) {
                strictAlternance.seccionCriticaHilo1();
            }
        }, "Hilo 1");

        Thread t6 = new Thread(() -> {
            while (true) {
                strictAlternance.seccionCriticaHilo2();
            }
        }, "Hilo 2");

        t5.start();
        t6.start();
    }
}