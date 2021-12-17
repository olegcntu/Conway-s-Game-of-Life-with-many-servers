public class MatrixWork {
    private Integer[][] matrix;
    private final int count;
    private final int startI;
    private final int startJ;
    private final int endI;
    private final int endJ;

    public MatrixWork(Integer[][] matrix, int count, int indexStart, int indexEnd) {
        this.matrix = matrix;
        this.count = count;
        this.startI = indexStart / count + 1;
        this.startJ = indexStart % count+1;
        this.endI = indexEnd / count;
        this.endJ = (indexEnd - 1) % count + 1;
        System.out.println("start: " + startI + " " + startJ);
        System.out.println("end: " + endI + " " + endJ);
    }

    public Integer[][] matrixTransformation() {
        int i = startI;
        int j = startJ;
        int localCount = count;

        for (; i < endI + 1; i++) {
            for (; j < localCount + 1; j++) {
                if (matrix[i][j] == 0) {
                    matrix = newCell(i, j);
                }
                if (matrix[i][j] == 1) {
                    matrix = dyingCellOrLive(i, j);
                }

            }
            j = 1;
            if (i == endI) {
                localCount = endJ;
            }

        }
        matrixToStandard();
        return matrix;
    }

    private void matrixToStandard() {
        int i = startI;
        int j = startJ;
        int localCount = count;

        for (; i < endI + 1; i++) {
            for (; j < localCount + 1; j++) {

                if (matrix[i][j] == 2) {
                    matrix[i][j]=1;
                }
                if (matrix[i][j] == 3){
                    matrix[i][j]=0;
                }
            }
            j = 1;
            if (i == endI) {
                localCount = endJ;
            }
        }
    }


    private Integer[][] newCell(int i, int j) {

        int cellCont = sumCells(i, j);
        if (cellCont == 3) {
            matrix[i][j] = 2;
        }

        return matrix;
    }


    private Integer[][] dyingCellOrLive(int i, int j) {

        int cellCont = sumCells(i, j);
        if (cellCont == 3 || cellCont == 2) {
            matrix[i][j] = 1;
        } else matrix[i][j] = 3;

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
        if (matrix[i + 1][j] == 1 || matrix[i + 1][j] == 3) {
            sum++;
        }
        if (matrix[i + 1][j + 1] == 1 || matrix[i + 1][j + 1] == 3) {
            sum++;
        }
        return sum;
    }

}
