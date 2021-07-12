public class prueba03 {
    public static void main(String[] args) {   
//        double ingreso[][]={{0,1,0},{0,1,1},{1,0,0},{1,0,1}};
//        double salida[][]={{1},{0},{1},{0}};
//        double evaluar[][]={{0,1,0},{0,1,1},{1,0,0},{1,0,1},{1,1,1},{0,0,0},{1,1,0}};
//        neuralnnn002 rn = new neuralnnn002(3,2,1);
//        rn.entrenamiento(ingreso, salida,1000);
//        rn.prueba(evaluar);
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

        ReLU rn = new ReLU(false,15,15,10,5,3,2);
        rn.entrenamiento(ingreso, salida,100000);
        rn.prueba(evaluar);
    }
}
