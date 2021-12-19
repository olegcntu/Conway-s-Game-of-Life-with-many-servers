import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadsDistribution {
    private Integer[][] matrix;
    private Integer[][] newMatrix;
    private final int count;
    private int serverCount;
    private Integer[] arrResult;


    public ThreadsDistribution(Integer[][] matrix, int count, int serverCount) {
        this.matrix = matrix;
        this.count = count;
        this.serverCount = serverCount;

    }

    public Integer[][] distribution() throws ExecutionException, InterruptedException {


        ExecutorService executor = Executors.newFixedThreadPool(serverCount);
        ArrayList<Future<Integer[]>> list = new ArrayList<>();


        for (int i = 0; i < serverCount; i++) {
            Future<Integer[]> future = executor.submit(new MyThread(matrix, count, serverPartitioning(i), serverPartitioning(i+1 )));
            //System.out.println(serverPartitioning(i)+" "+serverPartitioning(i+1));

            list.add(future);

        }

        int a = 0;
        arrResult = new Integer[count * count];
        Integer[] localArr;

        for (Future<Integer[]> fut : list) {
            localArr = fut.get();

            for (Integer integer : localArr) {
                arrResult[a] = integer;
                a++;
            }

//            for(int o=0;o<100;o++) {
//                if(o%10==0){System.out.println(); }
//                System.out.print(arrResult[o]+" ");
//            }

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

        newMatrix = new Integer[count + 1][count + 1];
        int a = 0;
        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < count + 1; j++) {
                newMatrix[i][j] = arrResult[a];
                a++;
            }
        }


        return newMatrix;
    }

}
