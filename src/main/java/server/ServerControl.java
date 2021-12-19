package server;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ServerControl extends JFrame {
    private JPanel mainPanel1;
    private JButton checkButton;
    private JTextArea textArea1;

    public ServerControl(String title) {
        super(title);
        this.setContentPane(mainPanel1);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countString = textArea1.getText();
                int count = Integer.parseInt(countString);

                try {
                    generate(count);
                } catch (Exception ex) {
                    textArea1.setText(ex.toString());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new ServerControl("Server control");
        frame.setVisible(true);
    }

    private void generate(int count) throws MalformedURLException, RemoteException {
        int port = 1099;
        for (int i = 0; i < count; i++) {
            String serverName = "rmi://localhost:" + Integer.toString(port) + "/server";
            Server s = new Server();
            s.startServer(serverName, port);
            port++;
        }
    }

}
