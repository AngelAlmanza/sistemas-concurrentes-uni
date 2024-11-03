package exclusionmutuaimpl;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private int processId;
    private int timestamp;
    private int okCount;
    private boolean requestingCriticalSection;
    private List<Message> messageQueue;
    private List<Process> processes;

    public Process(int processId) {
        this.processId = processId;
        this.timestamp = 0;
        this.okCount = 0;
        this.requestingCriticalSection = false;
        this.messageQueue = new ArrayList<>();
        this.processes = new ArrayList<>();
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public void requestCriticalSection() {
        timestamp++;
        requestingCriticalSection = true;
        System.out.println("Proceso " + processId + " solicita la sección crítica con timestamp " + timestamp);

        // Enviar solicitud a otros procesos
        for (Process process : processes) {
            if (process.processId != this.processId) {
                process.receiveMessage(new Message(this.processId, timestamp, "REQUEST"));
            }
        }
    }

    public void receiveMessage(Message message) {
        if (message.getType().equals("REQUEST")) {
            if (!requestingCriticalSection || (message.getTimestamp() < timestamp) ||
                    (message.getTimestamp() == timestamp && message.getSenderId() < processId)) {
                // Enviar OK si no se está en sección crítica o si el mensaje tiene prioridad
                sendOk(message.getSenderId());
            } else {
                // Agregar solicitud a la cola si no se puede responder inmediatamente
                messageQueue.add(message);
            }
        } else if (message.getType().equals("OK")) {
            // Contar los OK recibidos
            okCount++;
            if (okCount == processes.size() - 1) {
                enterCriticalSection();
            }
        }
    }

    public void sendOk(int receiverId) {
        for (Process process : processes) {
            if (process.processId == receiverId) {
                System.out.println("Proceso " + processId + " envía OK a " + receiverId);
                process.receiveMessage(new Message(processId, timestamp, "OK"));
            }
        }
    }

    public void enterCriticalSection() {
        System.out.println("Borracho N° " + processId + " entra al baño.");

        // Simulación de la sección crítica
        try {
            Thread.sleep(1000); // Simula el tiempo en la sección crítica
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Borracho N° " + processId + " sale del baño.");
        requestingCriticalSection = false;
        okCount = 0;

        // Enviar OK para las solicitudes en cola
        while (!messageQueue.isEmpty()) {
            Message queuedMessage = messageQueue.remove(0);
            sendOk(queuedMessage.getSenderId());
        }
    }
}
