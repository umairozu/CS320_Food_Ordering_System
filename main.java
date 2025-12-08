import FOS_DATA.DatabaseConnection;
import FOS_UI.MockUI.*;

import javax.swing.*;
import java.sql.Connection;

public class main {
        public static void main(String[] args) {

           SwingUtilities.invokeLater(()->{            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                System.err.println("Failed to create MainFrame: " + e.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Failed to start application: " + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            });
        }
}
