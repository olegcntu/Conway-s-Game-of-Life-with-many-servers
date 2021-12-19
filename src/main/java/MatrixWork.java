public class MatrixWork {
    private Integer[][] Newmatrix;
    private final int count;
    private final int size;
    private final int indexStart;
    private final int indexEnd;

    public MatrixWork(Integer[][] matrix, int count, int indexStart, int indexEnd) {
        this.Newmatrix = matrix;
        this.count = count;
        this.size = indexEnd - indexStart;
        this.indexStart = indexStart;
        this.indexEnd = indexEnd;
    }

    public Integer[] matrixTransformation() {
        int indexDiapason = 0;

        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < count + 1; j++) {

                if (indexDiapason >= indexStart && indexDiapason < indexEnd) {
                    if (Newmatrix[i][j] == 0) {
                        Newmatrix = newCell(i, j);
                    }
                    if (Newmatrix[i][j] == 1) {
                        Newmatrix = dyingCellOrLive(i, j);
                    }
                }
                indexDiapason++;
            }
        }
        return matrixToStandard();
    }


    private Integer[] matrixToStandard() {
        int indexDiapason = 0;
        int arrCount = 0;
        Integer[] arrOfMatrix = new Integer[size];
        for (int i = 1; i < count + 1; i++) {


            for (int j = 1; j < count + 1; j++) {

                if (indexDiapason >= indexStart && indexDiapason < indexEnd) {
                    if (Newmatrix[i][j] == 2) {
                        Newmatrix[i][j] = 1;
                    }
                    if (Newmatrix[i][j] == 3) {
                        Newmatrix[i][j] = 0;
                    }
                    arrOfMatrix[arrCount] = Newmatrix[i][j];
                    arrCount++;
                }
                indexDiapason++;
            }
        }
        return arrOfMatrix;
    }


    private Integer[][] newCell(int i, int j) {

        int cellCont = sumCells(i, j);
        if (cellCont == 3) {
            Newmatrix[i][j] = 2;
        }

        return Newmatrix;
    }


    private Integer[][] dyingCellOrLive(int i, int j) {

        int cellCont = sumCells(i, j);
        if (cellCont == 3 || cellCont == 2) {
            Newmatrix[i][j] = 1;
        } else Newmatrix[i][j] = 3;

        return Newmatrix;
    }

    private int sumCells(int i, int j) {
        int sum = 0;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Newmatrix[i - 1][j - 1] == 1 || Newmatrix[i - 1][j - 1] == 3) {
            sum++;
        }
        if (Newmatrix[i - 1][j] == 1 || Newmatrix[i - 1][j] == 3) {
            sum++;
        }

        if (Newmatrix[i - 1][j + 1] == 1 || Newmatrix[i - 1][j + 1] == 3) {
            sum++;
        }
        if (Newmatrix[i][j - 1] == 1 || Newmatrix[i][j - 1] == 3) {
            sum++;
        }
        if (Newmatrix[i][j + 1] == 1 || Newmatrix[i][j + 1] == 3) {
            sum++;
        }
        if (Newmatrix[i + 1][j - 1] == 1 || Newmatrix[i + 1][j - 1] == 3) {
            sum++;
        }
        if (Newmatrix[i + 1][j] == 1 || Newmatrix[i + 1][j] == 3) {
            sum++;
        }
        if (Newmatrix[i + 1][j + 1] == 1 || Newmatrix[i + 1][j + 1] == 3) {
            sum++;
        }
        return sum;
    }

}
