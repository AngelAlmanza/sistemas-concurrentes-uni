package dekker;

import java.util.Random;

public class PostergacionIndefinida implements  Runnable {
    public static Random rand = new Random();
    private int numero;
    private int other;
    private ExampleType type;
    private float dineroPorRetirar;

    public PostergacionIndefinida(int numero, int other, ExampleType type) {
        this.numero = numero;
        this.other = other;
        this.type = type;
    }

    public PostergacionIndefinida(int numero, int other, ExampleType type, float dineroPorRetirar) {
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

    private void barExample() {
        while (true) {
            Pizarra.flags[numero] = true;

            while(Pizarra.flags[other]) {
                Pizarra.flags[numero] = false;
                try {
                    int tiempo = getRandomNumber();
                    //System.out.println("N°" + this.numero + " se espera: " + tiempo);
                    Thread.sleep(tiempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Pizarra.flags[numero] = true;
            }

            synchronized (this) {
                try {
                    System.out.println("Es el turno del borracho N°" + this.numero);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("El borracho N°" + this.numero + " salio del baño");
            Pizarra.flags[numero] = false;
        }
    }

    private void bankExample() {
        while(true) {
            Pizarra.flags[numero] = true;
            while(Pizarra.flags[other]) {
                Pizarra.flags[numero] = false;
                try {
                    int tiempo = getRandomNumber();
                    Thread.sleep(tiempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Pizarra.flags[numero] = true;
            }

            synchronized (this) {
                try {
                    System.out.println("Dinero disponible $" + Bank.saldo);
                    if (Bank.saldo >= dineroPorRetirar) {
                        Bank.saldo -= dineroPorRetirar;
                        System.out.println("El cliente N°" + this.numero + " ha retirado $" + dineroPorRetirar);
                    } else {
                        System.out.println("No hay suficientes fondos");
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Cuenta disponible");
            Pizarra.flags[numero] = false;
        }
    }

    private int getRandomNumber() {
        return rand.nextInt(5);
    }
}
