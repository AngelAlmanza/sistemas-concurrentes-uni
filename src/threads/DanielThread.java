package threads;

import java.util.concurrent.ThreadLocalRandom;

public class DanielThread implements  Runnable {
    @Override
    public void run() {
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(2000, 5000 + 1);
            System.out.println("I'm Daniel Thread");
            Thread.sleep(randomNum);
            System.out.println("Runnable Interface");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
