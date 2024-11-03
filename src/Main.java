import exclusionmutuaimpl.Process;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Crear procesos (borrachos)
        Process p1 = new Process(1, 20.0f);
        Process p2 = new Process(2, 60.0f);
        Process p3 = new Process(3, 70.0f);

        // Añadir todos los procesos a cada proceso
        p1.setProcesses(Arrays.asList(p1, p2, p3));
        p2.setProcesses(Arrays.asList(p1, p2, p3));
        p3.setProcesses(Arrays.asList(p1, p2, p3));

        // Simulacion de solicitudes de acceso a la seccion critica
        new Thread(p1::requestCriticalSection).start();
        new Thread(p2::requestCriticalSection).start();
        new Thread(p3::requestCriticalSection).start();
    }
}