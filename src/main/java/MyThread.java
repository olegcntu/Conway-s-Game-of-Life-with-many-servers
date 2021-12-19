import java.util.Arrays;
import java.util.concurrent.Callable;

public class MyThread implements Callable<Integer[]> {

    private Integer[][] matrix;
    private Integer arrResult[];
    private int count;
    private int start;
    private int end;
    private Integer[][]newMatrix1;

    public MyThread(Integer[][] matrix, int count, int start, int end) {
        this.count = count;
        this.matrix = matrix.clone();
        this.start = start;
        this.end = end;

        this.newMatrix1=new Integer[count+2][count+2];
        for (int i = 0; i < count + 2; i++) {
            for (int j = 0; j < count + 2; j++) {
                this.newMatrix1[i][j] = matrix[i][j];
            }
        }

    }


    @Override
    public Integer[] call() {
        MatrixWork matrixWork = new MatrixWork(newMatrix1, count, start, end);
        return matrixWork.matrixTransformation();


    }
}
