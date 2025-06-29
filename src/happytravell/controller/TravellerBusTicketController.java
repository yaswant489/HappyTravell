package happytravell.controller;

import happytravell.dao.BookingDao;
import happytravell.dao.BusTicketsDao;
import happytravell.dao.VehiclesDao;
import happytravell.model.BookingData;
import happytravell.model.BusTicketsData;
import happytravell.model.VehiclesData;
import happytravell.popup.TicketsPopup;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerBusTicketsView;
import happytravell.view.TravellerProfileView;
import happytravell.view.TravellerRouteView;
import happytravell.view.TravellerVehiclesDetailsView;
import happytravell.view.TravellerdashboardView;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class TravellerBusTicketController {
    private TravellerBusTicketsView busTicketsView;
    private int currentTravellerId;
    private BusTicketsDao busTicketsDao;
    private VehiclesDao vehiclesDao; 
    private List<String> selectedSeats = new ArrayList<>();
    private String currentVehicleNumber;
    private JLabel vehicleImageLabel; 

    
    public TravellerBusTicketController(TravellerBusTicketsView busTicketsView, int travellerId) {
        this.currentTravellerId = travellerId;
        this.busTicketsView = busTicketsView;
        this.busTicketsDao = new BusTicketsDao();
        this.vehiclesDao = new VehiclesDao(); 
        this.vehicleImageLabel = busTicketsView.getBusImageLabel(); 

        // Initialize UI components
        initializeSeatSelection();
        populateBusNumbers(); 
        
        
        // Add listener for vehicle number selection
        busTicketsView.getBusNumberComboBox().addItemListener(new VehicleSelectionListener());
        
        this.busTicketsView.DashboardNavigation(new DashboardNav(busTicketsView.getDashboardLabel()));
        this.busTicketsView.BookingNavigation(new BookingNav(busTicketsView.getBusDetailsLabel()));
        this.busTicketsView.RouteNavigation(new RouteNav(busTicketsView.getRouteDetailsLabel()));
        this.busTicketsView.VehiclesDetailsNavigation(new VehiclesDetailsNav(busTicketsView.getVehiclesDetailsLabel()));
        this.busTicketsView.ProfileNavigation(new ProfileNav(busTicketsView.getProfileLabel()));
        this.busTicketsView.LogOutNavigation(new LogOutNav(busTicketsView.getLogOutLabel()));
        
        // Add action listener for Buy Now button
        this.busTicketsView.BuyNowButtonListener(new BuyNowListener());
        
        this.busTicketsView.MyTicketsButtonListener(new MyTicketsListener());
    }
    
//    public void MyTicketsButtonListener(ActionListener listener) {
//    busTicketsView.getMyTicketsButton().addActionListener(listener);
//    }

    
   class MyTicketsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println("Current Traveller ID: " + currentTravellerId); // Debug line
            
            List<BusTicketsData> tickets = busTicketsDao.getTicketsByTravellerId(currentTravellerId);
            
            System.out.println("Number of tickets found: " + tickets.size()); // Debug line
            for (BusTicketsData ticket : tickets) {
                System.out.println("Ticket: " + ticket.getName() + " - " + ticket.getBusNumber());
            }
            
            TicketsPopup.showTicketDetails(tickets);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(busTicketsView, 
                "Error loading tickets: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}

    
//    public void handleMyTicketsButtonClick() {
//    try {
//        // Option 1: Show all tickets (admin view)
//        BusTicketsDao ticketsDao = new BusTicketsDao();
//        List<BusTicketsData> allTickets = ticketsDao.getAllBusTickets();
//        TicketsPopup.showTicketsPopup(allTickets);
//        
//        // Option 2: Show tickets for specific traveller (if you have traveller context)
//        // TicketsPopup.showTicketsPopupForTraveller(travellerId);
//        
//    } catch (Exception e) {
//        JOptionPane.showMessageDialog(
//            null,
//            "Error loading tickets: " + e.getMessage(),
//            "Error",
//            JOptionPane.ERROR_MESSAGE
//        );
//        e.printStackTrace();
//    }
//}

    
    
    
    
    
    private void populateBusNumbers() {
        try {
            List<VehiclesData> buses = vehiclesDao.getVehiclesByType("Bus");
            JComboBox<String> comboBox = busTicketsView.getBusNumberComboBox();
            comboBox.removeAllItems();
            comboBox.addItem("Select Bus"); // Default option
            
            for (VehiclesData bus : buses) {
                comboBox.addItem(bus.getVehicleNumber());
            }
        } catch (Exception e) {
            System.err.println("Error populating bus numbers: " + e.getMessage());
            JOptionPane.showMessageDialog(busTicketsView, 
                "Error loading bus data. Please try again later.", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    
    class VehicleSelectionListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedBusNumber = (String) e.getItem();
                if (selectedBusNumber != null && !selectedBusNumber.equals("Select Bus")) {
                    currentVehicleNumber = selectedBusNumber;
                    displayBusImage(selectedBusNumber);
                    updateSeatAvailability();
                } else {
                    clearBusImage();
                    currentVehicleNumber = null;
                }
            }
        }
    }
    
    
    private void displayBusImage(String busNumber) {
        try {
            VehiclesData vehicle = vehiclesDao.getVehicleByNumber(busNumber);
            if (vehicle != null && vehicle.getVehicleImage() != null) {
                byte[] imageData = vehicle.getVehicleImage();
                ImageIcon icon = createScaledIcon(imageData, 
                    vehicleImageLabel.getWidth(), 
                    vehicleImageLabel.getHeight());
                vehicleImageLabel.setIcon(icon);
                vehicleImageLabel.setText("");
                // Set tooltip with bus information
                String tooltip = String.format("<html><b>%s</b><br/>Type: %s<br/>Seats: %d<br/>Color: %s<br/>Agency: %s</html>",
                    vehicle.getVehicleName(),
                    vehicle.getVehicleType(),
                    vehicle.getNumberOfSeats(),
                    vehicle.getVehicleColor(),
                    vehicle.getTravelAgency());
                vehicleImageLabel.setToolTipText(tooltip);
            } else {
                setDefaultBusImage();
            }
        } catch (Exception e) {
            System.err.println("Error displaying bus image: " + e.getMessage());
            setDefaultBusImage();
        }
    }

    private ImageIcon createScaledIcon(byte[] imageData, int width, int height) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        Image image = ImageIO.read(bis);
        if (width <= 0) width = 240;
        if (height <= 0) height = 140;
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    
    private void clearBusImage() {
        vehicleImageLabel.setIcon(null);
        vehicleImageLabel.setText("Select a bus to view image");
        vehicleImageLabel.setToolTipText(null);
    }
    
    private void setDefaultBusImage() {
        vehicleImageLabel.setIcon(null);
        vehicleImageLabel.setText("No Image Available");
        vehicleImageLabel.setToolTipText("No bus image available");
    }
    
    
    private void updateSeatAvailability() {
    if (currentVehicleNumber == null) return;
    
    String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    List<String> bookedSeats = busTicketsDao.getBookedSeatsForVehicle(currentVehicleNumber, currentDate);
    
    updateSeatLabels(bookedSeats);
    updateSeatCountDisplay(currentDate);
}

    private void updateSeatLabels(List<String> bookedSeats) {
        updateSeatLabel(busTicketsView.getSeatA1(), "A1", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA2(), "A2", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA3(), "A3", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA4(), "A4", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA5(), "A5", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA6(), "A6", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA7(), "A7", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA8(), "A8", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA9(), "A9", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA10(), "A10", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA11(), "A11", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA12(), "A12", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA13(), "A13", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatA14(), "A14", bookedSeats);    
    
        
        updateSeatLabel(busTicketsView.getSeatB1(), "B1", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB2(), "B2", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB3(), "B3", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB4(), "B4", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB5(), "B5", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB6(), "B6", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB7(), "B7", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB8(), "B8", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB9(), "B9", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB10(), "B10", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB11(), "B11", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB12(), "B12", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB13(), "B13", bookedSeats);
        updateSeatLabel(busTicketsView.getSeatB14(), "B14", bookedSeats);
    }

    private void updateSeatLabel(JLabel seatLabel, String seatNumber, List<String> bookedSeats) {
        if (bookedSeats.contains(seatNumber)) {
            seatLabel.setForeground(Color.RED); 
            seatLabel.setEnabled(false);
            seatLabel.setCursor(Cursor.getDefaultCursor());
        } else {
            seatLabel.setForeground(Color.GREEN); 
            seatLabel.setEnabled(true);
            seatLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            seatLabel.removeMouseListener(getExistingMouseListener(seatLabel));
            seatLabel.addMouseListener(new SeatSelectionListener(seatLabel, seatNumber));
        }
    }
    

    private MouseListener getExistingMouseListener(JLabel label) {
        for (MouseListener listener : label.getMouseListeners()) {
            if (listener instanceof SeatSelectionListener) {
                return listener;
            }
        }
        return null;
    }

    private void initializeSeatSelection() {
        updateSeatAvailability();
    }

    private void updateSeatCountDisplay(String date) {
    if (currentVehicleNumber == null) return;
    
    
    List<String> availableSeats = busTicketsDao.getAvailableSeatsForVehicle(currentVehicleNumber, date);
    List<String> bookedSeats = busTicketsDao.getBookedSeatsForVehicle(currentVehicleNumber, date);
    busTicketsView.getAvailableTextField().setText(String.valueOf(availableSeats.size()));
    busTicketsView.getBookedSeatsTextField().setText(String.valueOf(bookedSeats.size()));
    busTicketsView.getSelectedSeatsTextField().setText(String.valueOf(selectedSeats.size()));
}

    class SeatSelectionListener implements MouseListener {
        private final JLabel seatLabel;
        private final String seatNumber;

        public SeatSelectionListener(JLabel seatLabel, String seatNumber) {
            this.seatLabel = seatLabel;
            this.seatNumber = seatNumber;
        }
       
        @Override
        public void mouseClicked(MouseEvent e) {
            if (selectedSeats.contains(seatNumber)) {
                // Deselect seat
                selectedSeats.remove(seatNumber);
                seatLabel.setForeground(Color.GREEN); // Back to available color
            } else {
                // Select seat
                selectedSeats.add(seatNumber);
                seatLabel.setForeground(Color.BLUE); // Blue for selected
            }
            
             // Update selected seats count
            String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            updateSeatCountDisplay(currentDate);
        }

        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }

    class BuyNowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(busTicketsView, 
                    "Please select at least one seat", 
                    "No Seat Selected", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (currentVehicleNumber == null || currentVehicleNumber.equals("Select Bus")) {
                JOptionPane.showMessageDialog(busTicketsView, 
                    "Please select a bus", 
                    "No Bus Selected", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                VehiclesData vehicle = vehiclesDao.getVehicleByNumber(currentVehicleNumber);
                if (vehicle == null) {
                    JOptionPane.showMessageDialog(busTicketsView, 
                        "Selected bus not found in database", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Validate number of seats
                if (selectedSeats.size() > vehicle.getNumberOfSeats()) {
                    JOptionPane.showMessageDialog(busTicketsView, 
                        "Cannot select more seats than available in the bus", 
                        "Invalid Selection", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                
                // Get current date and time
                String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Timestamp now = Timestamp.valueOf(LocalDateTime.now());

                // Convert spinner dates to Timestamp
                Date departureDate = (Date) busTicketsView.getDepartureDateTimeSpinner().getValue();
                Timestamp departureTimestamp = new Timestamp(departureDate.getTime());
                
                Date returnDate = (Date) busTicketsView.getReturnDateTimeSpinner().getValue();
                Timestamp returnTimestamp = returnDate != null ? new Timestamp(returnDate.getTime()) : null;
           
                // Get traveler information
                String name = busTicketsView.getNameTextField().getText();
                String phoneNumber = busTicketsView.getPhoneNumberTextField().getText();
                String pickupAddress = busTicketsView.getPickupAddressTextField().getText();
                String dropAddress = busTicketsView.getDropAddressTextField().getText();

                boolean allTicketsSuccess = true;
                List<String> failedSeats = new ArrayList<>();
                
                // Create and save tickets for each selected seat
                for (String seatNumber : selectedSeats) {
                    BusTicketsData ticket = new BusTicketsData(
                        name,
                        phoneNumber,
                        currentVehicleNumber,
                        pickupAddress,
                        dropAddress,
                        departureTimestamp,
                        returnTimestamp,
                        currentDate,
                        seatNumber
                    );
                    ticket.setTravellerId(currentTravellerId); 
                    
                    boolean success = busTicketsDao.addBusTicket(ticket, currentTravellerId);
                    
                    if (success) {
                        busTicketsDao.updateSeatStatusForVehicle(currentVehicleNumber, seatNumber, "BOOKED");
                    } else {
                        allTicketsSuccess = false;
                        failedSeats.add(seatNumber);
                    }
                }
                
                if (allTicketsSuccess) {
                    JOptionPane.showMessageDialog(busTicketsView, 
                        "Tickets purchased successfully for seats: " + String.join(", ", selectedSeats), 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    selectedSeats.clear();
                    updateSeatAvailability();
                } else {
                    String message = "Failed to purchase tickets for seats: " + String.join(", ", failedSeats);
                    if (selectedSeats.size() > failedSeats.size()) {
                        List<String> successfulSeats = new ArrayList<>(selectedSeats);
                        successfulSeats.removeAll(failedSeats);
                        message += "\nSuccessfully purchased tickets for seats: " + String.join(", ", successfulSeats);
                    }
                    
                    JOptionPane.showMessageDialog(busTicketsView, 
                        message, 
                        "Partial Success", 
                        JOptionPane.WARNING_MESSAGE);
                    selectedSeats.clear();
                    updateSeatAvailability();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(busTicketsView, 
                    "Error processing ticket purchase: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void open() {
        this.busTicketsView.setVisible(true);
    }

    public void close() {
        this.busTicketsView.dispose();
    }

    // Navigation 
    class DashboardNav implements MouseListener {
        private final JLabel dashboardLabel;

        public DashboardNav(JLabel label) {
            this.dashboardLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerdashboardView travellerdashboardView = new TravellerdashboardView();
            TravellerDashboardController TravellerDashboard = new TravellerDashboardController(travellerdashboardView, currentTravellerId);
            TravellerDashboard.open();
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

    class BookingNav implements MouseListener {
        private final JLabel bookingLabel;

        public BookingNav(JLabel label) {
            this.bookingLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBookingView travellerBookingView = new TravellerBookingView();
            TravellerBookingController TravellerBooking = new TravellerBookingController(travellerBookingView, currentTravellerId);
            TravellerBooking.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            bookingLabel.setForeground(Color.WHITE);
            bookingLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            bookingLabel.setForeground(Color.BLACK);
            bookingLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    class RouteNav implements MouseListener {
        private final JLabel routeLabel;

        public RouteNav(JLabel label) {
            this.routeLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerRouteView travellerRouteView = new TravellerRouteView();
            TravellerRouteController TravellerRoute = new TravellerRouteController(travellerRouteView, currentTravellerId);
            TravellerRoute.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            routeLabel.setForeground(Color.WHITE);
            routeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            routeLabel.setForeground(Color.BLACK);
            routeLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    class VehiclesDetailsNav implements MouseListener {
        private final JLabel vehiclesDetailsLabel;

        public VehiclesDetailsNav(JLabel label) {
            this.vehiclesDetailsLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerVehiclesDetailsView travellerVehiclesDetailsView = new TravellerVehiclesDetailsView();
            TravellerVehiclesDetailsController TravellerVehiclesDetails = new TravellerVehiclesDetailsController(travellerVehiclesDetailsView, currentTravellerId);
            TravellerVehiclesDetails.open();
            close();
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

    class ProfileNav implements MouseListener {
        private final JLabel profileLabel;

        public ProfileNav(JLabel label) {
            this.profileLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerProfileView travellerProfileView = new TravellerProfileView();
            TravellerProfileController TravellerProfile = new TravellerProfileController(travellerProfileView, currentTravellerId);
            TravellerProfile.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            profileLabel.setForeground(Color.WHITE);
            profileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            profileLabel.setForeground(Color.BLACK);
            profileLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    class LogOutNav implements MouseListener {
        private final JLabel logOutLabel;

        public LogOutNav(JLabel label) {
            this.logOutLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                busTicketsView.dispose();

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
}