package FOS_UI.MockUI.ManagerPanels;

import javax.swing.*;
import java.awt.*;

public class EditDiscountPanel extends JPanel
{

    private JComboBox<String> itemDropdown;
    private JTextField percentageField;
    private JTextField descriptionField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton applyButton;

    public EditDiscountPanel() {
        setLayout(new BorderLayout());
        add(buildFormPanel(), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel buildFormPanel()
    {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        itemDropdown = new JComboBox<>();
        percentageField = new JTextField();
        descriptionField = new JTextField();
        startDateField = new JTextField("YYYY-MM-DD");
        endDateField = new JTextField("YYYY-MM-DD");

        panel.add(new JLabel("Menu Item:"));
        panel.add(itemDropdown);

        panel.add(new JLabel("Discount %:"));
        panel.add(percentageField);

        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        panel.add(new JLabel("Start Date:"));
        panel.add(startDateField);

        panel.add(new JLabel("End Date:"));
        panel.add(endDateField);

        return panel;
    }

    private JPanel buildButtonPanel()
    {
        JPanel panel = new JPanel();
        applyButton = new JButton("Apply Discount");
        panel.add(applyButton);
        return panel;
    }


    public JButton getApplyButton() { return applyButton; }

    public String getSelectedItem()
    {
        return (String) itemDropdown.getSelectedItem();
    }

    public String getPercentage() { return percentageField.getText(); }

    public String getDescription() { return descriptionField.getText(); }

    public String getStartDate() { return startDateField.getText(); }

    public String getEndDate() { return endDateField.getText(); }

    public void setMenuItems(java.util.List<String> items) {
        itemDropdown.removeAllItems();
        for (String item : items)
            itemDropdown.addItem(item);
    }
}
