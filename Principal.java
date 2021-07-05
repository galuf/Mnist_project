/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Roderick
 */
public class Principal {
    public static void main(String[] args) throws IOException {
        //ImagenMatriz im = new ImagenMatriz();
        //im.guardarArchivo(im.aplanarMatriz(im.leerMatriz()));
        
        //Ventana v1 = new Ventana();
        /*LeerArchivos LA = new LeerArchivos();
        File archivoprueba = new File("");
        String rutaarchivoprueba = archivoprueba.getAbsolutePath() + "\\imagenes";
        File archivofinal = new File(rutaarchivoprueba);
        LA.mostrarArray(LA.leerCarpeta(archivofinal));*/
        
        Ejecutar ejec = new Ejecutar();
        ejec.executeTrain();
        ejec.executeTest();
    }
}
