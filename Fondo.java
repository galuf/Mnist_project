/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Roderick
 */
public class Fondo extends JPanel {
    private Image imagenFondo;
    String rutaFondo;
    File archivoRutaFondo;
    PanelDibujo PD = new PanelDibujo();
    JButton boton1;

    @Override
    public void paint(Graphics g) {
        archivoRutaFondo = new File("");
        rutaFondo = archivoRutaFondo.getAbsolutePath() + "\\imagenfondo\\Unilogo.png";
            
        imagenFondo = new ImageIcon(rutaFondo).getImage();
            
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            
        setOpaque(false);
            
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Fondo(){
        setLayout(null);
        this.add(PD);
        //revalidate();
        //repaint();
        BotonGuardar();
    }
    
    private void BotonGuardar(){
        boton1 = new JButton();
        boton1.setText("Guardar Imagen");
        boton1.setBounds(105, 380, 160, 50);
        this.add(boton1);
        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PD.byteCon = new ByteArrayOutputStream();
                PD.archivo = new File("");
                PD.ahora = new Date();
                PD.defaultDate = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.MEDIUM);
                PD.ahoraString = PD.defaultDate.format(PD.ahora);
                String auxString = PD.ahoraString.replace(' ', '_');
                PD.ruta = PD.archivo.getAbsolutePath() + "\\imagenes\\img_n_" + auxString.replace(':', '/').replace('/', '_') + ".jpg";
                
                try {
                    ImageIO.write(PD.image, "jpg", PD.byteCon);
                } catch (IOException ex) {
                    //Logger.getLogger(DrawApp.class.getName()).log(Level.SEVERE, null, ex);
                }
                PD.img = PD.byteCon.toByteArray();
                System.out.println(PD.ruta);
                PD.guardarArchivo(PD.ruta, PD.img);
                
                Color color = PD.g2d.getColor();
                PD.g2d.setColor(Color.BLACK);
                PD.g2d.fillRect(0, 0, 280, 280);
                PD.g2d.setColor(color);
                PD.repaint();
            }
        };
        
        
        boton1.addActionListener(guardar);
    }
}

