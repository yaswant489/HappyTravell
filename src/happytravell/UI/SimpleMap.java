package happytravell.UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Simple Directional Map Component for HappyTravell
 * @author Acer
 */
public class SimpleMap extends JPanel {
    private ArrayList<RoutePoint> routePoints;
    private RoutePoint startPoint;
    private RoutePoint endPoint;
    private boolean showRoute = false;
    private Point lastMousePoint;
    private boolean isDragging = false;
    private double zoomLevel = 1.0;
    private Point2D.Double centerPoint;
    
    // Simple color scheme
    private Color backgroundColor = new Color(245, 245, 245);
    private Color roadColor = new Color(100, 100, 100);
    private Color routeColor = new Color(0, 150, 255);
    private Color startColor = new Color(0, 200, 0);
    private Color endColor = new Color(255, 0, 0);
    
    public SimpleMap() {
        this.routePoints = new ArrayList<>();
        this.centerPoint = new Point2D.Double(0, 0);
        
        // Initialize with basic route points
        initializeRoutePoints();
        
        // Add mouse listeners
        addMouseListeners();
        
        setPreferredSize(new Dimension(600, 400));
        setBackground(backgroundColor);
    }
    
    private void initializeRoutePoints() {
        // Add major cities as route points
        routePoints.add(new RoutePoint("Kathmandu", 100, 150, "Capital"));
        routePoints.add(new RoutePoint("Pokhara", 300, 100, "Tourist City"));
        routePoints.add(new RoutePoint("Chitwan", 200, 250, "National Park"));
        routePoints.add(new RoutePoint("Lumbini", 400, 200, "Buddha's Birthplace"));
        routePoints.add(new RoutePoint("Mustang", 150, 50, "Upper Mustang"));
        routePoints.add(new RoutePoint("Everest Base Camp", 50, 50, "Mountain"));
        routePoints.add(new RoutePoint("Bhaktapur", 120, 160, "Ancient City"));
        routePoints.add(new RoutePoint("Patan", 110, 140, "Lalitpur"));
    }
    
    private void addMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMousePoint = e.getPoint();
                isDragging = true;
                checkPointClick(e.getPoint());
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && lastMousePoint != null) {
                    int dx = e.getX() - lastMousePoint.x;
                    int dy = e.getY() - lastMousePoint.y;
                    
                    centerPoint.x -= dx / zoomLevel;
                    centerPoint.y -= dy / zoomLevel;
                    
                    lastMousePoint = e.getPoint();
                    repaint();
                }
            }
        });
        
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double zoomFactor = e.getWheelRotation() < 0 ? 1.1 : 0.9;
                zoomLevel *= zoomFactor;
                zoomLevel = Math.max(0.5, Math.min(zoomLevel, 2.0));
                repaint();
            }
        });
    }
    
    private void checkPointClick(Point clickPoint) {
        for (RoutePoint point : routePoints) {
            Point2D.Double screenPos = worldToScreen(point.x, point.y);
            double distance = clickPoint.distance(screenPos.x, screenPos.y);
            
            if (distance < 20) {
                if (startPoint == null) {
                    startPoint = point;
                    showMessage("Start point set to: " + point.name);
                } else if (endPoint == null && point != startPoint) {
                    endPoint = point;
                    showRoute = true;
                    showMessage("Route from " + startPoint.name + " to " + endPoint.name);
                } else {
                    // Reset route
                    startPoint = point;
                    endPoint = null;
                    showRoute = false;
                    showMessage("New start point: " + point.name);
                }
                repaint();
                break;
            }
        }
    }
    
    private void showMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, message, "Route Information", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    private Point2D.Double worldToScreen(double x, double y) {
        double screenX = (x - centerPoint.x) * zoomLevel + getWidth() / 2.0;
        double screenY = (y - centerPoint.y) * zoomLevel + getHeight() / 2.0;
        return new Point2D.Double(screenX, screenY);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw roads
        drawRoads(g2d);
        
        // Draw route if selected
        if (showRoute && startPoint != null && endPoint != null) {
            drawRoute(g2d);
        }
        
        // Draw route points
        drawRoutePoints(g2d);
        
        // Draw legend
        drawLegend(g2d);
        
        // Draw border
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
    
    private void drawRoads(Graphics2D g2d) {
        g2d.setColor(roadColor);
        g2d.setStroke(new BasicStroke(3));
        
        // Main highways - using world coordinates
        Point2D.Double p1, p2;
        
        // East-West Highway
        p1 = worldToScreen(0, 150);
        p2 = worldToScreen(500, 150);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        // North-South connections
        p1 = worldToScreen(100, 0);
        p2 = worldToScreen(100, 300);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        p1 = worldToScreen(300, 0);
        p2 = worldToScreen(300, 300);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        p1 = worldToScreen(200, 0);
        p2 = worldToScreen(200, 300);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        // Secondary roads
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(150, 150, 150));
        
        p1 = worldToScreen(50, 0);
        p2 = worldToScreen(50, 300);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        p1 = worldToScreen(250, 0);
        p2 = worldToScreen(250, 300);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        p1 = worldToScreen(350, 0);
        p2 = worldToScreen(350, 300);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        
        p1 = worldToScreen(450, 0);
        p2 = worldToScreen(450, 300);
        g2d.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
    }
    
    private void drawRoute(Graphics2D g2d) {
        if (startPoint != null && endPoint != null) {
            Point2D.Double start = worldToScreen(startPoint.x, startPoint.y);
            Point2D.Double end = worldToScreen(endPoint.x, endPoint.y);
            
            // Draw route line
            g2d.setColor(routeColor);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawLine((int)start.x, (int)start.y, (int)end.x, (int)end.y);
            
            // Draw direction arrow
            drawArrow(g2d, start, end);
            
            // Calculate and display distance
            double distance = calculateDistance(startPoint, endPoint);
            String distanceText = String.format("%.1f km", distance);
            
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(distanceText);
            int midX = (int)((start.x + end.x) / 2);
            int midY = (int)((start.y + end.y) / 2);
            g2d.drawString(distanceText, midX - textWidth/2, midY - 10);
        }
    }
    
    private void drawArrow(Graphics2D g2d, Point2D.Double start, Point2D.Double end) {
        // Calculate arrow direction
        double angle = Math.atan2(end.y - start.y, end.x - start.x);
        double arrowLength = 15;
        double arrowAngle = Math.PI / 6;
        
        // Arrow position (80% along the route)
        double arrowX = start.x + (end.x - start.x) * 0.8;
        double arrowY = start.y + (end.y - start.y) * 0.8;
        
        // Draw arrow
        g2d.setColor(routeColor);
        g2d.setStroke(new BasicStroke(2));
        
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        
        xPoints[0] = (int)(arrowX + arrowLength * Math.cos(angle));
        yPoints[0] = (int)(arrowY + arrowLength * Math.sin(angle));
        xPoints[1] = (int)(arrowX + arrowLength * Math.cos(angle - arrowAngle));
        yPoints[1] = (int)(arrowY + arrowLength * Math.sin(angle - arrowAngle));
        xPoints[2] = (int)(arrowX + arrowLength * Math.cos(angle + arrowAngle));
        yPoints[2] = (int)(arrowY + arrowLength * Math.sin(angle + arrowAngle));
        
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
    
    private void drawRoutePoints(Graphics2D g2d) {
        for (RoutePoint point : routePoints) {
            Point2D.Double screenPos = worldToScreen(point.x, point.y);
            
            // Check if point is visible
            if (screenPos.x >= -30 && screenPos.x <= getWidth() + 30 &&
                screenPos.y >= -30 && screenPos.y <= getHeight() + 30) {
                
                // Determine color based on selection
                Color pointColor;
                if (point == startPoint) {
                    pointColor = startColor;
                } else if (point == endPoint) {
                    pointColor = endColor;
                } else {
                    pointColor = Color.BLUE;
                }
                
                // Draw point
                g2d.setColor(pointColor);
                g2d.fillOval((int)screenPos.x - 8, (int)screenPos.y - 8, 16, 16);
                
                // Draw border
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval((int)screenPos.x - 8, (int)screenPos.y - 8, 16, 16);
                
                // Draw name
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 10));
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(point.name);
                g2d.drawString(point.name, (int)screenPos.x - textWidth/2, (int)screenPos.y - 15);
            }
        }
    }
    
    private void drawLegend(Graphics2D g2d) {
        int legendX = 10;
        int legendY = 20;
        int itemHeight = 20;
        
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Start point
        g2d.setColor(startColor);
        g2d.fillOval(legendX, legendY, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Start", legendX + 20, legendY + 10);
        
        // End point
        g2d.setColor(endColor);
        g2d.fillOval(legendX, legendY + itemHeight, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawString("End", legendX + 20, legendY + itemHeight + 10);
        
        // Route
        g2d.setColor(routeColor);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(legendX, legendY + 2*itemHeight + 6, legendX + 15, legendY + 2*itemHeight + 6);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Route", legendX + 20, legendY + 2*itemHeight + 10);
    }
    
    private double calculateDistance(RoutePoint start, RoutePoint end) {
        // Simple distance calculation (in km)
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        return Math.sqrt(dx*dx + dy*dy) * 0.5; // Scale factor for realistic distances
    }
    
    // Inner class for route points
    public static class RoutePoint {
        public String name;
        public double x, y;
        public String description;
        
        public RoutePoint(String name, double x, double y, String description) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.description = description;
        }
    }
    
    // Public methods
    public void setRoute(String startName, String endName) {
        startPoint = null;
        endPoint = null;
        
        for (RoutePoint point : routePoints) {
            if (point.name.equals(startName)) {
                startPoint = point;
            }
            if (point.name.equals(endName)) {
                endPoint = point;
            }
        }
        
        showRoute = (startPoint != null && endPoint != null);
        repaint();
    }
    
    public void clearRoute() {
        startPoint = null;
        endPoint = null;
        showRoute = false;
        repaint();
    }
    
    public void addRoutePoint(String name, double x, double y, String description) {
        routePoints.add(new RoutePoint(name, x, y, description));
        repaint();
    }
}