import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent; <- DISABLED DUE TO NOT USED!
// import java.awt.event.ActionListener; <- DISABLED DUE TO NOT USED!
import java.util.ArrayList;

public class FootballGUI extends JFrame {
    // We use ArrayList so we can add new players dynamically
    private ArrayList<FootballPlayer> playersList;
    private DefaultComboBoxModel<String> dropdownModelDashboard;
    private DefaultComboBoxModel<String> dropdownModelCompare1;
    private DefaultComboBoxModel<String> dropdownModelCompare2;

    public FootballGUI() {
        setTitle("Professional Football Player Statistic System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize dynamic player database
        playersList = new ArrayList<>();
        // Note: The last parameter is the expected image filename (e.g., "messi.png")
        playersList.add(new Attacker("Lionel Messi", "Inter Miami", 1059, 830, 360, "messi.png"));
        playersList.add(new Attacker("Cristiano Ronaldo", "Al Nassr", 1215, 885, 250, "ronaldo.jpg"));
        playersList.add(new Attacker("Vinicius Junior", "Real Madrid", 372, 125, 87, "vini.jpg"));
        playersList.add(new Defender("Virgil van Dijk", "Liverpool", 530, 750, 210, "vandijk.jpg"));
        playersList.add(new Defender("Jay Idzes", "Venezia FC", 120, 180, 45, "idzes.jpg"));

        // Initialize UI Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Dashboard", createDashboardTab());
        tabbedPane.addTab("Compare Players", createCompareTab());
        tabbedPane.addTab("Add Player", createAddPlayerTab());

        add(tabbedPane, BorderLayout.CENTER);
        updateDropdowns(); // Populate all dropdowns with initial data
    }

    // --- TAB 1: MAIN DASHBOARD ---
    private JPanel createDashboardTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        dropdownModelDashboard = new DefaultComboBoxModel<>();
        JComboBox<String> dropdown = new JComboBox<>(dropdownModelDashboard);
        JButton btnShow = new JButton("View Profile");
        
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Player: "));
        topPanel.add(dropdown);
        topPanel.add(btnShow);

        // Photo display area
        JLabel photoLabel = new JLabel("No Image", SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(200, 200));
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JTextArea statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(photoLabel, BorderLayout.WEST);
        centerPanel.add(new JScrollPane(statsArea), BorderLayout.CENTER);

        btnShow.addActionListener(e -> {
            int index = dropdown.getSelectedIndex();
            if (index >= 0) {
                FootballPlayer p = playersList.get(index);
                statsArea.setText(p.getStats());
                
                // Load Photo safely
                try {
                    // Looks for an 'images' folder inside your project directory
                    ImageIcon icon = new ImageIcon("images/" + p.getImageFileName());
                    Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    photoLabel.setIcon(new ImageIcon(img));
                    photoLabel.setText(""); // Clear fallback text
                } catch (Exception ex) {
                    photoLabel.setIcon(null);
                    photoLabel.setText("Image not found");
                }
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    }

    // --- TAB 2: COMPARISON TOOL ---
    private JPanel createCompareTab() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        dropdownModelCompare1 = new DefaultComboBoxModel<>();
        dropdownModelCompare2 = new DefaultComboBoxModel<>();
        
        // Left Side
        JPanel leftPanel = new JPanel(new BorderLayout());
        JComboBox<String> combo1 = new JComboBox<>(dropdownModelCompare1);
        JTextArea stats1 = new JTextArea();
        leftPanel.add(combo1, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(stats1), BorderLayout.CENTER);
        
        // Right Side
        JPanel rightPanel = new JPanel(new BorderLayout());
        JComboBox<String> combo2 = new JComboBox<>(dropdownModelCompare2);
        JTextArea stats2 = new JTextArea();
        rightPanel.add(combo2, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(stats2), BorderLayout.CENTER);
        
        // Action Listeners to auto-update when selected
        combo1.addActionListener(e -> {
            int idx = combo1.getSelectedIndex();
            if(idx >= 0) stats1.setText(playersList.get(idx).getStats());
        });
        
        combo2.addActionListener(e -> {
            int idx = combo2.getSelectedIndex();
            if(idx >= 0) stats2.setText(playersList.get(idx).getStats());
        });

        panel.add(leftPanel);
        panel.add(rightPanel);
        return panel;
    }

    // --- TAB 3: ADD NEW PLAYER ---
    private JPanel createAddPlayerTab() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField nameField = new JTextField();
        JTextField clubField = new JTextField();
        JTextField matchesField = new JTextField();
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"Attacker", "Defender"});
        JTextField stat1Field = new JTextField(); // Goals or Tackles
        JTextField stat2Field = new JTextField(); // Assists or Clean Sheets
        JButton btnSave = new JButton("Save New Player");

        panel.add(new JLabel("Player Name:")); panel.add(nameField);
        panel.add(new JLabel("Club:")); panel.add(clubField);
        panel.add(new JLabel("Matches Played:")); panel.add(matchesField);
        panel.add(new JLabel("Role:")); panel.add(roleBox);
        
        JLabel stat1Label = new JLabel("Goals / Tackles:");
        JLabel stat2Label = new JLabel("Assists / Clean Sheets:");
        panel.add(stat1Label); panel.add(stat1Field);
        panel.add(stat2Label); panel.add(stat2Field);
        
        panel.add(new JLabel("")); panel.add(btnSave); // Empty label for spacing

        btnSave.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String club = clubField.getText();
                int matches = Integer.parseInt(matchesField.getText());
                int s1 = Integer.parseInt(stat1Field.getText());
                int s2 = Integer.parseInt(stat2Field.getText());
                String role = (String) roleBox.getSelectedItem();

                // Dynamic object creation based on selection
                if (role.equals("Attacker")) {
                    playersList.add(new Attacker(name, club, matches, s1, s2, "default.jpg"));
                } else {
                    playersList.add(new Defender(name, club, matches, s1, s2, "default.jpg"));
                }

                updateDropdowns(); // Refresh all GUI dropdowns
                JOptionPane.showMessageDialog(this, "Player Added Successfully!");
                
                // Clear fields
                nameField.setText(""); clubField.setText(""); matchesField.setText("");
                stat1Field.setText(""); stat2Field.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers for stats.");
            }
        });

        return panel;
    }

    // Helper method to keep all dropdowns synchronized
    private void updateDropdowns() {
        dropdownModelDashboard.removeAllElements();
        dropdownModelCompare1.removeAllElements();
        dropdownModelCompare2.removeAllElements();
        
        for (FootballPlayer p : playersList) {
            dropdownModelDashboard.addElement(p.getName());
            dropdownModelCompare1.addElement(p.getName());
            dropdownModelCompare2.addElement(p.getName());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FootballGUI().setVisible(true));
    }
}