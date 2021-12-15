public class Cnn_process {

  public double[][] maxPooling(double[][] m, int salto) {
    int filas = m.length, columnas = m[0].length;
    double[][] res = new double[filas / salto][columnas / salto];

    for (int i = 0; i < filas / salto; i++) {
      for (int j = 0; j < columnas / salto; j++) {
        double maximo = -10000;
        for (int x = i * salto; x < i * salto + salto; x++) {
          for (int y = j * salto; y < j * salto + salto; y++) {

            if (maximo < m[x][y]) {
              maximo = m[x][y];
            }
            // System.out.println(maximo);
          }
        }
        res[i][j] = maximo;
      }
    }

    return res;
  }

  public double fun_ReLU(double x) {
    if (x > 0) {
      return x;
    } else {
      return 0.0;
    }
  }

  public double[][] padding(double[][] m, int p) {
    double[][] res = new double[m.length + 2 * p][m[0].length + 2 * p];
    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[0].length; j++) {

        for (int k = 0; k < p; k++) {
          if ((i != k || i != res.length - 1 - k)) {
            res[i][j] = m[i][j];
          }
        }

      }
    }
    return res;
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

}