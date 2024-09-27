package bar;

public class Borracho implements Runnable {
    private String nombre;

    public Borracho(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // Espera ocupada
        while (true) {
            while (Bath.estaOcupado) {
                // El borracho sigue esperando a que el baño se desocupe
            }

            synchronized (Borracho.class) {
                if (!Bath.estaOcupado) {
                    Bath.estaOcupado = true;
                    System.out.println(nombre + " ha entrado al baño");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Bath.estaOcupado = false;
                    System.out.println(nombre + " ha salido del baño");
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
