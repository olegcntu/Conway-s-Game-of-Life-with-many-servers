public class MatrixWork {
    private int matrix[][];
    private int count;

    public MatrixWork(int matrix[][], int count) {
        this.matrix = matrix;
        this.count = count;
    }

    public int[][] matrixTransformation() {

        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < count + 1; j++) {
                if (matrix[i][j] == 0) {
                    matrix = newCell(i, j);
                }
                if (matrix[i][j] == 1) {
                    matrix = dyingCellOrLive(i, j);
                }

            }

        }

        return matrix;
    }

    private int[][] newCell(int i, int j) {

        int cellCont = sumCells(i, j);
        if (cellCont == 3) {
            matrix[i][j] = 2;
        }

        return matrix;
    }


    private int[][] dyingCellOrLive(int i, int j) {

        int cellCont = sumCells(i, j);
        if (cellCont == 3 || cellCont == 2) {
            matrix[i][j] = 1;
        }
        else matrix[i][j] = 3;

        return matrix;
    }

    private int sumCells(int i, int j) {
        int sum = 0;
        if (matrix[i - 1][j - 1] == 1 || matrix[i - 1][j - 1] == 3) {
            sum++;
        }
        if (matrix[i - 1][j] == 1 || matrix[i - 1][j] == 3) {
            sum++;
        }

        if (matrix[i - 1][j + 1] == 1 || matrix[i - 1][j + 1] == 3) {
            sum++;
        }
        if (matrix[i][j - 1] == 1 || matrix[i][j - 1] == 3) {
            sum++;
        }
        if (matrix[i][j + 1] == 1 || matrix[i][j + 1] == 3) {
            sum++;
        }
        if (matrix[i + 1][j - 1] == 1 || matrix[i + 1][j - 1] == 3) {
            sum++;
        }
        if (matrix[i + 1][j] == 1 || matrix[i + 1][j]  == 3) {
            sum++;
        }
        if (matrix[i + 1][j + 1] == 1 || matrix[i + 1][j + 1]  == 3) {
            sum++;
        }
        return sum;
    }

}
