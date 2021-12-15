import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Ejecutar {
    
    public Ejecutar() {
        
    }
    
    public void executeTrain() throws IOException{
        File file1 = new File("");
        LeerArchivos leer = new LeerArchivos();
        ImagenMatriz IM = new ImagenMatriz();
        CNN cnn = new CNN();
        for(int i=0;i<10;i++){
            String rutafile1 = file1.getAbsolutePath()+"/data/train/"+i;
            System.out.println(rutafile1);
            File file2 = new File(rutafile1);
            ArrayList<String> arrayLeido = leer.leerCarpeta(file2);
            String iterador = String.valueOf(i);
            for(String s:arrayLeido){
                IM.guardarArchivo(cnn.proceso_matriz(IM.leerMatriz(rutafile1+"/"+s)),"Train"+iterador+".csv",iterador);
            }
        }
    }
    public void executeTest() throws IOException{
        File file1 = new File("");
        LeerArchivos leer = new LeerArchivos();
        ImagenMatriz IM = new ImagenMatriz();
        CNN cnn = new CNN();
        for(int i=0;i<10;i++){
            String rutafile1 = file1.getAbsolutePath()+"/data/test/"+i;
            File file2 = new File(rutafile1);
            ArrayList<String> arrayLeido = leer.leerCarpeta(file2);
            String iterador = String.valueOf(i);
            for(String s:arrayLeido){
                IM.guardarArchivo(cnn.proceso_matriz(IM.leerMatriz(rutafile1+"/"+s)),"Test"+iterador+".csv", iterador);
            }
        }
    }
}
