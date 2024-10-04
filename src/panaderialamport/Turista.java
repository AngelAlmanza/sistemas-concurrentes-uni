package panaderialamport;

public class Turista extends Thread {
    private final int id;
    private final int maxTransportes = 4;
    private int transportesRestantes;
    private final LamportPanaderia panaderia;
    private static final Object lock = new Object();

    public Turista(int id, LamportPanaderia panaderia) {
        this.id = id;
        this.panaderia = panaderia;
        this.transportesRestantes = maxTransportes;
    }

    @Override
    public void run() {
        while (transportesRestantes > 0) {
            panaderia.tomarTicket(id);

            // Sección crítica: el turista sube a la lancha
            synchronized (lock) {
                System.out.println("Turista " + id + " sube a la lancha. Transportes restantes: " + transportesRestantes);
                transportesRestantes--;
                // Simular tiempo de transporte
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Turista " + id + " baja de la lancha.");
            }

            panaderia.dejarTicket(id);

            // Simular estancia en la isla/playa
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // El turista ha terminado el día
        synchronized (lock) {
            System.out.println("Turista " + id + " ha terminado su paseo.");
        }
    }
}
