package FOS_UI.MockUI;

import FOS_CORE.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantListPanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel restaurantPanel;
    private JComboBox<String> cityDropdown;
    private JTextField searchField;
    private ArrayList<Restaurant> restaurants;
    private String selectedAddress;

    public RestaurantListPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("City:"));
        cityDropdown = new JComboBox<>(getCustomerAddresses());
        selectedAddress = cityDropdown.getItemAt(0);
        topPanel.add(cityDropdown);
        topPanel.add(new JLabel("Keyword:"));
        searchField = new JTextField(15);
        topPanel.add(searchField);
        JButton searchKeywordButton = new JButton("Search");
        topPanel.add(searchKeywordButton);
        add(topPanel, BorderLayout.NORTH);

        restaurantPanel = new JPanel();
        restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(restaurantPanel);
        add(scrollPane, BorderLayout.CENTER);
        cityDropdown.addActionListener(e -> searchByCity());
        searchKeywordButton.addActionListener(e -> searchByKeyword());
    }

    public void refresh() {
        restaurantPanel.removeAll();
        String city = extractCityFromAddress(selectedAddress);
        if (!city.isEmpty()) {
            loadRestaurantsByCity(city);
        }
        restaurantPanel.revalidate();
        restaurantPanel.repaint();
    }

    private void searchByCity() {
        selectedAddress = cityDropdown.getSelectedItem().toString();
        String city = extractCityFromAddress(selectedAddress);
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        loadRestaurantsByCity(city);
    }

    private void searchByKeyword() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            searchByCity();
            return;
        }
        loadRestaurantsByKeyword(keyword);
    }

    private void loadRestaurantsByCity(String city) {
        restaurantPanel.removeAll();
        RestaurantService service = mainFrame.getRestaurantService();
        this.restaurants = service.getRestaurantsByCity(city);

        if (restaurants.isEmpty()) {
            restaurantPanel.add(new JLabel("No restaurants found in " + city));
        } else {
            for (Restaurant restaurant : restaurants) {
                JPanel card = createRestaurantCard(restaurant);
                restaurantPanel.add(card);
            }
        }
        restaurantPanel.revalidate();
        restaurantPanel.repaint();
    }

    private void loadRestaurantsByKeyword(String keyword) {
        restaurantPanel.removeAll();
        RestaurantService service = mainFrame.getRestaurantService();
        this.restaurants = service.searchRestaurantsByKeyword(keyword,restaurants);

        if (restaurants.isEmpty()) {
            restaurantPanel.add(new JLabel("No restaurants found matching: " + keyword));
        } else {
            for (Restaurant restaurant : restaurants) {
                JPanel card = createRestaurantCard(restaurant);
                restaurantPanel.add(card);
            }
        }
        restaurantPanel.revalidate();
        restaurantPanel.repaint();
    }

    private JPanel createRestaurantCard(Restaurant restaurant) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(600, 150));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JLabel nameLabel = new JLabel(restaurant.getRestaurantName());
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        JLabel cuisineLabel = new JLabel("Cuisine: " + restaurant.getCuisineType());
        JLabel cityLabel = new JLabel("City: " + restaurant.getCity());

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(nameLabel);
        infoPanel.add(cuisineLabel);
        infoPanel.add(cityLabel);

        JButton viewMenuButton = new JButton("View Menu");
        viewMenuButton.addActionListener(e -> mainFrame.showMenu(restaurant));

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(viewMenuButton, BorderLayout.EAST);
        return card;
    }
    private String extractCityFromAddress(String address) {
        String[] parts = address.split(",");
        if (parts.length >= 2) {
            return parts[1].trim();
        }
        return "";
    }
    private String[] getCustomerAddresses() {
        List<Address> addresses = mainFrame.getCurrentCustomer().getAddresses();
        String[] addressStrings = new String[addresses.size()];
        for (int i = 0; i < addresses.size(); i++) {
            addressStrings[i] = addresses.get(i).toString();
        }
        return addressStrings;
    }
    public String getSelectedAddress() {
        return selectedAddress;
    }
}

