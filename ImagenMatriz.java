import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ImagenMatriz {

  public double [][] leerMatriz(String Ruta) throws IOException {
    InputStream input = new FileInputStream(Ruta);
    ImageInputStream imageInput = ImageIO.createImageInputStream(input);
    BufferedImage imagenL = ImageIO.read(imageInput);
  
    int alto = imagenL.getHeight();
    int ancho = imagenL.getWidth();
    
    double matriz[][] = new double[alto][ancho];
    double matriz_res[][] = new double[28][28];
    
    if(alto>28&&ancho>28){
        for(int y=0; y < alto; y++){
            for(int x=0; x < ancho; x++ ){

            int srcPixel = imagenL.getRGB(x, y);

            Color c = new Color(srcPixel);
            int valR = c.getRed();
        
            matriz[y][x] = valR;
            
            }
        
        }

        for(int i=0;i<28;i++){          
            for(int j=0;j<28;j++){
                //float suma = 0; 
                double max = 0;
                for(int x=i*10;x<i*10+10;x++){          
                    for(int y=j*10;y<j*10+10;y++){
                        //suma += matriz[x][y];
                        if (max < matriz[x][y]) {
                            max = matriz[x][y];
                        }
                    }
                }
                matriz_res[i][j] = max;
            }
        }

    }else{
        for(int y=0; y < alto; y++){
            for(int x=0; x < ancho; x++ ){

            int srcPixel = imagenL.getRGB(x, y);

            Color c = new Color(srcPixel);
            int valR = c.getRed();
        
            matriz[y][x] = valR;
            
            } 
        }

        for(int i=0;i<28;i++){
            for(int j=0;j<28;j++){
                matriz_res[i][j] = matriz[i][j];
            }
        }
    }

    //TODO: Normallizar, revisar convolusion Erronea
    return matriz_res;
  }
  
  public double [] aplanarMatriz(double [][] matriz){
      double [] arrAplanado = new double[28*28];
      int k=0;
      for(int i=0;i<28;i++){
          for(int j=0;j<28;j++){
              arrAplanado[k] = matriz[i][j];
              k++;
          }
      }
      return arrAplanado;
  }
  
  public void guardarArchivo (double [] arrayAplanado,String Nombre,String iteradorString) throws IOException{
    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ArchivoEntrada"+Nombre,true)))) { 
        String archivoString = Arrays.toString(arrayAplanado);
        String archivoFinal;
        archivoFinal = archivoString.replace("[", "").replace("]", "").replace(" ", "");
        out.print(archivoFinal);
        switch (iteradorString) {
            case "0": out.println(",1,0,0,0,0,0,0,0,0,0"); break;
            case "1": out.println(",0,1,0,0,0,0,0,0,0,0"); break;
            case "2": out.println(",0,0,1,0,0,0,0,0,0,0"); break;
            case "3": out.println(",0,0,0,1,0,0,0,0,0,0"); break;
            case "4": out.println(",0,0,0,0,1,0,0,0,0,0"); break;
            case "5": out.println(",0,0,0,0,0,1,0,0,0,0"); break;
            case "6": out.println(",0,0,0,0,0,0,1,0,0,0"); break;
            case "7": out.println(",0,0,0,0,0,0,0,1,0,0"); break;
            case "8": out.println(",0,0,0,0,0,0,0,0,1,0"); break;
            case "9": out.println(",0,0,0,0,0,0,0,0,0,1"); break;
            default:
                throw new AssertionError();
        }
    } catch (IOException e){ System.out.println(e);}
  }
}
