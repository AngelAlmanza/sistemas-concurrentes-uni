package rmi;

import java.rmi.RemoteException;

public class HelloImpl implements Hello{
    public HelloImpl() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        return "Hello World";
    }
}
