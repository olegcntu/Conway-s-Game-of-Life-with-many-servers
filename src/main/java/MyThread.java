import java.util.concurrent.Callable;

public class MyThread implements Callable<Integer[][]> {

    private Integer[][] matrix;
    private Integer[][] newMatrix;
    private int count;
    private int start;
    private int end;

    public MyThread(Integer[][] matrix, int count, int start, int end) {
        this.count = count;
        this.matrix = matrix;
        this.start = start;
        this.end = end;
    }


    @Override
    public Integer[][] call() throws Exception {
        MatrixWork matrixWork = new MatrixWork(matrix, count, start, end);
        newMatrix = matrixWork.matrixTransformation();
        return newMatrix;
    }
}
