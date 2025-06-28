package happytravell.popup;

import happytravell.model.BusTicketsData;
import happytravell.dao.BusTicketsDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TicketsPopup extends JDialog {
    private List<BusTicketsData> tickets;
    private List<BusTicketsData> ticketsToCancel;
    private BusTicketsDao ticketsDao;
    private JButton saveButton;
    private JButton closeButton;
    private JPanel mainPanel;
    private int travellerId;

    public TicketsPopup(List<BusTicketsData> tickets) {
        this.tickets = tickets;
        this.ticketsToCancel = new ArrayList<>();
        this.ticketsDao = new BusTicketsDao();
        initializeUI();
    }

    public TicketsPopup(int travellerId) {
        this.travellerId = travellerId;
        this.ticketsDao = new BusTicketsDao();
        this.ticketsToCancel = new ArrayList<>();
        this.tickets = ticketsDao.getTicketsByTravellerId(travellerId);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("My Bus Tickets");
        setSize(900, 650);
        setModal(true);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(248, 219, 164));

        // Main panel with scroll
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(248, 219, 164));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getViewport().setBackground(new Color(248, 219, 164));
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        refreshTicketDisplay();
    }

    private void refreshTicketDisplay() {
        mainPanel.removeAll();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        if (tickets.isEmpty()) {
            JLabel noTicketsLabel = new JLabel("You don't have any tickets yet.");
            noTicketsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            noTicketsLabel.setForeground(new Color(120, 120, 120));
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(noTicketsLabel, gbc);
        } else {
            // Add title
            gbc.gridwidth = 7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JLabel titleLabel = new JLabel("My Bus Tickets", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            titleLabel.setForeground(new Color(171, 106, 32));
            mainPanel.add(titleLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            
            // Add header
            addHeaderLabels(mainPanel, gbc);

            // Add ticket details
            for (BusTicketsData ticket : tickets) {
                gbc.gridy++;
                addTicketDetails(mainPanel, gbc, ticket);
            }

            // Add buttons panel at the bottom
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 7;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.insets = new Insets(30, 10, 10, 10);
            JPanel buttonsPanel = createButtonsPanel();
            mainPanel.add(buttonsPanel, gbc);
        }
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void addHeaderLabels(JPanel panel, GridBagConstraints gbc) {
        Font headerFont = new Font("Segoe UI", Font.BOLD, 14);
        Color headerColor = new Color(171, 106, 32);

        gbc.gridx = 0;
        gbc.insets = new Insets(15, 10, 5, 10);

        JLabel[] headers = {
            new JLabel("Passenger Name"),
            new JLabel("Bus Number"),
            new JLabel("Seat"),
            new JLabel("Pickup Location"),
            new JLabel("Drop Location"),
            new JLabel("Departure"),
            new JLabel("Action")
        };

        for (JLabel header : headers) {
            header.setFont(headerFont);
            header.setForeground(headerColor);
            panel.add(header, gbc);
            gbc.gridx++;
        }
    }

    private void addTicketDetails(JPanel panel, GridBagConstraints gbc, BusTicketsData ticket) {
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 10, 8, 10);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        Font dataFont = new Font("Segoe UI", Font.PLAIN, 12);

        // Passenger Name
        JLabel nameLabel = new JLabel(ticket.getName());
        nameLabel.setFont(dataFont);
        panel.add(nameLabel, gbc);

        // Bus Number
        gbc.gridx++;
        JLabel busNumberLabel = new JLabel(ticket.getBusNumber());
        busNumberLabel.setFont(dataFont);
        busNumberLabel.setForeground(new Color(51, 51, 51));
        panel.add(busNumberLabel, gbc);

        // Seat Number
        gbc.gridx++;
        JLabel seatLabel = new JLabel(ticket.getSeatNumber());
        seatLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        seatLabel.setForeground(new Color(171, 106, 32));
        panel.add(seatLabel, gbc);

        // Pickup Address
        gbc.gridx++;
        JLabel pickupLabel = new JLabel(truncateText(ticket.getPickupAddress(), 20));
        pickupLabel.setFont(dataFont);
        pickupLabel.setToolTipText(ticket.getPickupAddress()); // Full text on hover
        panel.add(pickupLabel, gbc);

        // Drop Address
        gbc.gridx++;
        JLabel dropLabel = new JLabel(truncateText(ticket.getDropAddress(), 20));
        dropLabel.setFont(dataFont);
        dropLabel.setToolTipText(ticket.getDropAddress()); // Full text on hover
        panel.add(dropLabel, gbc);

        // Departure Date and Time
        gbc.gridx++;
        JLabel dateLabel = new JLabel(dateFormat.format(ticket.getDepartureDateTime()));
        dateLabel.setFont(dataFont);
        panel.add(dateLabel, gbc);

        // Action Buttons
        gbc.gridx++;
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(new Color(248, 219, 164));
        
        JButton cancelBtn = new JButton("Cancel Ticket");
        cancelBtn.setBackground(new Color(220, 53, 69));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 10));
        cancelBtn.setFocusPainted(false);
        cancelBtn.addActionListener(e -> handleCancelTicket(ticket));
        
        JButton viewBtn = new JButton("View Details");
        viewBtn.setBackground(new Color(23, 162, 184));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setFont(new Font("Segoe UI", Font.BOLD, 10));
        viewBtn.setFocusPainted(false);
        viewBtn.addActionListener(e -> showTicketDetails(ticket));
        
        actionPanel.add(viewBtn);
        actionPanel.add(cancelBtn);
        panel.add(actionPanel, gbc);
    }

    private String truncateText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 219, 164));

        saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(40, 167, 69));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> handleSave());
        saveButton.setEnabled(!ticketsToCancel.isEmpty());

        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(108, 117, 125));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(171, 106, 32));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> refreshTickets());

        panel.add(refreshButton);
        panel.add(saveButton);
        panel.add(closeButton);

        return panel;
    }

    private void handleCancelTicket(BusTicketsData ticket) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "<html><body style='width: 300px'>" +
            "<h3>Confirm Ticket Cancellation</h3>" +
            "<p><b>Passenger:</b> " + ticket.getName() + "</p>" +
            "<p><b>Bus:</b> " + ticket.getBusNumber() + "</p>" +
            "<p><b>Seat:</b> " + ticket.getSeatNumber() + "</p>" +
            "<p><b>Departure:</b> " + new SimpleDateFormat("MMM dd, yyyy HH:mm").format(ticket.getDepartureDateTime()) + "</p>" +
            "<br><p>Are you sure you want to cancel this ticket?</p>" +
            "</body></html>",
            "Confirm Cancellation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Add to cancellation list
            if (!ticketsToCancel.contains(ticket)) {
                ticketsToCancel.add(ticket);
                saveButton.setEnabled(true);
                
                JOptionPane.showMessageDialog(
                    this,
                    "<html><body style='width: 250px'>" +
                    "<p>Ticket for seat <b>" + ticket.getSeatNumber() + "</b> has been marked for cancellation.</p>" +
                    "<p>Click 'Save Changes' to confirm the cancellation.</p>" +
                    "</body></html>",
                    "Ticket Marked for Cancellation",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void showTicketDetails(BusTicketsData ticket) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy 'at' HH:mm");
        
        String details = "<html><body style='width: 350px; font-family: Arial, sans-serif;'>" +
            "<h2 style='color: #AB6A20; text-align: center;'>Ticket Details</h2>" +
            "<hr>" +
            "<table style='width: 100%; border-spacing: 10px;'>" +
            "<tr><td style='font-weight: bold; color: #333;'>Passenger Name:</td><td>" + ticket.getName() + "</td></tr>" +
            "<tr><td style='font-weight: bold; color: #333;'>Phone Number:</td><td>" + ticket.getPhoneNumber() + "</td></tr>" +
            "<tr><td style='font-weight: bold; color: #333;'>Bus Number:</td><td>" + ticket.getBusNumber() + "</td></tr>" +
            "<tr><td style='font-weight: bold; color: #333;'>Seat Number:</td><td style='color: #AB6A20; font-weight: bold;'>" + ticket.getSeatNumber() + "</td></tr>" +
            "<tr><td style='font-weight: bold; color: #333;'>Pickup Location:</td><td>" + ticket.getPickupAddress() + "</td></tr>" +
            "<tr><td style='font-weight: bold; color: #333;'>Drop Location:</td><td>" + ticket.getDropAddress() + "</td></tr>" +
            "<tr><td style='font-weight: bold; color: #333;'>Departure:</td><td>" + dateFormat.format(ticket.getDepartureDateTime()) + "</td></tr>" +
            "<tr><td style='font-weight: bold; color: #333;'>Travel Date:</td><td>" + ticket.getTravelDate() + "</td></tr>";

        if (ticket.getReturnDateTime() != null) {
            details += "<tr><td style='font-weight: bold; color: #333;'>Return:</td><td>" + dateFormat.format(ticket.getReturnDateTime()) + "</td></tr>";
        }
        
        details += "</table>" +
            "<hr>" +
            "<p style='text-align: center; color: #666; font-size: 12px;'>Please arrive at the pickup location 15 minutes before departure.</p>" +
            "</body></html>";

        JOptionPane.showMessageDialog(
            this,
            details,
            "Ticket Information",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void handleSave() {
        if (ticketsToCancel.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "No changes to save.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to cancel " + ticketsToCancel.size() + " ticket(s)?",
            "Confirm Changes",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean allCancelled = true;
            StringBuilder results = new StringBuilder();
            
            for (BusTicketsData ticket : ticketsToCancel) {
                boolean cancelled = ticketsDao.deleteTicket(ticket.getId());
                if (cancelled) {
                    results.append("✓ Seat ").append(ticket.getSeatNumber()).append(" cancelled\n");
                    tickets.remove(ticket); // Remove from local list
                } else {
                    results.append("✗ Failed to cancel seat ").append(ticket.getSeatNumber()).append("\n");
                    allCancelled = false;
                }
            }

            if (allCancelled) {
                JOptionPane.showMessageDialog(
                    this,
                    "All selected tickets have been cancelled successfully!\n\n" + results.toString(),
                    "Cancellation Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Some tickets could not be cancelled:\n\n" + results.toString(),
                    "Partial Success",
                    JOptionPane.WARNING_MESSAGE
                );
            }

            ticketsToCancel.clear();
            saveButton.setEnabled(false);
            refreshTicketDisplay();
        }
    }

    private void refreshTickets() {
        // Reload tickets from database
        if (travellerId > 0) {
            tickets = ticketsDao.getTicketsByTravellerId(travellerId);
        } else {
            tickets = ticketsDao.getAllBusTickets();
        }
        ticketsToCancel.clear();
        saveButton.setEnabled(false);
        refreshTicketDisplay();
        
        JOptionPane.showMessageDialog(
            this,
            "Tickets refreshed successfully!",
            "Refresh Complete",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Static methods for easy popup creation
    public static void showTicketsPopup(List<BusTicketsData> tickets) {
        TicketsPopup popup = new TicketsPopup(tickets);
        popup.setVisible(true);
    }

    public static void showTicketsPopupForTraveller(int travellerId) {
        TicketsPopup popup = new TicketsPopup(travellerId);
        popup.setVisible(true);
    }
}