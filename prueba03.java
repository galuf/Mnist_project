import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class prueba03 {

	public static void show(double [] n){
		int n_ = n.length;
		for(int i=0; i<n_;i++){
			System.out.print(n[i]+ " ");
		}
	}

	public static double[][] kernelEntreLayers(double[][] image, double kernel[][]){
  
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

	public static void muestra(double[][] m){
		int filas = m.length;
		int col = m[0].length;

		for(int i=0;i<filas;i++){
			for(int j=0;j<col;j++){
				System.out.print(m[i][j]);
			}
			System.out.println("");
		}

	}

	public static void main(String[] args) throws IOException{   

		ReLU prob = new ReLU(false,80,40,10);
    prob.entrenamiento_file("train_x_cnn.csv", "train_y_cnn.csv", 100);
    prob.guardaPesos();

		//Ejecutar eje = new Ejecutar();
		//eje.executeTrain();
		//eje.executeTest();

		Test prueba = new Test(80,40,10);
    System.out.printf("%.2f\n",prueba.accuracy("test_x_cnn.csv","test_y_cnn.csv",5000));
    System.out.printf("%.2f\n",prueba.accuracy("train_x_cnn.csv","train_y_cnn.csv",10000));

	}
}