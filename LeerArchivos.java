import java.io.File;
import java.util.ArrayList;

public class LeerArchivos {
    
    public ArrayList<String> leerCarpeta(File rutaFile) {
        ArrayList<String> Elementos = new ArrayList<>();
        
        for(File archivoFile : rutaFile.listFiles()){
            if(!archivoFile.isDirectory()){
                Elementos.add(archivoFile.getName());
            }
            else{
                leerCarpeta(archivoFile);
            }
        }
        
        return Elementos;
        
}
    public void mostrarArray (ArrayList<String> arraynombre){
        for(String s : arraynombre){
            System.out.println(s);
        }
    }
}
