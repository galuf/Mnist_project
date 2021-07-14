/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        
        //Ejecutar ejec = new Ejecutar();
        //ejec.executeTrain();
        //ejec.executeTest();
        //(784,500,200,70,25,10)
        //ReLU prob = new ReLU(false,784,700,350,100,50,10);
        //prob.entrenamiento_file("train_x.csv", "train_y.csv", 25);
        //prob.guardaPesos();

        Test prueba = new Test(784,500,200,70,25,10);
        System.out.println(prueba.accuracy("test_x.csv","test_y.csv",5000));
        System.out.println(prueba.accuracy("train_x.csv","train_y.csv",10000));
        //prueba.show(10);
        //prueba.mayores(); -> [1er max,2do maxim]
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
}
