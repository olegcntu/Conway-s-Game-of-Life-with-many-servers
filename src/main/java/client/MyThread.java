package client;

import server.MatrixWork;
import server.Transformation;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;

public class MyThread implements Callable<Integer[]>  {

    private final int count;
    private final int start;
    private final int end;
    private final Integer[][] newMatrix1;
    private  final int port;
    private  final int portCount;

    public MyThread(Integer[][] matrix, int count, int start, int end, int port, int portCount) {
        this.portCount=portCount;
        this.port=port;
        this.count = count;
        this.start = start;
        this.end = end;
        this.newMatrix1 = new Integer[count + 2][count + 2];
        for (int i = 0; i < count + 2; i++)
            System.arraycopy(matrix[i], 0, this.newMatrix1[i], 0, count + 2);


    }


    @Override
    public Integer[] call() throws NamingException, RemoteException {


        String serverName = "rmi://localhost:" + Integer.toString(port + portCount) + "/server";
        Context context = new InitialContext();
        Transformation transformation = (Transformation)context.lookup(serverName);

        return transformation.matrixTransformation(newMatrix1, count, start, end);


    }
}
