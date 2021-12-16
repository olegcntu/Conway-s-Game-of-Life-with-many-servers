import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class UserForm extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scroll;
    private JButton button2;
    private JButton runButton;
    private final JPanel sc;
    private final int count = 10;
    private int[][] matrix;

    public static void main(String[] args) {
        JFrame frame = new UserForm("RMI calculator");
        frame.setVisible(true);
    }

    public UserForm(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.setSize(600, 400);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        sc = new TestPane(count);
        scroll.setViewportView(sc);


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fromTableToMatrix();

            }

        });


        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    fromTableToMatrix();

            }
        });
    }

    private void fromTableToMatrix() {
        Component[] components = sc.getComponents();
        matrix = new int[count + 2][count + 2];
        int countOfComponent = 0;
        for (int i = 0; i < count + 2; i++) {
            for (int j = 0; j < count + 2; j++) {

                if (i == 0 || j == 0 || i == count + 1 || j == count + 1) {
                    matrix[i][j] = 0;
                    continue;
                }
                if (components[countOfComponent++].getBackground().equals(Color.BLACK)) {
                    matrix[i][j] = 1;
                } else matrix[i][j] = 0;
            }
        }
        MatrixWork matrixWork = new MatrixWork(matrix, count);
        matrix = matrixWork.matrixTransformation();
        fromMatrixToTable(components);
    }


    private void fromMatrixToTable(Component[] components) {

        int index = 0;
        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < count + 1; j++) {

                if (matrix[i][j] == 3 || matrix[i][j] == 0) {
                    components[index].setBackground(Color.WHITE);
                } else {
                    components[index].setBackground(Color.BLACK);
                }
                sc.setComponentZOrder(components[index], 99);

                index++;
            }
        }


    }

    public static class TestPane extends JPanel {


        public TestPane(int count) {
            GridBagLayout gl = new GridBagLayout();
            setLayout(gl);

            GridBagConstraints gbc = new GridBagConstraints();

            for (int row = 0; row < count; row++) {
                for (int col = 0; col < count; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;
                    CellPane cellPane = new CellPane();
                    Border border;
                    border = new MatteBorder(1, 1, 1, 1, Color.GRAY);

                    cellPane.setBorder(border);
                    add(cellPane, gbc);
                }
            }


        }
    }

    public static class CellPane extends JPanel {


        public CellPane() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (getBackground() == Color.BLACK) setBackground(Color.WHITE);
                    else setBackground(Color.BLACK);
                }

            });
        }
    }
}
