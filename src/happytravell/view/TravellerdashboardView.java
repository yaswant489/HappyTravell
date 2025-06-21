/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Acer
 */
public class TravellerdashboardView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public TravellerdashboardView() {
        initComponents();
        scaleImage1();
        scaleImage2();
        scaleImage3();
        scaleImage4();
        scaleImage5();
        scaleImage6();
        scaleImage7();
        scaleImage8();
        scaleImage9();
    }
    
    public void scaleImage1(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/dashboard.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        dashboardIcon.setIcon(scaledIcon);
    }
    public void scaleImage2(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/booking.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bookingIcon.setIcon(scaledIcon);
    }
    public void scaleImage3(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/directions.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        routeIcon.setIcon(scaledIcon);
    }
    public void scaleImage4(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/tickets.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        busTicketIcon.setIcon(scaledIcon);
    }
    public void scaleImage5(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/front-of-bus.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        vehiclesIcon.setIcon(scaledIcon);
    }
    public void scaleImage6(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/user.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        profileIcon.setIcon(scaledIcon);
    }
    public void scaleImage7(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/logout.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoutIcon.setIcon(scaledIcon);
    }
    public void scaleImage8(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/happy.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoIcon.setIcon(scaledIcon);
    }
    public void scaleImage9(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/ringing.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        notificationIcon.setIcon(scaledIcon);
    }

    public void setContent(JPanel panel) {
        TravelerDetailsPanel.removeAll();
        TravelerDetailsPanel.add(panel, java.awt.BorderLayout.CENTER);
        TravelerDetailsPanel.revalidate();
        TravelerDetailsPanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        TravelerDetailsPanel = new javax.swing.JPanel();
        placePanel = new javax.swing.JPanel();
        // ... (all original components restored here)
        logOutLabel = new javax.swing.JLabel();

        // ... (original layout code restored here)
        
        pack();
    }// </editor-fold>                        

    public void addLogoutListener(java.awt.event.ActionListener listener) {
        logoutIcon.addMouseListener(createMouseListener(listener));
        logOutLabel.addMouseListener(createMouseListener(listener));
    }

    public void addBookingListener(java.awt.event.ActionListener listener) {
        bookingIcon.addMouseListener(createMouseListener(listener));
        bookingLabel.addMouseListener(createMouseListener(listener));
    }

    public void addVehiclesListener(java.awt.event.ActionListener listener) {
        vehiclesIcon.addMouseListener(createMouseListener(listener));
        vehiclesDetailsLabel.addMouseListener(createMouseListener(listener));
    }

    public void addDashboardListener(java.awt.event.ActionListener listener) {
        dashboardIcon.addMouseListener(createMouseListener(listener));
        dashboardLabel.addMouseListener(createMouseListener(listener));
    }

    public void addRouteListener(java.awt.event.ActionListener listener) {
        routeIcon.addMouseListener(createMouseListener(listener));
        routeLabel.addMouseListener(createMouseListener(listener));
    }

    public void addBusTicketsListener(java.awt.event.ActionListener listener) {
        busTicketIcon.addMouseListener(createMouseListener(listener));
        busTicketsLabel.addMouseListener(createMouseListener(listener));
    }

    public void addProfileListener(java.awt.event.ActionListener listener) {
        profileIcon.addMouseListener(createMouseListener(listener));
        profileLabel.addMouseListener(createMouseListener(listener));
    }
    
    private java.awt.event.MouseAdapter createMouseListener(java.awt.event.ActionListener listener) {
        return new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listener.actionPerformed(new java.awt.event.ActionEvent(evt.getSource(), java.awt.event.ActionEvent.ACTION_PERFORMED, null));
            }
        };
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TravellerdashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel kathmanduPanelButton;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JPanel mustangPanelButton;
    private javax.swing.JLabel notificationIcon;
    private javax.swing.JButton pendingButton;
    private javax.swing.JPanel placePanel;
    private javax.swing.JButton places;
    private javax.swing.JPanel pokharaPanelButton;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel routeLabel;
    private javax.swing.JButton satisfactionButton;
    private javax.swing.JButton travelsDoneButton;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables
}
