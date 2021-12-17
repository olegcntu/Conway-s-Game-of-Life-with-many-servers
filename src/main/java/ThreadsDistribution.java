import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadsDistribution {
    private Integer[][] matrix;
    private Integer[][] newMatrix;
    private final int count;
    private int serverCount;


    public ThreadsDistribution(Integer[][] matrix, int count, int serverCount) {
        this.matrix = matrix;
        this.count = count;
        this.serverCount = serverCount;
    }

    public Integer[][] distribution() {

        ExecutorService executor = Executors.newFixedThreadPool(serverCount);
        List<Future<Integer[][]>> list = new ArrayList<Future<Integer[][]>>();

        for (int i = 0; i < serverCount; i++) {
            Future<Integer[][]> future = executor.submit(new MyThread(matrix,count,0,50));
            list.add(future);
        }

        return newMatrix;


    }

    private int serverPartitioning() {

        return 1;
    }
}
