package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public interface    Transformation extends Remote  {
    public Integer[] matrixTransformation(Integer[][] matrix, int count, int indexStart, int indexEnd)throws RemoteException;

}
