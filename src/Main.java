import investigacionlamport.ThreadProcess;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadProcess(1, 10));
        Thread thread2 = new Thread(new ThreadProcess(2, 5));
        Thread thread3 = new Thread(new ThreadProcess(3, 10));
        Thread thread4 = new Thread(new ThreadProcess(4, 15));
        Thread thread5 = new Thread(new ThreadProcess(5, 5));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}