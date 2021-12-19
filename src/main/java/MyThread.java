
import java.util.concurrent.Callable;

public class MyThread implements Callable<Integer[]> {

    private final int count;
    private final int start;
    private final int end;
    private final Integer[][] newMatrix1;

    public MyThread(Integer[][] matrix, int count, int start, int end) {
        this.count = count;
        this.start = start;
        this.end = end;
        this.newMatrix1 = new Integer[count + 2][count + 2];
        for (int i = 0; i < count + 2; i++)
            System.arraycopy(matrix[i], 0, this.newMatrix1[i], 0, count + 2);


    }


    @Override
    public Integer[] call() {
        MatrixWork matrixWork = new MatrixWork(newMatrix1, count, start, end);

        return matrixWork.matrixTransformation();


    }
}
