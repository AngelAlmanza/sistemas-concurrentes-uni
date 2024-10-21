import buffersincronizacion.*;

public class Main {
    public static void main(String[] args) {

        MessageBuffer buffer = new MessageBuffer(3);

        Sender sender = new Sender(buffer);
        Receiver receiver = new Receiver(buffer);

        sender.start();
        receiver.start();
    }
}