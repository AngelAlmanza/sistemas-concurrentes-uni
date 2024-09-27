import banco.Cliente;

public class Main {
    public static void main(String[] args) {
        Thread cliente1 = new Thread(new Cliente("Angel", 20.0f));
        Thread cliente2 = new Thread(new Cliente("Daniel", 40.0f));

        cliente1.start();
        cliente2.start();
    }
}