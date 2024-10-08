package dekker;

public class AlternanciaEstricta implements Runnable {
    private int numero;
    private int other;
    private ExampleType type;
    private float dineroPorRetirar;

    public AlternanciaEstricta(int numero, int other, ExampleType type) {
        this.numero = numero;
        this.other = other;
        this.type = type;
    }

    public AlternanciaEstricta(int numero, int other, ExampleType type, float dineroPorRetirar) {
        this.numero = numero;
        this.other = other;
        this.type = type;
        this.dineroPorRetirar = dineroPorRetirar;
    }

    @Override
    public void run() {
        if (type == ExampleType.BAR) {
            this.barExample();
        } else if (type == ExampleType.BANK) {
            this.bankExample();
        }
    }

    public void barExample () {
        while (true) {
            while (Bath.turno != this.numero) {}

            synchronized (this) {
                try {
                    System.out.println("Es el turno del borracho N°" + this.numero);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("El borracho N°" + this.numero + " salio del baño");
                Bath.turno = other;
            }
        }
    }

    public void bankExample () {
        while (true) {
            while (Bank.turno != this.numero) {}

            synchronized (this) {
                System.out.println("Dinero disponible: $" + Bank.saldo);
                try {
                    if (Bank.saldo >= dineroPorRetirar) {
                        Bank.saldo -= dineroPorRetirar;
                        System.out.println("El client N°" + this.numero + " ha retirado $" + dineroPorRetirar);
                    } else {
                        System.out.println("No ha suficientes fondos");
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Cuenta disponible");
                Bank.turno = other;
            }
        }
    }
}
