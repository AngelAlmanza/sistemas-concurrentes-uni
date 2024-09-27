package banco;

public class Cliente implements  Runnable {
    private String nombre;
    public float dineroPorRetirar;

    public Cliente(String nombre, float dineroPorRetirar) {
        this.nombre = nombre;
        this.dineroPorRetirar = dineroPorRetirar;
    }

    @Override
    public void run() {
        // Espera ocupada
        while (true) {
            while (!CuentaBancaria.disponible) {
                // La cuenta no esta disponible
            }

            synchronized (Cliente.class) {
                System.out.println("Dinero disponible: " + CuentaBancaria.saldo);
                if (CuentaBancaria.disponible) {
                    CuentaBancaria.disponible = false;

                    try {
                        if (CuentaBancaria.saldo >= dineroPorRetirar) {
                            CuentaBancaria.saldo -= dineroPorRetirar;
                            System.out.println("El cliente " + nombre + " ha retirado dinero (" + dineroPorRetirar + ")");
                        } else {
                            System.out.println("No hay suficientes fondos");
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    CuentaBancaria.disponible = true;
                    System.out.println("Cuenta disponible");
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
