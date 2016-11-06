import java.text.DecimalFormat;

class DeltaRule {

    public static void main(String[] args) {

        final int ITER = 100;   // ilosc iteracji
        final double LR = .1; // wspolczynnik uczenia
        final int N_INST = 100; // ilosc punktow

        int theta = 0;

        double[] x = new double[N_INST];
        double[] y = new double[N_INST];
        double[] z = new double[N_INST];

        int[] outputs = new int [N_INST];

        for (int i = 0; i < 50; i++) {
            // 50 przykladowych punktow klasy 1
            x[i] = randomNumber(5, 10);
            y[i] = randomNumber(4, 8);
            z[i] = randomNumber(2, 9);
            outputs[i] = 1;

            // 50 przykladowych punktow klasy 1
            x[50+i] = randomNumber(-1, 3);
            y[50+i] = randomNumber(-4, 2);
            z[50+i] = randomNumber(-3, 5);
            outputs[50+i] = 0;
        }

        // tablica wag, 3 zmienne + bias
        double[] weights = new double[4];
        for (int i = 0; i < 4; i++) weights[i] = randomNumber(0 , 1);
        double localError = 0, globalError = 0;
        int iterations, output;

        iterations = 0;
        do {
            iterations++;

            // iterowanie po kazdym punkcie; wykonanie jednej epoki
            for (int i = 0; i < N_INST; i++) {

                // obliczanie przwidywanej klasy
                output = calcOut(theta, weights, x[i], y[i], z[i]);
                // różnica między przewidywaną, a rzeczywistą wartością klasy
                localError = outputs[i] - output;

                // uaktualnienie wag i bias-u

                weights[0] += LR * localError * x[i];
                weights[1] += LR * localError * y[i];
                weights[2] += LR * localError * z[i];
                weights[3] += LR * localError;

                // zsumowanie bledu kwadratowego
                globalError += (localError*localError);

            }

            System.out.println("Iteracja: " + iterations);
            System.out.println("RMSE: " + Math.sqrt(  globalError/N_INST ));
            System.out.println("-----");


        } while (iterations < ITER && globalError != 0);

        System.out.println("\n========\nRównanie granicy decyzji:");
        System.out.println(weights[0] + "*x + " + weights[1] + "*y + " + weights[2] +
                "z + " + weights[3] + " = 0");

        for (int j = 0; j < 10; j++) {
            double x1 = randomNumber(-10, 10);
            double y1 = randomNumber(-10, 10);
            double z1 = randomNumber(-10, 10);

            output = calcOut(theta, weights, x1, y1, z1);
            System.out.println("\n=====\nNowy punkt losowy:");
            System.out.println("x = " + x1 + ",y = " + y1 + ",z= " + z1);
            System.out.println("Klasa = " + output);
        }

    }

    public static double randomNumber(int min, int max) {

        DecimalFormat df = new DecimalFormat("#.####");
        double d = (Math.random() * (max - min)) + min;
        String s = df.format(d);
        double x = Double.parseDouble(s);

        return x;

    }

    public static int calcOut(int t, double[] W, double x, double y, double z) {

        double sum = 0;
        sum += W[0]*x + W[1]*y + W[2]*z + W[3];

        return (sum > t) ? 1 : 0;

    }

}






