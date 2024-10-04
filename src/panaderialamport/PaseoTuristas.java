package panaderialamport;

public class PaseoTuristas {
    public static void main(String[] args) {
        int numTuristas = 5;
        LamportPanaderia panaderia = new LamportPanaderia();

        Turista[] turistas = new Turista[numTuristas];
        for (int i = 0; i < numTuristas; i++) {
            turistas[i] = new Turista(i, panaderia);
            turistas[i].start();
        }

        for (Turista turista : turistas) {
            try {
                turista.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
