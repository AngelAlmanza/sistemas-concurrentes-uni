import dekker.*;

public class Main {
    public static void main(String[] args) {
        // BAR EXAMPLES

        /*
        Thread borracho1 = new Thread(new AlternanciaEstricta(1, 2, ExampleType.BAR));
        Thread borracho2 = new Thread(new AlternanciaEstricta(2, 1, ExampleType.BAR));
         */

        /*
        Thread borracho1 = new Thread(new ProblemaInterbloqueo(0, 1, ExampleType.BAR));
        Thread borracho2 = new Thread(new ProblemaInterbloqueo(1, 0, ExampleType.BAR));
         */

        /*
        Thread borracho1 = new Thread(new ColisionRegionCritica(0, 1, ExampleType.BAR));
        Thread borracho2 = new Thread(new ColisionRegionCritica(1, 0, ExampleType.BAR));
         */

        /*
        Thread borracho1 = new Thread(new PostergacionIndefinida(0, 1, ExampleType.BAR));
        Thread borracho2 = new Thread(new PostergacionIndefinida(1, 0, ExampleType.BAR));

        borracho1.start();
        borracho2.start();
         */

        // BANK EXAMPLES

        /*
        Thread cliente1 = new Thread(new AlternanciaEstricta(1, 2, ExampleType.BANK, 20.0f));
        Thread cliente2 = new Thread(new AlternanciaEstricta(2, 1, ExampleType.BANK, 40.0f));
         */

        /*
        Thread cliente1 = new Thread(new ProblemaInterbloqueo(0, 1, ExampleType.BANK, 20.0f));
        Thread cliente2 = new Thread(new ProblemaInterbloqueo(1, 0, ExampleType.BANK, 40.0f));
         */

        /*
        Thread cliente1 = new Thread(new ColisionRegionCritica(0, 1, ExampleType.BANK, 20.0f));
        Thread cliente2 = new Thread(new ColisionRegionCritica(1, 0, ExampleType.BANK, 40.0f));
         */

        Thread cliente1 = new Thread(new PostergacionIndefinida(0, 1, ExampleType.BANK, 20.0f));
        Thread cliente2 = new Thread(new PostergacionIndefinida(1, 0,ExampleType.BANK, 40.0f));

        cliente1.start();
        cliente2.start();
    }
}