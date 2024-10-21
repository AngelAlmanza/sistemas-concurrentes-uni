package buffersincronizacion;

public class Receiver extends Thread {
    private final MessageBuffer buffer;

    public Receiver(MessageBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                String message = buffer.receive();  // Recibe mensajes del buffer
                Thread.sleep(1000);  // Simula algún procesamiento
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}