/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.VehiclesDao;
import happytravell.model.VehiclesData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class AdminVehiclesDetailsController {
    private int currentAdminId;
    private AdminVehiclesDetailsView vehiclesDetailsView;
    private VehiclesDao vehiclesDao;
    private JTable vehiclesTable;
    private DefaultTableModel tableModel;
    
    public AdminVehiclesDetailsController(AdminVehiclesDetailsView adminVehiclesDetailsView, int adminId ) {
        this.vehiclesDetailsView = adminVehiclesDetailsView;
        this.currentAdminId = adminId;
        this.vehiclesDao = new VehiclesDao();
        
        // Initialize components and event listeners
        initializeVehiclesComponents();
        initializeEventListeners();
        loadVehiclesData();
        
        // Setup navigation
        this.vehiclesDetailsView.DashboardNavigation(new DashboardNav(adminVehiclesDetailsView.getDashboardlabel()));
        this.vehiclesDetailsView.BookingDetailsNavigation(new BookingDetailsNav(adminVehiclesDetailsView.getBookingDetailslabel()));
        this.vehiclesDetailsView.BusTicketsNavigation(new BusTicketsNav(adminVehiclesDetailsView.getBusTicketslabel()));
        this.vehiclesDetailsView.RouteDetailsNavigation(new RouteDetailsNav(adminVehiclesDetailsView.getRouteDetailslabel()));
        this.vehiclesDetailsView.VehiclesDetailsNavigation(new VehiclesDetailsNav(adminVehiclesDetailsView.getVehiclesDetailslabel()));
        this.vehiclesDetailsView.ProfileNavigation(new ProfileNav(adminVehiclesDetailsView.getProfilelabel()));
        this.vehiclesDetailsView.LogOutNavigation(new LogOutNav(adminVehiclesDetailsView.getLogOutlabel()));
    }
    
    private void initializeVehiclesComponents() {
        setupVehiclesTable();
    }
    
    private void initializeEventListeners() {
        // Add action listener for the Add Vehicles button
        vehiclesDetailsView.getAddVeheclesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddVehiclePopup();
            }
        });
    }
    
    private void setupVehiclesTable() {
        // Create table model with column names
        String[] columnNames = {"ID", "Type", "Number", "Seats", "Name", "Color", "Agency", "Actions"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Only actions column is editable
            }
        };
        
        vehiclesTable = new JTable(tableModel);
        vehiclesTable.setFont(new Font("Candara", Font.PLAIN, 12));
        vehiclesTable.setRowHeight(30);
        vehiclesTable.getTableHeader().setFont(new Font("Candara", Font.BOLD, 12));
        vehiclesTable.getTableHeader().setBackground(new Color(200, 143, 75));
        vehiclesTable.getTableHeader().setForeground(Color.WHITE);
        
        // Create scroll pane for table
        JScrollPane tableScrollPane = new JScrollPane(vehiclesTable);
        tableScrollPane.setBounds(10, 50, 480, 200);

        // Add table to the main panel (jPanel2)
        JPanel vehiclesDetailsPanel = (JPanel) vehiclesDetailsView.getContentPane().getComponent(0);
        
        // Get the correct panel - check if it's directly accessible or within a scroll pane
        JPanel jPanel2;
        try {
            // Try to get jPanel2 directly
            jPanel2 = (JPanel) vehiclesDetailsPanel.getComponent(2);
        } catch (Exception e) {
            // If that fails, try to get it from a scroll pane
            try {
                JScrollPane mainScrollPane = (JScrollPane) vehiclesDetailsPanel.getComponent(2);
                jPanel2 = (JPanel) mainScrollPane.getViewport().getView();
            } catch (Exception ex) {
                // Create a new panel if we can't find the existing one
                jPanel2 = new JPanel();
                jPanel2.setLayout(null); // Use absolute layout
                vehiclesDetailsPanel.add(jPanel2);
            }
        }
        
        // Set the layout to null (absolute layout) if it's not already set
        if (jPanel2.getLayout() == null || jPanel2.getLayout().getClass().getName().contains("AbsoluteLayout")) {
            jPanel2.setLayout(null);
        }
        
        jPanel2.add(tableScrollPane);
        jPanel2.revalidate();
        jPanel2.repaint();
    }
    
    private void showAddVehiclePopup() {
        AddVehiclePopup popup = new AddVehiclePopup(vehiclesDetailsView);
        
        // Add action listener for the add button in popup
        popup.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (popup.validateFields()) {
                    // Check if vehicle number already exists
                    if (vehiclesDao.isVehicleNumberExists(popup.getVehicleNumber())) {
                        JOptionPane.showMessageDialog(popup, 
                            "Vehicle number already exists! Please use a different number.", 
                            "Duplicate Entry", 
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    // Create new vehicle object
                    VehiclesData newVehicle = new VehiclesData(
                        popup.getVehicleType(),
                        popup.getVehicleNumber(),
                        Integer.parseInt(popup.getNumberOfSeats()),
                        popup.getVehicleName(),
                        popup.getVehicleColor(),
                        popup.getTravelAgency()
                    );
                    
                    // Add vehicle to database
                    if (vehiclesDao.addVehicle(newVehicle)) {
                        popup.showSuccess("Vehicle added successfully!");
                        popup.clearFields();
                        popup.dispose();
                        
                        // Refresh the vehicles table
                        loadVehiclesData();
                    } else {
                        JOptionPane.showMessageDialog(popup, 
                            "Failed to add vehicle. Please try again.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        popup.setVisible(true);
    }
    
    private void loadVehiclesData() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Get all vehicles from database
        List<VehiclesData> vehicles = vehiclesDao.getAllVehicles();
        
        // Add vehicles to table
        for (VehiclesData vehicle : vehicles) {
            Object[] rowData = {
                vehicle.getVehicleId(),
                vehicle.getVehicleType(),
                vehicle.getVehicleNumber(),
                vehicle.getNumberOfSeats(),
                vehicle.getVehicleName(),
                vehicle.getVehicleColor(),
                vehicle.getTravelAgency(),
                "Edit | Delete" // Actions column
            };
            tableModel.addRow(rowData);
        }
    }
    
    public void open(){
        this.vehiclesDetailsView.setVisible(true);
    } 
    
    public void close(){
        this.vehiclesDetailsView.dispose();
    }
    
    // Dashboard Navigation
    class DashboardNav implements MouseListener{
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label){
            this.dashboardLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView admindashboardView = new AdmindashboardView();
            AdminDashboardController AdminDashboard= new AdminDashboardController(admindashboardView, currentAdminId);
            AdminDashboard.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            dashboardLabel.setForeground(Color.WHITE);
            dashboardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            dashboardLabel.setForeground(Color.BLACK);
            dashboardLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    // Booking Details Navigation
    class BookingDetailsNav implements MouseListener{
        private JLabel bookingDetailsLabel;
        
        public BookingDetailsNav(JLabel label){
            this.bookingDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBookingDetailsView adminBookingDetailsView = new AdminBookingDetailsView();
            AdminBookingDetailsController AdminBookingDetails= new AdminBookingDetailsController(adminBookingDetailsView, currentAdminId);
            AdminBookingDetails.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.RED);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.BLACK);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    // Route Details Navigation
    class RouteDetailsNav implements MouseListener{
        private JLabel routeDetailsLabel;      
        
        public RouteDetailsNav(JLabel label){
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController AdminRouteDetails= new AdminRouteDetailsController(adminRouteDetailsView, currentAdminId);
            AdminRouteDetails.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            routeDetailsLabel.setForeground(Color.WHITE);
            routeDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            routeDetailsLabel.setForeground(Color.BLACK);
            routeDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    // Bus Tickets Navigation  
    class BusTicketsNav implements MouseListener{
        private JLabel busTicketsLabel;
        
        public BusTicketsNav(JLabel label){
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBusTicketsView adminBusTicketsView = new AdminBusTicketsView();
            AdminBusTicketsController AdminBusTickets= new AdminBusTicketsController(adminBusTicketsView, currentAdminId);
            AdminBusTickets.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            busTicketsLabel.setForeground(Color.WHITE);
            busTicketsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            busTicketsLabel.setForeground(Color.BLACK);
            busTicketsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    //  Vehicles Details Navigation
    class VehiclesDetailsNav implements MouseListener{
        
        private JLabel vehiclesDetailsLabel;
        public VehiclesDetailsNav(JLabel label){
            this.vehiclesDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            // Already on this page
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            vehiclesDetailsLabel.setForeground(Color.WHITE);
            vehiclesDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            vehiclesDetailsLabel.setForeground(Color.BLACK);
            vehiclesDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    // Profile Navigation
    class ProfileNav implements MouseListener{
        private JLabel profileLabel;
        
        public ProfileNav(JLabel label){
            this.profileLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminProfileView adminProfileView = new AdminProfileView();
            AdminProfileController  AdminProfile= new  AdminProfileController(adminProfileView , currentAdminId);
            AdminProfile.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            profileLabel.setForeground(Color.RED);
            profileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            profileLabel.setForeground(Color.BLACK);
            profileLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    // LogOut Navigation
    class LogOutNav implements MouseListener{
        private JLabel logOutLabel;
        
        public LogOutNav(JLabel label){
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                vehiclesDetailsView.dispose();

                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            logOutLabel.setForeground(Color.WHITE);
            logOutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            logOutLabel.setForeground(Color.BLACK);
            logOutLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }

    // Add Vehicle Popup Class
    public class AddVehiclePopup extends JDialog {
        
        private JTextField vehicleNumberField;
        private JTextField numberOfSeatsField;
        private JTextField vehicleNameField;
        private JTextField vehicleColorField;
        private JTextField travelAgencyField;
        private JButton addButton;
        private JButton cancelButton;
        private JComboBox<String> vehicleTypeCombo;
        
        public AddVehiclePopup(JFrame parent) {
            super(parent, "Add New Vehicle", true);
            initComponents();
            setupLayout();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);
            pack();
            setLocationRelativeTo(parent);
        }
        
        private void initComponents() {
            // Initialize components
            String[] vehicleTypes = {"Bus", "Car", "Taxi", "Jeep"};
            vehicleTypeCombo = new JComboBox<>(vehicleTypes);
            vehicleTypeCombo.setFont(new Font("Candara", Font.PLAIN, 12));
            
            vehicleNumberField = new JTextField(15);
            vehicleNumberField.setFont(new Font("Candara", Font.PLAIN, 12));
            
            numberOfSeatsField = new JTextField(15);
            numberOfSeatsField.setFont(new Font("Candara", Font.PLAIN, 12));
            
            vehicleNameField = new JTextField(15);
            vehicleNameField.setFont(new Font("Candara", Font.PLAIN, 12));
            
            vehicleColorField = new JTextField(15);
            vehicleColorField.setFont(new Font("Candara", Font.PLAIN, 12));
            
            travelAgencyField = new JTextField(15);
            travelAgencyField.setFont(new Font("Candara", Font.PLAIN, 12));
            
            addButton = new JButton("Add Vehicle");
            addButton.setFont(new Font("Candara", Font.BOLD, 12));
            addButton.setBackground(new Color(200, 143, 75));
            addButton.setForeground(Color.WHITE);
            addButton.setFocusPainted(false);
            addButton.setBorder(BorderFactory.createRaisedBevelBorder());        
            
            cancelButton = new JButton("Cancel");
            cancelButton.setFont(new Font("Candara", Font.BOLD, 12));
            cancelButton.setBackground(new Color(220, 220, 220));
            cancelButton.setForeground(Color.BLACK);
            cancelButton.setFocusPainted(false);
            cancelButton.setBorder(BorderFactory.createRaisedBevelBorder());        
            
            // Add action listener for cancel button
            cancelButton.addActionListener(e -> dispose());
        }
        
        private void setupLayout() {
            setLayout(new BorderLayout());
            
            // Main panel with form
            JPanel mainPanel = new JPanel(new GridBagLayout());
            mainPanel.setBackground(new Color(255, 242, 227));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.anchor = GridBagConstraints.WEST;
            
            // Title
            JLabel titleLabel = new JLabel("Add New Vehicle");
            titleLabel.setFont(new Font("Constantia", Font.BOLD, 16));
            titleLabel.setForeground(new Color(101, 67, 33));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(titleLabel, gbc);
            
            // Reset grid settings
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;
            
            // Vehicle Type
            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(createLabel("Vehicle Type:"), gbc);
            gbc.gridx = 1;
            mainPanel.add(vehicleTypeCombo, gbc);
            
            // Vehicle Number
            gbc.gridx = 0;
            gbc.gridy = 2;
            mainPanel.add(createLabel("Vehicle Number:"), gbc);
            gbc.gridx = 1;
            mainPanel.add(vehicleNumberField, gbc);
            
            // Number of Seats
            gbc.gridx = 0;
            gbc.gridy = 3;
            mainPanel.add(createLabel("Number of Seats:"), gbc);
            gbc.gridx = 1;
            mainPanel.add(numberOfSeatsField, gbc);
            
            // Vehicle Name
            gbc.gridx = 0;
            gbc.gridy = 4;
            mainPanel.add(createLabel("Vehicle Name:"), gbc);
            gbc.gridx = 1;
            mainPanel.add(vehicleNameField, gbc);
            
            // Vehicle Color
            gbc.gridx = 0;
            gbc.gridy = 5;
            mainPanel.add(createLabel("Vehicle Color:"), gbc);
            gbc.gridx = 1;
            mainPanel.add(vehicleColorField, gbc);
            
            // Travel Agency
            gbc.gridx = 0;
            gbc.gridy = 6;
            mainPanel.add(createLabel("Travel Agency:"), gbc);
            gbc.gridx = 1;
            mainPanel.add(travelAgencyField, gbc);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setBackground(new Color(255, 242, 227));
            buttonPanel.add(addButton);
            buttonPanel.add(cancelButton);
            
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(20, 8, 8, 8);
            mainPanel.add(buttonPanel, gbc);
            
            add(mainPanel, BorderLayout.CENTER);
        }
        
        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setFont(new Font("Candara", Font.BOLD, 12));
            label.setForeground(new Color(101, 67, 33));
            return label;
        }
        
        // Getter methods for accessing field values
        public String getVehicleType() {
            return (String) vehicleTypeCombo.getSelectedItem();
        }
        
        public String getVehicleNumber() {
            return vehicleNumberField.getText().trim();
        }
        
        public String getNumberOfSeats() {
            return numberOfSeatsField.getText().trim();
        }
        
        public String getVehicleName() {
            return vehicleNameField.getText().trim();
        }
        
        public String getVehicleColor() {
            return vehicleColorField.getText().trim();
        }
        
        public String getTravelAgency() {
            return travelAgencyField.getText().trim();
        }
        
        // Method to add action listener to add button
        public void addAddButtonListener(ActionListener listener) {
            addButton.addActionListener(listener);
        }
        
        // Method to clear all fields
        public void clearFields() {
            vehicleTypeCombo.setSelectedIndex(0);
            vehicleNumberField.setText("");
            numberOfSeatsField.setText("");
            vehicleNameField.setText("");
            vehicleColorField.setText("");
            travelAgencyField.setText("");
        }
        
        // Method to validate fields
        public boolean validateFields() {
            if (getVehicleNumber().isEmpty()) {
                showError("Vehicle Number is required!");
                vehicleNumberField.requestFocus();
                return false;
            }
            
            if (getNumberOfSeats().isEmpty()) {
                showError("Number of Seats is required!");
                numberOfSeatsField.requestFocus();
                return false;
            }
            
            try {
                int seats = Integer.parseInt(getNumberOfSeats());
                if (seats <= 0) {
                    showError("Number of Seats must be a positive number!");
                    numberOfSeatsField.requestFocus();
                    return false;
                }
            } catch (NumberFormatException e) {
                showError("Number of Seats must be a valid number!");
                numberOfSeatsField.requestFocus();
                return false;
            }
            
            if (getVehicleName().isEmpty()) {
                showError("Vehicle Name is required!");
                vehicleNameField.requestFocus();
                return false;
            }
            
            if (getVehicleColor().isEmpty()) {
                showError("Vehicle Color is required!");
                vehicleColorField.requestFocus();
                return false;
            }
            
            if (getTravelAgency().isEmpty()) {
                showError("Travel Agency is required!");
                travelAgencyField.requestFocus();
                return false;
            }
            
            return true;
        }
        
        private void showError(String message) {
            JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        
        public void showSuccess(String message) {
            JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}