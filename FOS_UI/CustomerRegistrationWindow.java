package FOS_UI;

import FOS_CORE.IAccountService;
import FOS_CORE.Customer;
import FOS_CORE.Address;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
// Done by Hassan Askari
public class CustomerRegistrationWindow extends JFrame {

    private final JTextField emailField = new JTextField(20);
    private final JTextField phoneField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JTextField addressField = new JTextField(30);
    private final JButton registerBtn = new JButton("Register");

    public CustomerRegistrationWindow() {
        super("Register - FOS");
        initUI();
    }

    private void initUI() {
        setSize(480,260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        var c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; panel.add(new JLabel("Email:"), c);
        c.gridx = 1; panel.add(emailField, c);

        c.gridx = 0; c.gridy = 1; panel.add(new JLabel("Phone:"), c);
        c.gridx = 1; panel.add(phoneField, c);

        c.gridx = 0; c.gridy = 2; panel.add(new JLabel("Password:"), c);
        c.gridx = 1; panel.add(passwordField, c);

        c.gridx = 0; c.gridy = 3; panel.add(new JLabel("Address (optional):"), c);
        c.gridx = 1; panel.add(addressField, c);

        c.gridx = 1; c.gridy = 4; panel.add(registerBtn, c);

        add(panel);

        registerBtn.addActionListener(this::onRegister);
    }

    private void onRegister(ActionEvent e) {
        String email = emailField.getText();
        String phone = phoneField.getText();
        String pw = new String(passwordField.getPassword());
        String addressLine = addressField.getText();

        if (!InputValidator.isEmailValid(email)) {
            DialogUtils.showError(this, "Validation", "Enter a valid email.");
            return;
        }
        if (!InputValidator.isPhoneValid(phone)) {
            DialogUtils.showError(this, "Validation", "Enter a valid phone number.");
            return;
        }
        if (!InputValidator.isNonEmpty(pw)) {
            DialogUtils.showError(this, "Validation", "Enter a password.");
            return;
        }

        try {
            IAccountService accountService = ServiceContext.getAccountService();
            Customer created = accountService.createCustomerAccount(email, phone, pw);
            if (created == null) {
                DialogUtils.showError(this, "Registration", "Registration failed (possibly duplicate).");
                return;
            }

            if (InputValidator.isNonEmpty(addressLine)) {
                Address addr = new Address(); // using FOS_CORE.Address
                addr.setAddressLine(addressLine);
                accountService.addAddress(created, addr);
            }

            DialogUtils.showInfo(this, "Success", "Account created successfully. You can login now.");
            this.dispose();
        } catch (Exception ex) {
            DialogUtils.showError(this, "Error", "An error occurred while registering.");
            ex.printStackTrace();
        }
    }
}

