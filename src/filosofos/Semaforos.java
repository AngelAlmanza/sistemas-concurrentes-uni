package filosofos;

import java.util.concurrent.Semaphore;

public class Semaforos {
    private static final int NUM_FILOSOFOS = 5;
    private static final Semaphore[] tenedores = new Semaphore[NUM_FILOSOFOS];

    static {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new Semaphore(1);
        }
    }

    public static class Filosofos extends Thread {
        private final int id;

        public Filosofos(int id) {
            this.id = id;
        }

        private void pensar() throws InterruptedException {
            System.out.println("Filósofo " + id + " está pensando");
            Thread.sleep((long) (Math.random() * 1000));
        }

        private void comer() throws InterruptedException {
            System.out.println("Filósofo " + id + " está intentando comer");
            tenedores[id].acquire();
            tenedores[(id + 1) % NUM_FILOSOFOS].acquire();
            System.out.println("Filósofo " + id + " está comiendo");
            Thread.sleep((long) (Math.random() * 1000));
            tenedores[id].release();
            tenedores[(id + 1) % NUM_FILOSOFOS].release();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    pensar();
                    comer();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            new Filosofos(i).start();
        }
    }
}
