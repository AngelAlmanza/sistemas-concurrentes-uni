package threads;

import java.util.concurrent.ThreadLocalRandom;

public class AngelThread extends Thread {

    @Override
    public void run() {
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(2000, 5000 + 1);
            System.out.println("I'm AngelThread");
            Thread.sleep(randomNum);
            System.out.println("Thread Class");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
