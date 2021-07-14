import java.util.*;
import java.io.*; 

public class Test {

    public ArrayList<double[][]> pesos = new ArrayList<double[][]>();
    public ArrayList<double[]> test = new ArrayList<double[]>();
    public ArrayList<double[]> salida = new ArrayList<double[]>(); //5000*10
    public ArrayList<double[][]> testeo = new ArrayList<double[][]>();       

    public int[] array;
    public int[] errors;
    public double[][] entrada; //5000*784
    public double[][] output; // 5000*10
    public double[][] evaluar;

    public double buenas = 0.0;
    public double malas = 0.0;
    public double total = 5000.0;

    public Test(int ...a){ // Spreat operator
      this.array = a;
      read_pesos();
    }

    public void read_pesos(){
      for(int i=0;i<array.length - 1;i++){
        File fiche = new File(System.getProperty("user.dir")+"//w"+(i+1)+".dat");
        pesos.add(matrizPeso(fiche));
      }
    }

    public double[][] matrizPeso(File peso){
    
        FileInputStream fis = null;
        DataInputStream entrada = null;
        double [][] matrizError = {{0,0,0},{0,0,0}};
        try {
            fis = new FileInputStream(peso);
            entrada = new DataInputStream(fis);
            int filas = entrada.readInt();            //se lee el primer entero del fichero                           
            int columnas = entrada.readInt();
            System.out.println(filas+" "+columnas);
            double [][] matriz = new double[filas][columnas];         //se lee el segundo entero del fichero
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {  // se leen los double y se guarda en una matriz                
                    matriz[i][j] = entrada.readDouble();
                }
            }
            return matriz;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());                                                               
            }
        }
        return matrizError;
    }
    
    public void mostrar(double[][] m){
        for(int i=0;i<m.length;i++){
          for(int j = 0; j<m[0].length;j++){
            System.out.print(m[i][j]+" ");
          }
          System.out.println();
        }
    }

    public double[][] dotArMa(double[] a, double[][] b){
    
        int fil_a = 1;
        int col_a = a.length;
            
        int fil_b = b.length;
        int col_b = b[0].length;
        
        if (col_a != fil_b){
          System.out.println(fil_a+" "+col_a);
          System.out.println(fil_b+" "+col_b);
          throw new RuntimeException("No se pueden multiplicar dotArMa las matrices");
        }
    
        double[][] multiplicacion = new double[fil_a][col_b]; // c
    
        for (int i = 0; i < fil_a; i++) {
          for (int j = 0; j < col_b; j++) {
            for (int k = 0; k < col_a; k++) {
              multiplicacion[i][j] += a[k] * b[k][j];
            }
          }
        }
        return multiplicacion;
    }

    public double sigmoidea(double x){
        return 1/(1+Math.exp(-x));
    }
    
    public double fun_ReLU(double x){
      if(x > 0){
        return x;
      }
      else{
        return 0.0;
      }
    }

    public double[][] matrizSigmoidea(double[][] m){
        double[][] m2 = new double[m.length][m[0].length];
        for(int i=0;i<m.length;i++){
          for(int j = 0; j<m[0].length;j++){
            m2[i][j] = sigmoidea(m[i][j]);
          }
        }
        return m2;
    }

    public double[][] matrizReLU(double[][] m){
      double[][] m2 = new double[m.length][m[0].length];
      for(int i=0;i<m.length;i++){
        for(int j = 0; j<m[0].length;j++){
          m2[i][j] = fun_ReLU(m[i][j]);
        }
      }
      return m2;
    }

    public double[][] read_file(String rutaFile,int x){
        
      double[][] matrizLectura = new double[x][];
            
      try (BufferedReader in = new BufferedReader( new FileReader(rutaFile))){ 
        String line = in.readLine(); 
        int k=0;
        while (line != null) {                
          String [] arrStrings = line.split(",");
          int i=0;
          matrizLectura[k] = new double[arrStrings.length];
          
          for(String s: arrStrings){
            matrizLectura[k][i] = Double.parseDouble(s);
            i++;
          }
          line = in.readLine();
          k++;
        }
      } catch (IOException e){
        System.out.println(e);
      }
            
      return matrizLectura;
    }

    public void prueba(){
        
      salida.clear();
        
      for(int i = 0; i<entrada.length;i++){
        test.clear();
        test.add(entrada[i]);
        //show_array(entrada[i]);
        //System.out.println(entrada.length);  
        for(int ii=1;ii<array.length;ii++){
          
            test.add(
              matrizSigmoidea(
                dotArMa(test.get(ii-1),
                        pesos.get(ii-1)
                )
              )[0]
            );
          

            
        }

        salida.add(test.get(test.size()-1));
      }

    }
    
    public double accuracy(String ruta_x,String ruta_y, int row){
      this.entrada = read_file(ruta_x,row); //[5000][784]
      this.output = read_file(ruta_y,row); // [5000][10]
      
      this.errors = new int[10];
      for(int i=0;i<10;i++) this.errors[i] = 0;

      prueba();
      this.buenas = 0;
      this.malas = 0;

      for(int i=0;i<entrada.length;i++){
        mayor(salida.get(i),this.output[i]);
      }
      for(int i=0;i<10;i++){
        System.out.print(this.errors[i] + " ");
      }
      System.out.println("");
      System.out.println("las buenas son: " + this.buenas);
      System.out.println("las malas son: " + this.malas);
      return (this.buenas/row)*100;
    }

    public void mayor(double [] numeros, double[] output_i){
      //numero => [0.1,.02,0.3,.03,]
      //[0.0.0.0.1.0.0.0.0]
      int ans=0;
      double mayor = -10000;
      int index=0;
      for(int i =0;i<numeros.length;i++){
        if(numeros[i] > mayor){
          mayor=numeros[i];
          index = i;
        }
      }
      for(int i=0;i<output_i.length;i++){
        if(output_i[i] == 1.0){
          ans = i;
          break;
        }
      }

      if(output_i[index] == 1.0){
        this.buenas = this.buenas + 1;
        //System.out.println(this.buenas);
      }else{
        this.errors[ans] = this.errors[ans] + 1;
        this.malas = this.malas + 1;
      }
    }  
    
    public void show_array(double[] d){
      for(int i=0;i<d.length;i++){
        System.out.print(d[i]);
        System.out.print(" ");
      }
      System.out.println(" ");
    }

    public void show(int num){
      for(int i=0;i<num;i++){
        System.out.print("Real : ");
        show_array(output[i]);
        System.out.print("Salida: ");
        show_array(salida.get(i));
      }
    }
    // public double[] mayores_simple(){
    //   double m[] = new double[2];
    // }

    // public void test_unit(String ruta){
    //   //double [][] = 1*784
    // }
}