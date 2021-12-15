public class MatrixWork {
   private int matrix[][];
   private int count;

    public MatrixWork(int matrix[][],int count) {
        this.matrix=matrix;
        this.count=count;

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
