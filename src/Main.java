import bar.Borracho;

public class Main {
    public static void main(String[] args) {
        Thread borracho1 = new Thread(new Borracho("Angel"));
        Thread borracho2 = new Thread(new Borracho("Daniel"));

        borracho1.start();
        borracho2.start();
    }
}