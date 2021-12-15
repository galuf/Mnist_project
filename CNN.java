import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.util.*;

public class CNN {

  public double[][] sumaMatrices(double A[][], double B[][],int sizeX, int sizeY) { 
      int i, j; 
      double C[][] = new double[sizeX][sizeY]; 

      for (i = 0; i < sizeX; i++) 
          for (j = 0; j < sizeY; j++) 
              C[i][j] = A[i][j] + B[i][j]; 

      return C; 
  }

  public double fun_ReLU(double x) {
    if (x > 0) {
      return x;
    } else {
      return 0.0;
    }
  }

  public double[][] Relu(double[][] m) {
    double[][] res = new double[m.length][m[0].length];
    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[0].length; j++) {
        res[i][j] = fun_ReLU(m[i][j]);
      }
    }
    return res;
  }

  public double[] flater(List<double[][]> imagenes){
    int tamanio = imagenes.size()*imagenes.get(0).length*imagenes.get(0)[0].length;
    double[] entrada = new double[tamanio];
    for(int i=0;i<imagenes.size();i++){
      int index = i*imagenes.get(0).length*imagenes.get(0)[0].length;
      for(int j=0;j<imagenes.get(0).length;j++){
        for(int k=0;k<imagenes.get(0)[0].length;k++ ){
          //System.out.println(index+j*(imagenes.get(0).length > imagenes.get(0)[0].length ? imagenes.get(0)[0].length:imagenes.get(0).length) +k);
          entrada[index+j*(imagenes.get(0).length > imagenes.get(0)[0].length ? imagenes.get(0)[0].length:imagenes.get(0).length) +k] = imagenes.get(i)[j][k];
        }
      }
    }
    return entrada;
  }
  
  public double [][] maxPooling(double [][] m, int dim){
    //TODO: generalizar
    
    int filas = m.length, columnas = m[0].length;
    
    if ((filas%dim) != 0 || (columnas%dim) != 0 ) {
        int resto = filas%dim;
        if(resto%2==0){
            double[][] m1 = padding(m, resto/2);
            int f1 = m1.length, c1= m1[0].length;
            double[][] res = new double[f1/dim][c1/dim];
            
            for(int i=0;i<f1/dim;i++){
                for(int j=0;j<c1/dim;j++){
                    double maximo = -10000;  
                    for(int x=i*dim;x<i*dim+dim;x++){          
                        for(int y=j*dim;y<j*dim+dim;y++){
            
                            if(maximo < m1[x][y]) {
                                maximo = m1[x][y];
                            }
                            //System.out.println(maximo);
                        }
                    }
                    res[i][j] = maximo;
                }
            }
            return res;
        }
        else {
            double [][] m1 = paddingRight(m, (resto-1)/2);
            int f1 = m1.length, c1= m1[0].length;
            double[][] res = new double[f1/dim][c1/dim];
            
            for(int i=0;i<f1/dim;i++){
                for(int j=0;j<c1/dim;j++){
                    double maximo = -10000;  
                    for(int x=i*dim;x<i*dim+dim;x++){          
                        for(int y=j*dim;y<j*dim+dim;y++){
            
                            if(maximo < m1[x][y]) {
                                maximo = m1[x][y];
                            }
                            //System.out.println(maximo);
                        }
                    }
                    res[i][j] = maximo;
                }
            }
            return res;
        }
            
            
    }else{
        double[][] res = new double[filas/dim][columnas/dim];

    for(int i=0;i<filas/dim;i++){
      for(int j=0;j<columnas/dim;j++){
        double maximo = -10000;  
        for(int x=i*dim;x<i*dim+dim;x++){          
          for(int y=j*dim;y<j*dim+dim;y++){
            
            if(maximo < m[x][y]) {
              maximo = m[x][y];
            }
            //System.out.println(maximo);
          }
        }
        res[i][j] = maximo;
      }
    }
    
    return res;
    } 
    
  }

  public double [][] padding(double [][] m, int p){
    double [][] res = new double[m.length + 2*p][m[0].length + 2*p];
    
    int x=p;
    for(int i=0;i<m.length; i++){
        int y=p;
      for(int j=0;j<m[0].length; j++){
          res[x][y] = m[i][j];
          y++;
      }
      x++;
    }
    return res;
  }
  
  public double [][] paddingRight(double [][]m,int p){
      double[][] res = padding(m, p);
      double[][] res2 = new double[res.length+1][res[0].length+1];
      
      for (int i = 0; i <res.length; i++) {
          for (int j = 0; j < res[0].length; j++) {
              res2[i][j] = res[i][j];
          }
      }
      
      return res2;
  }

  public static double[][] convolucion(double[][] image, double kernel[][]){
  
    int width_img = image.length;
    int height_img = image[0].length;
    
    int kernel_col = kernel[0].length;
    int kernel_row = kernel.length;
    int offset_x = kernel_col/2;
    int offset_y = kernel_row/2;
    
    double[][] output = new double[width_img-kernel_col+1][height_img-kernel_row+1];
    int out_x = 0;
    int out_y = 0;
    
    for(int x=0; x<width_img - kernel_col + 1 ;x++)
    {
        for(int y = 0; y < height_img - kernel_row + 1;y++)
        {
                float out=0f;
                for(int kr=0; kr<kernel_row; kr++)
                    for(int kc=0; kc<kernel_col; kc++)
                    {
                            int imageX = x + kr;
                            int imageY = y + kc;

                            int punto = (int)image[imageX][imageY];
                            out += (punto*kernel[kr][kc]);

                    }
                int o = (int) out;
                
                output[x][y] = o;
                out_y++;
               
        }
        out_y = 0;
        out_x++;
    }
    
    return output;
  }

  public double [] proceso_matriz(double [][] m){
    // PRIMERA CAPA CNN
    int primeraCapa = 3;
    List<double[][]> outputs_kernel = new ArrayList<>();

    double[][] kernel1 = {{ 1, 0, -1 },
                          { 1, 0, -1 },
                          { 1, 0, -1 } };
    double[][] kernel2 = {{ 1, 0, -1 },
                          { 2, 0, -2 },
                          { 1, 0, -1 } };
    double[][] kernel3 = {{ 0, -1, 0 },
                          { -1, 4, -1 },
                          { 0, -1, 0 } };

    List<double[][]> kernel_capa1 = new ArrayList<>();

    kernel_capa1.add(kernel1);
    kernel_capa1.add(kernel2);
    kernel_capa1.add(kernel3);

    for( int i = 0;i<primeraCapa;i++){

      double temp[][] = convolucion(m, kernel_capa1.get(i));
      outputs_kernel.add(temp);
    }

    // MAX POOLING
    List<double[][]> outputs_pooling = new ArrayList<>();
    for( double[][] actual:outputs_kernel){
      int poolDim = 3;
      outputs_pooling.add(maxPooling(actual, poolDim));
    }
    System.out.println("primer Pool");
    System.out.println("dimTotal: "+outputs_pooling.size());
    System.out.println("dimOne x: "+outputs_pooling.get(0).length+" y: "+outputs_pooling.get(0)[0].length);

    //ReLu
    List<double[][]> outputs_relu = new ArrayList<>();
    for( double[][] actual: outputs_pooling){
      outputs_relu.add(Relu(actual));
    }

    // PASO DE LAYER N-1 A LAYER N
    List<double[][]> outputs_primeraCapa = new ArrayList<>();
    int xDim = outputs_relu.get(0).length;
    int yDim = outputs_relu.get(0)[0].length;

    int segundaCapa = 5;

    double[][] kernel4 = { { 3, 10, 3 },
        { 0, 0, 0 },
        { -3, -10, -3 } };
    double[][] kernel5 = { { 0, -1, 0 },
        { -1, 5, -1 },
        { 0, -1, 0 } };
    double[][] kernel6 = { { 1 / 9, 1 / 9, 1 / 9 },
        { 1 / 9, 1 / 9, 1 / 9 },
        { 1 / 9, 1 / 9, 1 / 9 } };
    double[][] kernel7 = { { 1 / 16, 1 / 8, 1 / 16 },
        { 1 / 8, 1 / 4, 1 / 8 },
        { 1 / 16, 1 / 8, 1 / 16 } };
    double[][] kernel8 = { { 1, 2, 1 },
        { 0, 0, 0 },
        { -1, -2, -1 } };

    List<double[][]> kernel_capa2 = new ArrayList<>();
    kernel_capa2.add(kernel4);
    kernel_capa2.add(kernel5);
    kernel_capa2.add(kernel6);
    kernel_capa2.add(kernel7);
    kernel_capa2.add(kernel8);

    for( int i = 0;i<segundaCapa;i++){

      double temp[][] = new double[xDim - kernel_capa2.get(0).length + 1][yDim - kernel_capa2.get(0)[0].length + 1];

      for (double[][] actual : outputs_relu) {
        double algotemp[][] = convolucion(actual, kernel_capa2.get(i));
        temp = sumaMatrices(temp, algotemp, algotemp.length, algotemp[0].length);
      }
      outputs_primeraCapa.add(temp);
      temp = null; // empty again
    }
    System.out.println("Segundo layer");

    // OTRO MAX POOLING
    List<double[][]> outputs_pooling2 = new ArrayList<>();
    for(double[][] actual : outputs_primeraCapa){
        int poolDim = 2;
        outputs_pooling2.add(maxPooling(actual, poolDim ));
    }
    System.out.println("Segundo Pool");
    System.out.println("dimTotal: "+outputs_pooling2.size());
    System.out.println("dimOne x: "+outputs_pooling2.get(0).length+" y: "+outputs_pooling2.get(0)[0].length);
    
    //ReLu
    List<double[][]> outputs_relu_1 = new ArrayList<>();
    for( double[][] actual: outputs_pooling2){
      outputs_relu_1.add(Relu(actual));
    }

    return flater(outputs_relu_1);
  }  
}

