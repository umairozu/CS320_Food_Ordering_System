package FOS_UI.MockUI;

import FOS_CORE.*;
import javax.swing.*;
import java.awt.*;

public class AccountDetailsWindow extends JPanel {
    private MainFrame mainFrame;
    private JLabel emailLabel;
    private JLabel phoneLabel;

    public AccountDetailsWindow(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Restaurants");
        backButton.addActionListener(e -> mainFrame.showRestaurants());
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel titleLabel = new JLabel("Account Information");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailLabel = new JLabel("N/A");
        emailLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        centerPanel.add(emailLabel, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneLabel = new JLabel("N/A");
        phoneLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        centerPanel.add(phoneLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void refresh() {
        Customer customer = mainFrame.getCurrentCustomer();
        if (customer != null) {
            emailLabel.setText(customer.getEmail() != null ? customer.getEmail() : "N/A");
            String phone = "N/A";
            if (customer.getPhoneNumbers() != null && !customer.getPhoneNumbers().isEmpty()) {
                phone = customer.getPhoneNumbers().get(0);
            }
            phoneLabel.setText(phone);
        } else {
            emailLabel.setText("N/A");
            phoneLabel.setText("N/A");
        }
    }
}
