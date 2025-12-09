// java
package FOS_UI;

import FOS_CORE.Manager;
import FOS_CORE.Restaurant;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

// Being Done by Umair Ahmad (updated)
public class ManagerDashboardWindow extends JFrame {

    private final JPanel gridPanel;
    private int columns = 2;

    public ManagerDashboardWindow() {
        setTitle("Manager Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel header = new JLabel("Manager Dashboard");
        header.setFont(header.getFont().deriveFont(Font.BOLD, 22f));
        header.setBorder(new EmptyBorder(16, 20, 8, 20));

        gridPanel = new JPanel();
        gridPanel.setBackground(new Color(0, 0, 0, 0));
        gridPanel.setLayout(new GridLayout(0, columns, 20, 20));
        gridPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(getContentPane().getBackground());

        getContentPane().setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // adjust columns on resize for a more responsive feel
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = getContentPane().getWidth();
                int newCols = Math.max(1, w / 360); // each card ~ 320-360px
                if (newCols != columns) {
                    columns = newCols;
                    gridPanel.setLayout(new GridLayout(0, columns, 20, 20));
                    gridPanel.revalidate();
                }
            }
        });
    }

    public ManagerDashboardWindow(Manager manager) {
        this();
        loadRestaurants(manager);
    }

    private void loadRestaurants(Manager manager) {
        if (manager == null) {
            setRestaurants(new ArrayList<>());
            return;
        }

        try {
            ArrayList<Restaurant> restaurants = ServiceContext.getManagerService().getManagerRestaurants(manager);
            List<RestaurantCard> cards = new ArrayList<>();
            
            for (Restaurant restaurant : restaurants) {
                String name = restaurant.getRestaurantName();
                String cuisine = restaurant.getCuisineType();
                String city = restaurant.getCity();
                // Restaurant class doesn't have image fields, so we'll pass null
                cards.add(new RestaurantCard(name, cuisine, city, null, null));
            }
            
            setRestaurants(cards);
        } catch (Exception e) {
            System.err.println("Error loading restaurants for manager: " + e.getMessage());
            e.printStackTrace();
            setRestaurants(new ArrayList<>());
        }
    }

    // populate the dashboard
    public void setRestaurants(List<RestaurantCard> restaurants) {
        gridPanel.removeAll();
        if (restaurants == null || restaurants.isEmpty()) {
            JLabel none = new JLabel("No restaurants found.", SwingConstants.CENTER);
            none.setBorder(new EmptyBorder(40, 0, 40, 0));
            gridPanel.setLayout(new GridLayout(1, 1));
            gridPanel.add(none);
        } else {
            gridPanel.setLayout(new GridLayout(0, columns, 20, 20));
            for (RestaurantCard r : restaurants) {
                gridPanel.add(makeCard(r));
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private JPanel makeCard(RestaurantCard r) {
        JPanel card = new JPanel(new BorderLayout(12, 0));
        card.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        card.setBackground(new Color(255, 255, 255));
        card.setPreferredSize(new Dimension(320, 120));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.setOpaque(true);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220,220,220),1,true),
                new EmptyBorder(12,12,12,12)
        ));

        // image area
        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(72, 72));
        BufferedImage img = loadImage(r);
        if (img != null) {
            Image scaled = img.getScaledInstance(72, 72, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaled));
            imgLabel.setBorder(new LineBorder(new Color(255,102,0), 2, true));
        } else {
            JPanel placeholder = new JPanel(new BorderLayout());
            placeholder.setPreferredSize(new Dimension(72,72));
            placeholder.setBackground(new Color(248,248,248));
            placeholder.setBorder(new LineBorder(new Color(220,220,220),1,true));
            JLabel plus = new JLabel("+", SwingConstants.CENTER);
            plus.setFont(plus.getFont().deriveFont(Font.BOLD, 20f));
            placeholder.add(plus, BorderLayout.CENTER);
            imgLabel = new JLabel();
            imgLabel.setIcon(iconFromComponent(placeholder));
        }

        // main text
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        JLabel name = new JLabel(r.name);
        name.setFont(name.getFont().deriveFont(Font.BOLD, 16f));
        JLabel meta = new JLabel(String.format("Cuisine: %s    Location: %s", r.cuisineType, r.city));
        meta.setForeground(new Color(90,90,90));
        meta.setFont(meta.getFont().deriveFont(12f));
        main.add(name);
        main.add(Box.createVerticalStrut(8));
        main.add(meta);

        // arrow
        JLabel arrow = new JLabel("\u2192"); // →
        arrow.setFont(arrow.getFont().deriveFont(Font.BOLD, 28f));
        arrow.setForeground(new Color(255,102,0));
        JPanel arrowPanel = new JPanel(new BorderLayout());
        arrowPanel.setOpaque(false);
        arrowPanel.add(arrow, BorderLayout.SOUTH);

        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(false);
        left.add(imgLabel, BorderLayout.WEST);

        card.add(left, BorderLayout.WEST);
        card.add(main, BorderLayout.CENTER);
        card.add(arrowPanel, BorderLayout.EAST);

        // hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(255,102,0), 2, true),
                        new EmptyBorder(12,12,12,12)
                ));
                card.setBackground(new Color(255,255,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(220,220,220), 1, true),
                        new EmptyBorder(12,12,12,12)
                ));
                card.setBackground(new Color(255,255,255));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // placeholder action: open/manage restaurant
                JOptionPane.showMessageDialog(ManagerDashboardWindow.this, "Open: " + r.name);
            }
        });

        return card;
    }

    private BufferedImage loadImage(RestaurantCard r) {
        try {
            if (r.imageBase64 != null && !r.imageBase64.isBlank()) {
                byte[] bytes = Base64.getDecoder().decode(r.imageBase64);
                return ImageIO.read(new ByteArrayInputStream(bytes));
            } else if (r.imageUrl != null && !r.imageUrl.isBlank()) {
                return ImageIO.read(new URL(r.imageUrl));
            }
        } catch (Exception ignored) {}
        return null;
    }

    private Icon iconFromComponent(JComponent comp) {
        comp.setSize(comp.getPreferredSize());
        BufferedImage img = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        comp.paint(g);
        g.dispose();
        return new ImageIcon(img);
    }

    // Simple DTO to provide the required fields for the UI.
    public static class RestaurantCard {
        public final String name;
        public final String cuisineType;
        public final String city;
        public final String imageBase64;
        public final String imageUrl;

        public RestaurantCard(String name, String cuisineType, String city, String imageBase64, String imageUrl) {
            this.name = name;
            this.cuisineType = cuisineType;
            this.city = city;
            this.imageBase64 = imageBase64;
            this.imageUrl = imageUrl;
        }
    }
}
