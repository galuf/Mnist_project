public class prueba03 {
	public static void main(String[] args) {   

		double ingreso[][]={ 
												{1,1,1,1,0,1,1,1,1,1,0,1,1,0,1},//letra A
												{1,1,1,1,0,0,1,1,1,1,0,0,1,1,1},//letra E
												{0,1,0,0,1,0,0,1,0,0,1,0,0,1,0},//letra I
												{1,1,1,1,0,1,1,0,1,1,0,1,1,1,1}//Letra 0
												};
		double salida[][]={{0,0},{0,1},{1,0},{1,1}};
		double evaluar[][]={
												{1,1,1,1,0,1,1,1,1,1,0,1,1,0,1},//letra A
												{1,1,1,1,0,0,1,1,1,1,0,0,1,1,1},//letra E
												{0,1,0,0,1,0,0,1,0,0,1,0,0,1,0},//letra I
												{1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},//letra 0
												{1,1,0,1,0,1,1,1,1,1,0,1,0,0,1},//letra A
												{1,1,1,1,0,0,1,1,0,1,0,0,1,1,1},//letra E
												{0,1,1,0,1,0,0,1,0,0,1,0,1,1,0},//letra I
												{1,1,0,1,0,1,1,0,1,1,0,1,1,1,0},//letra 0
												};

		//ReLU rn = new ReLU(false,15,15,10,5,3,2);
		//rn.entrenamiento(ingreso, salida,100000);
		//rn.prueba(evaluar);

		double init [][] = new double[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				init[i][j] = 1;
			}
		}

		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(init[i][j]);
			}
			System.out.println("");
		}

		System.out.println("");

		Cnn_process cnn = new Cnn_process();

		double[][] res =  cnn.padding(init,1);
		for(int i=0;i<12;i++){
			for(int j=0;j<12;j++){
				System.out.print(res[i][j]);
			}
			System.out.println("");
		}
	}
}
