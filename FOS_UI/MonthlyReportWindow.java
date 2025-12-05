package FOS_UI;

// Being done by Hassan Askari

import FOS_CORE.IManagerService;
import FOS_CORE.Manager;
import FOS_CORE.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;

public class MonthlyReportWindow extends JFrame {

    private JComboBox<String> monthDropdown;
    private JComboBox<String> yearDropdown;
    private JButton generateBtn;
    private JTextArea reportArea;

    public MonthlyReportWindow()
    {

        setTitle("Food Ordering System – Monthly Report");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents()
    {

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        monthDropdown = new JComboBox<>(new String[]{
                "01 - January", "02 - February", "03 - March",
                "04 - April", "05 - May", "06 - June",
                "07 - July", "08 - August", "09 - September",
                "10 - October", "11 - November", "12 - December"
        });

        yearDropdown = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for (int y = currentYear; y >= currentYear - 5; y--)
        {
            yearDropdown.addItem(String.valueOf(y));
        }

        generateBtn = new JButton("Generate Report");
        generateBtn.addActionListener(e -> onGenerateReport());

        topPanel.add(new JLabel("Month:"));
        topPanel.add(monthDropdown);
        topPanel.add(new JLabel("Year:"));
        topPanel.add(yearDropdown);
        topPanel.add(generateBtn);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(reportArea);
        getContentPane().setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void onGenerateReport() {

        Manager manager = Session.getCurrentManager();

        if (manager == null)
        {
            DialogUtils.showError(this, "Only managers can view monthly reports.");
            return;
        }

        try {

            IManagerService ms = ServiceContext.getManagerService();
            Restaurant restaurant = ms.getRestaurantDetails(manager);

            if (restaurant == null)
            {
                DialogUtils.showError(this, "Restaurant not found for this manager.");
                return;
            }

            String monthString = (String) monthDropdown.getSelectedItem();
            if (monthString == null) return;

            int month = Integer.parseInt(monthString.substring(0, 2));
            int year = Integer.parseInt((String) yearDropdown.getSelectedItem());

            LocalDate date = LocalDate.of(year, month, 1);
            Date sqlDate = Date.valueOf(date);
            String report = ms.generateMonthlyReport(manager, restaurant, sqlDate);

            if (report == null || report.trim().isEmpty())
            {
                reportArea.setText("No report available for the selected month.");
            }
            else
            {
                reportArea.setText(report);
            }

        }
        catch (Exception ex)
        {
            DialogUtils.showError(this, "Failed to generate monthly report.");
        }
    }
}
