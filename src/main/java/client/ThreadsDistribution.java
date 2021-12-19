package client;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadsDistribution {
    private final Integer[][] matrix;
    private final int count;
    private final int serverCount;
    private Integer[] arrResult;
    private final int port = 1099;


    public ThreadsDistribution(Integer[][] matrix, int count, int serverCount)  {
        this.matrix = matrix;
        this.count = count;
        this.serverCount = serverCount;

    }



    public Integer[][] distribution() throws ExecutionException, InterruptedException {


        ExecutorService executor = Executors.newFixedThreadPool(serverCount);
        ArrayList<Future<Integer[]>> list = new ArrayList<>();


        for (int i = 0; i < serverCount; i++) {
            Future<Integer[]> future = executor.submit(new MyThread(matrix, count, serverPartitioning(i), serverPartitioning(i + 1),port,i));
            list.add(future);

        }

        int index = 0;
        arrResult = new Integer[count * count];
        Integer[] localArr;

        for (Future<Integer[]> fut : list) {
            localArr = fut.get();

            for (Integer integer : localArr) {
                arrResult[index] = integer;
                index++;
            }
        }


        return fromArrayToMatrix();


    }

    private int serverPartitioning(int i) {
        int oneCount = (count * count) / serverCount;

        if (i == serverCount) {
            return count * count;
        }

        return oneCount * i;

    }

    private Integer[][] fromArrayToMatrix() {

        Integer[][] newMatrix = new Integer[count + 1][count + 1];
        int index = 0;
        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < count + 1; j++) {
                newMatrix[i][j] = arrResult[index];
                index++;
            }
        }


        return newMatrix;
    }

}
