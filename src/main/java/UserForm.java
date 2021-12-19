import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class UserForm extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scroll;
    private JButton button2;
    private JButton runButton;
    private JTextArea textArea1;
    private JButton randomButton;
    private JTextArea textArea2;
    private final JPanel sc;
    private final int count = 10;
    private Integer[][] matrix;
    private long timeStart;


    public static void main(String[] args) {
        JFrame frame = new UserForm("RMI calculator");
        frame.setVisible(true);
    }

    public UserForm(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.setSize(600, 400);
        // this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        sc = new TestPane(count);
        scroll.setViewportView(sc);


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeStart= System.currentTimeMillis();
                try {
                    fromTableToMatrix();
                } catch (ExecutionException | InterruptedException ex) {
                    ex.printStackTrace();
                }

            }

        });


        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                Component[] components = sc.getComponents();
                for (int i = 1; i < count + 1; i++) {
                    for (int j = 1; j < count + 1; j++) {
                        components[index].setBackground(Color.WHITE);
                        sc.setComponentZOrder(components[index], index);

                        index++;
                    }
                }

            }
        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                Component[] components = sc.getComponents();
                for (int i = 1; i < count + 1; i++) {
                    for (int j = 1; j < count + 1; j++) {
                        Random random = new Random();
                        if (random.nextBoolean()) {
                            components[index].setBackground(Color.WHITE);
                        } else {
                            components[index].setBackground(Color.BLACK);
                        }

                        sc.setComponentZOrder(components[index], index);
                        index++;
                    }
                }
            }
        });
    }

    private void fromTableToMatrix() throws ExecutionException, InterruptedException {
        Component[] components = sc.getComponents();
        matrix = new Integer[count + 2][count + 2];
        int countOfComponent = 0;
        for (int i = 0; i < count + 2; i++) {
            for (int j = 0; j < count + 2; j++) {

                if (i == 0 || j == 0 || i == count + 1 || j == count + 1) {
                    matrix[i][j] = 0;
                } else {
                    if (components[countOfComponent++].getBackground().equals(Color.BLACK)) {
                        matrix[i][j] = 1;
                    } else matrix[i][j] = 0;
                }
            }
        }


        ThreadsDistribution threadsDistribution = new ThreadsDistribution(matrix, count, serverCount());
        matrix = threadsDistribution.distribution();
        fromMatrixToTable(components);
    }


    private int serverCount() {
        int SCount = 1;
        try {
            SCount = Integer.parseInt(textArea1.getText());
        } catch (Exception ignored) {
            textArea1.setText("ERROR");
        }
        return SCount;

    }

    private void fromMatrixToTable(Component[] components) {

        int index = 0;
        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < count + 1; j++) {
                if (matrix[i][j] == 0) {
                    components[index].setBackground(Color.WHITE);
                } else {
                    components[index].setBackground(Color.BLACK);
                }
                sc.setComponentZOrder(components[index], index);

                index++;
            }
        }

        textArea2.setText(String.valueOf(System.currentTimeMillis()-timeStart)+" millis");



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
