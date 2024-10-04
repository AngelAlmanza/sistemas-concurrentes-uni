package investigacionlamport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ThreadProcess implements Runnable {
    private int processId;
    private int lamportTimestamp;
    private static List<ThreadProcess> queue = new ArrayList<>();

    public ThreadProcess(int processId, int lamportTimestamp) {
        this.processId = processId;
        this.lamportTimestamp = lamportTimestamp;
    }

    public int getProcessId() {
        return processId;
    }

    public int getLamportTimestamp() {
        return lamportTimestamp;
    }

    // Simula el envio de solicitud para acceder a la seccion critica
    public void requestCriticalSection() {
        queue.add(this);
        System.out.println("Proceso " + processId + " solicito acceso con timestamp " + lamportTimestamp);
    }

    // Verificar si este proceso tiene el turno de acceder a la seccion critica
    public boolean canEnterCriticalSection() {
        // Verificar si el proceso tiene el menor timestamp o en caso de empate el menor ID
        return this.equals(getNextProcess());
    }

    public void enterCriticalSection() {
        System.out.println("Proceso " + processId + " entrando a la seccion critica con timestamp " + lamportTimestamp);
        // Simulacion de trabajo en la seccion critica
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseCriticalSection() {
        queue.remove(this);
        System.out.println("Proceso " + processId + " libero la seccion critica");
    }

    public static ThreadProcess getNextProcess() {
        return queue.stream().min(
                Comparator.comparingInt(ThreadProcess::getLamportTimestamp)
                        .thenComparingInt(ThreadProcess::getProcessId)
                ).orElse(null);
    }

    @Override
    public void run() {
        requestCriticalSection();

        while (!canEnterCriticalSection()) {}

        enterCriticalSection();
        releaseCriticalSection();
    }
}
