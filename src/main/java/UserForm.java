import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class UserForm extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scroll;
    private JButton button2;
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

                Component[] components = sc.getComponents();
                matrix = new int[count][count];
                int a = 0;
                for (int i = 0; i < count; i++) {
                    for (int j = 0; j < count; j++)
                        if (components[a++].getBackground().equals(Color.BLACK)) {
                            matrix[i][j] = 1;
                        } else matrix[i][j] = 0;
                }

                MatrixWork matrixWork=new MatrixWork(matrix,count);

            }


        });
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
                    Border border ;
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
                    else {
                        setBackground(Color.BLACK);
                    }
                }

            });
        }
    }
}
