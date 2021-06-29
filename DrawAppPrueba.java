/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 *
 * @author User
 */
public class DrawAppPrueba extends JPanel{
    ByteArrayOutputStream byteCon = new ByteArrayOutputStream();
    
    JButton boton1;
    
    File archivo = new File("");
    String ruta = archivo.getAbsolutePath().toString() + "/img/my_img.jpg";
    
    byte[] img;
    FileOutputStream salida;
    
    BufferedImage image;
    Graphics2D g2d;
    
    public DrawAppPrueba(){
        super();
        init();
        colocarBoton();
    }
    
    private void init(){
        
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED),"Panel de dibujo",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION));

        image = new BufferedImage(280,280,BufferedImage.TYPE_INT_RGB);
        g2d = image.createGraphics();
        g2d.translate(-50, -50);
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
        g.drawImage(image, 50, 50, null);
        
    }
    

    public static void main(String[] args) {
        
        DrawAppPrueba prueba1 = new DrawAppPrueba();
        prueba1.ventana();
        //colocarBoton();
    }
    
    public void ventana(){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setTitle("DrawAppPrueba");
            frame.setMinimumSize(new Dimension(800,600));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new DrawAppPrueba());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setLayout(null);
        });
    }
    
    private void colocarBoton(){
        boton1 = new JButton();
        boton1.setText("Guardar Imagen");
        boton1.setBounds(120, 450, 150, 30);
        
        this.add(boton1);
        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    ImageIO.write(image, "jpg", byteCon);
                } catch (IOException ex) {
                    //Logger.getLogger(DrawApp.class.getName()).log(Level.SEVERE, null, ex);
                }
                img = byteCon.toByteArray();

                System.out.println(ruta);
                //String ruta = archivo.getAbsolutePath().toString() + "/img/my_img.jpg";

                guardarArchivo(ruta, img);
                //reescalarArchivo(ruta);

                Color color = g2d.getColor();
                g2d.setColor(Color.BLACK);
                g2d.fillRect(50, 50, 280, 280);
                g2d.setColor(color);
                repaint();
            }
        };
        
        
        boton1.addActionListener(guardar);
    }
    
    public String guardarArchivo(String archivo, byte [] img){
        String mensj = null;
        try {
            salida = new FileOutputStream(archivo);
            salida.write(img);
            mensj = "Archivo Guardado";
        } catch (Exception e) {
        }
        return mensj;
    }
    
        
}
