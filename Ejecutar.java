/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Ejecutar {
    
    public Ejecutar() {
        
    }
    
    public void executeTrain() throws IOException{
        File file1 = new File("");
        LeerArchivos leer = new LeerArchivos();
        ImagenMatriz IM = new ImagenMatriz();
        for(int i=0;i<10;i++){
            String rutafile1 = file1.getAbsolutePath()+"/data/train/"+i;
            System.out.println(rutafile1);
            File file2 = new File(rutafile1);
            ArrayList<String> arrayLeido = leer.leerCarpeta(file2);
            String iterador = String.valueOf(i);
            for(String s:arrayLeido){
                IM.guardarArchivo(IM.aplanarMatriz(IM.leerMatriz(rutafile1+"/"+s)),"Train"+iterador+".csv",iterador);
            }
        }
    }
        public void executeTest() throws IOException{
        File file1 = new File("");
        LeerArchivos leer = new LeerArchivos();
        ImagenMatriz IM = new ImagenMatriz();
        for(int i=0;i<10;i++){
            String rutafile1 = file1.getAbsolutePath()+"/data/test/"+i;
            File file2 = new File(rutafile1);
            ArrayList<String> arrayLeido = leer.leerCarpeta(file2);
            String iterador = String.valueOf(i);
            for(String s:arrayLeido){
                IM.guardarArchivo(IM.aplanarMatriz(IM.leerMatriz(rutafile1+"/"+s)),"Test"+iterador+".csv", iterador);
            }
        }
    }
}
