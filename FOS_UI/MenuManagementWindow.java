package FOS_UI;

// Being done by Hassan Askari

import FOS_CORE.MenuItem;
import FOS_CORE.Restaurant;
import FOS_CORE.IRestaurantService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MenuManagementWindow extends JFrame {

    private JComboBox<Restaurant> restaurantDropdown;
    private JTable menuTable;
    private DefaultTableModel menuTableModel;

    public MenuManagementWindow()
    {
        setTitle("Food Ordering System – Menu Management");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        loadRestaurants();
    }

    private void initComponents()
    {

        restaurantDropdown = new JComboBox<>();
        restaurantDropdown.addActionListener(e -> loadMenuForSelectedRestaurant());

        menuTableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Description", "Price", "Discounted Price"}, 0
        );
        menuTable = new JTable(menuTableModel);
        menuTable.setEnabled(false); // READ ONLY TABLE
        JScrollPane tableScroll = new JScrollPane(menuTable);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Restaurant:"));
        topPanel.add(restaurantDropdown);

        getContentPane().setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
    }

    private void loadRestaurants()
    {
        try {
            IRestaurantService rs = ServiceContext.getRestaurantService();

            ArrayList<Restaurant> restaurants = rs.getRestaurantsByCity("Lahore");

            if (restaurants == null || restaurants.isEmpty())
            {
                DialogUtils.showInfo(this, "No restaurants found for the selected city.");
                return;
            }

            for (Restaurant r : restaurants)
            {
                restaurantDropdown.addItem(r);
            }

        }
        catch (Exception ex)
        {
            DialogUtils.showError(this, "Failed to load restaurants.");
        }
    }

    private void loadMenuForSelectedRestaurant()
    {
        Restaurant selected = (Restaurant) restaurantDropdown.getSelectedItem();
        if (selected == null) return;

        try
        {
            IRestaurantService rs = ServiceContext.getRestaurantService();
            ArrayList<MenuItem> menu = rs.fetchRestaurantMenu(selected);

            menuTableModel.setRowCount(0);

            if (menu == null)
            {
                DialogUtils.showError(this, "Failed to load menu.");
                return;
            }

            for (MenuItem m : menu)
            {
                double discountedPrice = rs.calculateMenuItemDiscount(m);

                menuTableModel.addRow(new Object[]{
                        m.getMenuItemID(),
                        m.getItemName(),
                        m.getDescription(),
                        m.getPrice(),
                        discountedPrice
                });
            }

        }
        catch (Exception ex)
        {
            DialogUtils.showError(this, "An error occurred while loading menu.");
        }
    }
}
