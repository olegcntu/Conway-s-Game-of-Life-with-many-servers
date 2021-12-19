public class MatrixWork {
    //private final  Integer[][] matrix;
    private Integer[][] Newmatrix;
    private final int count;
    private final int startI;
    private final int startJ;
    private final int endI;
    private final int endJ;
    private int size;

    private int indexStart;
    private int indexEnd;

    public MatrixWork(Integer[][] matrix, int count, int indexStart, int indexEnd) {
        this.Newmatrix = matrix;
        this.count = count;
        this.startI = indexStart / count + 1;
        this.startJ = indexStart % count + 1;
        this.endI = indexEnd / count;
        this.endJ = (indexEnd - 1) % count + 1;
        this.size = indexEnd - indexStart;
        this.indexStart = indexStart;
        this.indexEnd = indexEnd;
    }

    public Integer[] matrixTransformation() {

        int i = startI;
        int j = startJ;
        int localCount = count;

        for (; i < endI + 1; i++) {
            for (; j < localCount + 1; j++) {
                if (Newmatrix[i][j] == 0) {
                    Newmatrix = newCell(i, j);
                }
                if (Newmatrix[i][j] == 1) {
                    Newmatrix = dyingCellOrLive(i, j);
                }

            }
            j = 1;
            if (i == endI) {
                localCount = endJ;
            }

        }
        return matrixToStandard();
    }

    private Integer[] matrixToStandard() {
        int a = 0;
        int f = 0;

        Integer arrOfMatrix[] = new Integer[size];
        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < count + 1; j++) {

                if (a >= indexStart && a < indexEnd) {
                    if (Newmatrix[i][j] == 2) {
                        Newmatrix[i][j] = 1;
                    }
                    if (Newmatrix[i][j] == 3) {
                        Newmatrix[i][j] = 0;
                    }
                    arrOfMatrix[f] = Newmatrix[i][j];
                    f++;
                }
                a++;
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
