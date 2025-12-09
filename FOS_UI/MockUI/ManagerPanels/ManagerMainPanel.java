package FOS_UI.MockUI.ManagerPanels;

import FOS_CORE.Manager;
import FOS_CORE.ManagerService;
import FOS_UI.MockUI.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ManagerMainPanel extends JPanel {

    private MainFrame mainFrame;
    private CardLayout cardLayout;
    private RestaurantListPanel restaurantListPanel;

    private Manager currentManager;

    private ManagerService managerService;


    public ManagerMainPanel(MainFrame mainFrame, Manager manager) {
        this.mainFrame = mainFrame;
        this.currentManager = manager;
        managerService = new ManagerService();
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        initComponents();
    }
    private void initComponents() {

        restaurantListPanel = new RestaurantListPanel(this);

        add(restaurantListPanel, "MANAGER RESTAURANTS");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem logoutItem = new JMenuItem("Logout");

        logoutItem.addActionListener(e -> mainFrame.logout());
        menu.add(logoutItem);
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);

        showRestaurants();
    }

    public void showRestaurants() {
        restaurantListPanel.refresh();
        cardLayout.show(this, "RESTAURANTS");
    }

    public ManagerService getManagerService() {
        return managerService;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public RestaurantListPanel getRestaurantListPanel() {
        return restaurantListPanel;
    }

    public Manager getCurrentManager() {
        return currentManager;
    }
}
