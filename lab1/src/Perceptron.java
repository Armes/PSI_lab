import java.text.DecimalFormat;

/**
 * Created by K_Pudlo on 18/10/2016.
 */
public class Perceptron {


	// dane wejsciowe
    static int MAX_ITER = 100;
    static double LEARNING_RATE = 0.1;
    static int N_INST = 100;
    static int theta = 0;

    public static void main(String args[]) {

        double[] x = new double[N_INST];
        double[] y = new double[N_INST];
        double[] z = new double[N_INST];
        int[] outputs = new int [N_INST];

        //fifty random points of class 1
        for (int i=0; i<N_INST/2; i++) {
            x[i] = randomNumber(5, 10);
            y[i] = randomNumber(4, 8);
            z[i] = randomNumber(2, 9);
            outputs[i] = 1;
            System.out.println(x[i] + "\t" + y[i] + "\t" + z[i] + "\t" + outputs[i]);
        }

        for (int i=50; i<N_INST; i++) {
            x[i] = randomNumber(-1, 3);
            y[i] = randomNumber(-4, 2);
            z[i] = randomNumber(-3, 5);
            outputs[i] = 0;
            System.out.println(x[i] + "\t" + y[i] + "\t" + z[i] + "\t" + outputs[i]);
        }

        double[] weights = new double[4]; //3 for input and 1 for bias
        double localError, globalError;
        int p, iteration, output;

        weights[0] = randomNumber(0,1); //weight 1
        weights[1] = randomNumber(0,1); //weight 2
        weights[2] = randomNumber(0,1); //weight 3
        weights[3] = randomNumber(0,1); //bias

        iteration = 0;
        do {
            iteration++;
            globalError = 0;
            for (p=0; p < N_INST; p++) {
                output = calculateOutput(theta, weights, x[p], y[p], z[p]);
                localError = outputs[p] - output;

                //update weights and bias
                weights[0] += LEARNING_RATE * localError * x[p];
                weights[1] += LEARNING_RATE * localError * y[p];
                weights[2] += LEARNING_RATE * localError * z[p];
                weights[0] += LEARNING_RATE * localError;

                globalError += (localError*localError);

            }

            System.out.println("Iteration " + iteration + " : RMSE = " + Math.sqrt(globalError/N_INST));

        }while (globalError != 0 && iteration <= MAX_ITER);

        System.out.println("\n========\nDecision boundary equation:");
        System.out.println(weights[0] + "*x + " + weights[1] + "*y + " + weights[2] +
        "z + " + weights[3] + " = 0");

        for (int j = 0; j < 10; j++) {
            double x1 = randomNumber(-10, 10);
            double y1 = randomNumber(-10, 10);
            double z1 = randomNumber(-10, 10);

            output = calculateOutput(theta, weights, x1, y1, z1);
            System.out.println("\n=====\nNew Random Point:");
            System.out.println("x = " + x1 + ",y = " + y1 + ",z= " + z1);
            System.out.println("class = " + output);
        }

    } //end main

    public static double randomNumber(int min, int max) {
        DecimalFormat df = new DecimalFormat("#.####");
        double d = min + Math.random() * (max-min);
        String s = df.format(d);
        double x = Double.parseDouble(s);
        return x;
    }

    static int calculateOutput(int theta, double weights[], double x, double y, double z) {
        double sum = x*weights[0] + y*weights[1] + z*weights[2] + weights[3];
        return (sum >= theta) ? 1 : 0;
    }

}












