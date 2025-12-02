package FOS_UI;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardWindow extends JFrame {

    public ManagerDashboardWindow() {
        setTitle("Food Ordering System - Manager Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Manager Dashboard", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
