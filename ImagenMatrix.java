import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ImagenMatrix {

  public static void main(String[] args) throws IOException{
    
    InputStream input = new FileInputStream("./img/my_img.jpg");
    ImageInputStream imageInput = ImageIO.createImageInputStream(input);
    BufferedImage imagenL = ImageIO.read(imageInput);
  
    int alto = imagenL.getHeight();
    int ancho = imagenL.getWidth();
    
    int matriz[][] = new int[alto][ancho];

    for(int y=0; y < alto; y++){
      for(int x=0; x < ancho; x++ ){

        int srcPixel = imagenL.getRGB(x, y);

        Color c = new Color(srcPixel);
        int valR = c.getRed();
        
        matriz[y][x] = valR;
        //System.out.print(valR + " ");
        // if(valR != 0){
        //   System.out.print("*");
        // }else{
        //   System.out.print(" ");
        // }
      }
      //System.out.println("");
    }
    int matriz_res[][] = new int[28][28];

    for(int i=0;i<28;i++){
      for(int j=0;j<28;j++){
        float suma = 0; 
        for(int x=i*10;x<i*10+10;x++){          
          for(int y=j*10;y<j*10+10;y++){
            suma += matriz[x][y];
            //System.out.println(suma);
          }
        }
      
      matriz_res[i][j] = (suma/100 != 0 ? 1 : 0);
      System.out.print(matriz_res[i][j] + " ");
      }
      System.out.println("");
    }



    // 0 0 1 1 1 1 1 1
    // 0 0 1 1 1 1 1 1       1 1 1 1
    // 1 1 1 1 1 1 1 1       1 1 1 1
    // 1 1 1 1 1 1 1 1       1 1 1 1
    // 1 1 1 1 1 1 1 1       1 1 1 1 
    // 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1

    // 1 + 1 + 1 + 1 / 4 = 1

  }

}
