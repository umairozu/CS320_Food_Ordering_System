package FOS_UI;

import FOS_CORE.Customer;
import FOS_CORE.IOrderService;
import FOS_CORE.Order;
import FOS_CORE.OrderStatus;
import FOS_CORE.Rating;
import javax.swing.*;
import java.awt.*;

// worked on by Umair Ahmad
public class RatingDialog extends JDialog{

    private final Order order;
    private final IOrderService orderService;

    private JSpinner ratingSpinner;
    private JTextArea commentArea;
    private JButton submitButton;

    public RatingDialog(JFrame parent, Order order) {
        super(parent, "Rate Order", true);
        this.order = order;
        this.orderService = ServiceContext.getOrderService();

        setSize(420, 320);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        initComponents();
        updateStateFromOrder();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel(order != null
                ? "Rate Order #" + order.getOrderID()
                : "No order selected");
        headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD, 16f));
        contentPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.add(new JLabel("Rating (1-5):"));
        ratingSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 5, 1));
        ratingPanel.add(ratingSpinner);
        formPanel.add(ratingPanel);

        ///
        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.add(new JLabel("Comment (optional):"), BorderLayout.NORTH);
        commentArea = new JTextArea(5, 25);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentPanel.add(new JScrollPane(commentArea), BorderLayout.CENTER);
        formPanel.add(commentPanel);

        contentPanel.add(formPanel, BorderLayout.CENTER);

    }

}
