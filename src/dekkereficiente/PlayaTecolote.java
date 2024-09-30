package dekkereficiente;

public class PlayaTecolote {
    private static final int NUM_TURISTAS = 5;
    private static final int TRANSPORTES_POR_TURISTA = 4;

    // Array de flags para saber si el turista quiere usar la lancha
    private volatile boolean[] wantTourist = new boolean[NUM_TURISTAS];

    private volatile int turn = 0; // Inicialmente, el turno es del primer turista (índice 0)

    class Turista extends Thread {
        private String nombre;
        private int id;
        private int transportesRestantes = TRANSPORTES_POR_TURISTA;

        public Turista(String nombre, int id) {
            this.nombre = nombre;
            this.id = id;
        }

        private void usarLancha() throws InterruptedException {
            // Cada turista intenta acceder a la lancha
            wantTourist[id] = true;

            // Esperar hasta que sea el turno de este turista
            while (true) {
                if (turn == id) {
                    // Sección crítica: usar la lancha
                    System.out.println(nombre + " está usando la lancha. Transportes restantes: " + transportesRestantes);

                    // Simular el viaje
                    Thread.sleep(1000); // Simulación del viaje

                    System.out.println(nombre + " ha llegado a su destino.");

                    // Salir de la sección crítica y liberar la lancha
                    transportesRestantes--;
                    wantTourist[id] = false; // Ya no necesita la lancha
                    turn = (turn + 1) % NUM_TURISTAS; // Pasar el turno al siguiente turista
                    break; // Romper el ciclo y salir
                } else {
                    // Si no es su turno, espera a que sea el siguiente
                    Thread.yield(); // Cede el uso de la CPU para permitir que otros turistas se ejecuten
                }
            }
        }

        @Override
        public void run() {
            try {
                while (transportesRestantes > 0) {
                    usarLancha();
                }
                System.out.println(nombre + " ha completado sus transportes y ha terminado el día.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void iniciarViajes() {
        Turista[] turistas = new Turista[NUM_TURISTAS];

        // Crear y empezar los hilos de cada turista
        for (int i = 0; i < NUM_TURISTAS; i++) {
            turistas[i] = new Turista("Turista " + (i + 1), i);
            turistas[i].start();
        }

        // Esperar a que todos los turistas terminen sus transportes
        for (Turista turista : turistas) {
            try {
                turista.join();  // Esperar a que cada hilo de turista termine
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Todos los turistas han completado sus transportes.");
    }

    public static void main(String[] args) {
        new PlayaTecolote().iniciarViajes();
    }
}
