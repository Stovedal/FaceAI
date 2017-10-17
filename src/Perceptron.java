import static java.lang.System.err;
import static java.lang.System.exit;

/**
 * Created by Linnea on 2017-10-04.
 */
public class Perceptron {

    private double[][] weights;


    public Perceptron(){

        weights = new double[20][20];

        for(int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                weights[i][j] = Math.random();
            }
        }

    }



    public double guessMood(Image image) {

        double sum = 0;

        for(int i = 0 ; i < 20 ; i++) {
            for (int j = 0; j < 20; j++) {
                sum += weights[i][j] * image.data[i][j];
            }
        }

        return activationFunction(sum);


    }


    public double activationFunction (double sum) {

        return (1/( 1 + Math.pow(Math.E,(-1*sum))));

    }



    public void adjustWeights(double error, Image image) {

        double learningrate = 0.1;
        for(int i = 0 ; i < 20 ; i++) {
            for (int j = 0; j < 20; j++) {
                double delta = learningrate * error * image.data[i][j];
                weights[i][j] = weights[i][j] + delta;
            }
        }

    }



}
