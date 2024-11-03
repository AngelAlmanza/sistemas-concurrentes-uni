package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Hello stub = (Hello) registry.lookup("Hello");
            System.out.println("Response: " + stub.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
