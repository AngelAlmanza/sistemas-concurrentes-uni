import threads.AngelThread;
import threads.DanielThread;

public class Main {
    public static void main(String[] args) {
        AngelThread angelThread = new AngelThread();
        angelThread.start();

        DanielThread danielThread = new DanielThread();
        Thread danielThread2 = new Thread(danielThread);
        danielThread2.start();
    }
}