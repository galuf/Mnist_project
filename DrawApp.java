import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class DrawApp extends JPanel {
    BufferedImage image;
    Graphics2D g2d;
    
    public DrawApp(){
        super();
        init();
    }
    
    private void init(){
        setBackground(Color.BLACK);
        
        image = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
        g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(10));
        
        MouseAdapter mouseHandler = new MouseAdapter() {
            private Point curPoint = new Point();
            
            @Override
            public void mousePressed(MouseEvent e) {
                curPoint.setLocation(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                g2d.drawLine(curPoint.x, curPoint.y, e.getX(), e.getY());
                curPoint.setLocation(e.getPoint());
                repaint();
            }
            
            
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        
    }
    
    public void ventana(){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("DrawApp");
            frame.setMinimumSize(new Dimension(640,480));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new DrawApp());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
