package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutionException;

public class MatrixWork extends UnicastRemoteObject implements Transformation{
    private Integer[][] Newmatrix;
    private int count;
    private  int size;
    private  int indexStart;
    private  int indexEnd;

    public MatrixWork() throws RemoteException {

    }




    public Integer[] matrixTransformation(Integer[][] matrix, int count, int indexStart, int indexEnd) {
        this.Newmatrix = matrix;
        this.count = count;
        this.size = indexEnd - indexStart;
        this.indexStart = indexStart;
        this.indexEnd = indexEnd;
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

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


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
