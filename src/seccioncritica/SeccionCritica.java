package seccioncritica;

public class SeccionCritica {
    private int recursoCompartido = 0;

    public void constructor () {}

    public synchronized void incrementar () {
        recursoCompartido++;
        System.out.println(Thread.currentThread().getName() + " incrementó el valor a: " + recursoCompartido);
    }
}
