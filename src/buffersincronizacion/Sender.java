package buffersincronizacion;

public class Sender extends Thread {
    private final MessageBuffer buffer;

    public Sender(MessageBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                String message = "Mensaje " + i;
                buffer.send(message);  // Envía mensajes al buffer
                Thread.sleep(500);  // Simula algún retardo en el envío
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}