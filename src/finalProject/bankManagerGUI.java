package finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class bankManagerGUI {
    private static bankManager bankManager = new bankManager();
    private static JPanel mainPanel, secondPanel;
    private static CardLayout cardLayout;
    private static JTextArea displayArea1, displayArea2;  // Two JTextAreas
    private static JLabel nameLabel, nameLabel2, clientN, clientA, isPrem, clientList, clientB;
    private static JButton goToSecond, backButton, addClient, removeClient;
    private static JTextField nameField, acctField, name2, acct2, balField;
    private static JComboBox premField;

    public static void main(String[] args) {
        cardLayout = new CardLayout();
        
        JFrame frame = new JFrame("Bank Manager");
        frame.setSize(550, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(cardLayout);

        setupMainPanel();
        setupSecondPanel();

        frame.add(mainPanel, "Main");
        frame.add(secondPanel, "Second");

        frame.setVisible(true);

        addClient.addActionListener(e -> addClient());
        removeClient.addActionListener(e -> removeClient());
    }

    // Setup the first screen
    private static void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null); 

        nameLabel = new JLabel("Register Client");
        nameLabel.setBounds(180, 10, 150, 30);
        mainPanel.add(nameLabel);

        clientN = new JLabel("Client Name:");
        clientN.setBounds(30, 60, 100, 20);
        mainPanel.add(clientN);
        
        clientA = new JLabel("Acct #:");
        clientA.setBounds(60, 120, 100, 20);
        mainPanel.add(clientA);
        
        clientB = new JLabel("Balance:");
        clientB.setBounds(58, 180, 100, 20);
        mainPanel.add(clientB);
        
        isPrem = new JLabel("Premium Member:");
        isPrem.setBounds(20, 240, 140, 20);
        mainPanel.add(isPrem);
        
        acctField = new JTextField();
        acctField.setBounds(120, 120, 150, 30);
        mainPanel.add(acctField);
        
        nameField = new JTextField();
        nameField.setBounds(120, 60, 150, 30);
        mainPanel.add(nameField);
        
        balField = new JTextField();
        balField.setBounds(120, 180, 150, 30);
        mainPanel.add(balField);
        
        String[] options = {"yes", "no"};
        premField = new JComboBox<>(options);
        premField.setBounds(140, 240, 100, 20);
        mainPanel.add(premField);
        
        clientList = new JLabel("Clients");
        clientList.setBounds(375, 30, 100, 20);
        mainPanel.add(clientList);
        
        displayArea1 = new JTextArea();
        displayArea1.setBounds(300, 60, 200, 300);
        mainPanel.add(displayArea1);        
        
        addClient = new JButton("add to client list");
        addClient.setBounds(75, 325, 150, 50);
        mainPanel.add(addClient);

        goToSecond = new JButton("remove client ->");
        goToSecond.setBounds(300, 400, 200, 30);
        goToSecond.addActionListener(e -> cardLayout.show(mainPanel.getParent(), "Second"));
        mainPanel.add(goToSecond);
    }

    // Setup the second screen
    private static void setupSecondPanel() {
        secondPanel = new JPanel();
        secondPanel.setLayout(null);

        nameLabel2 = new JLabel("Remove client");
        nameLabel2.setBounds(180, 10, 150, 30);
        secondPanel.add(nameLabel2);

        clientN = new JLabel("Client Name:");
        clientN.setBounds(30, 60, 100, 20);
        secondPanel.add(clientN);
        
        clientA = new JLabel("Acct #:");
        clientA.setBounds(60, 120, 100, 20);
        secondPanel.add(clientA);

        acct2 = new JTextField();
        acct2.setBounds(120, 120, 150, 30);
        secondPanel.add(acct2);
        
        name2 = new JTextField();
        name2.setBounds(120, 60, 150, 30);
        secondPanel.add(name2);
        
        clientList = new JLabel("Clients");
        clientList.setBounds(375, 30, 100, 20);
        secondPanel.add(clientList);
        
        displayArea2 = new JTextArea();  // Separate JTextArea for the second panel
        displayArea2.setBounds(300, 60, 200, 300);
        secondPanel.add(displayArea2);        
        
        removeClient = new JButton("remove client");
        removeClient.setBounds(75, 175, 150, 50);
        secondPanel.add(removeClient);

        backButton = new JButton("<- add client");
        backButton.setBounds(25, 400, 150, 30);
        backButton.addActionListener(e -> cardLayout.show(secondPanel.getParent(), "Main"));
        secondPanel.add(backButton);
    }

    public static void updateDisplay() {
        displayArea1.setText("");  
        displayArea2.setText("");  

        for (client c : bankManager.getAllClients()) {
            if (c != null) {
                displayArea1.append( c.toString() + "\n" +"-------------------" + "\n");    // Full info on first screen
                displayArea2.append(c.getName() + "    " + c.getAcct() + "\n");  // Name and acct# only on second screen
            }
        }
    }


    private static void addClient() {
        String name = nameField.getText();
        String acctStr = acctField.getText();
        double balance = Double.parseDouble(balField.getText());
        boolean isPrem = premField.getSelectedItem().toString().equalsIgnoreCase("yes");
        if (!acctStr.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Account number must be exactly 4 digits.", "Invalid Account Number", JOptionPane.ERROR_MESSAGE);
            acctField.setText("");
            acctField.requestFocus();
            return;
        }
        int acct = Integer.parseInt(acctStr); 

        String message;
        if (isPrem) {
            message = bankManager.addClient(new premiumMember(name, acct, balance, true));
        } else {
            message = bankManager.addClient(new client(name, acct, balance));
        }

        displayArea1.setText(message + "\n"); // Show the result message here
        updateDisplay();  
        nameField.setText("");
        acctField.setText("");
        balField.setText("");
        
        Color originalColor = nameField.getBackground();  // Save original color

        nameField.setBackground(Color.GREEN);
        acctField.setBackground(Color.GREEN);
        balField.setBackground(Color.GREEN);

       
        Timer timer = new Timer(100, e -> {
            nameField.setBackground(originalColor);
            acctField.setBackground(originalColor);
            balField.setBackground(originalColor);
        });
        timer.setRepeats(false); // Only run once
        timer.start();
        
    }

    private static void removeClient() {
        String nameToRemove = name2.getText();
        int acctToRemove = Integer.parseInt(acct2.getText());

        client clientToRemove = null;
        for (client c : bankManager.getAllClients()) {
            if (c != null && c.getAcct() == acctToRemove && c.getName().equalsIgnoreCase(nameToRemove)) {
                clientToRemove = c;
                break;
            }
        }

        if (clientToRemove != null) {
            bankManager.removeClient(clientToRemove);
            updateDisplay();  // Update the display after removal
        } else {
        	Color originalColor = name2.getBackground();  // Save original color
        	displayArea2.setText("Client not found.\n");
        	name2.setBackground(Color.red);
            acct2.setBackground(Color.red);
        	
        	Timer timer = new Timer(1000, e -> {
            updateDisplay();
            name2.setBackground(originalColor);
            acct2.setBackground(originalColor);
        	});
        	timer.setRepeats(false);
        	timer.start();
        }
        
        name2.setText("");
        acct2.setText("");
    }
}

