
/**
 * Created by Linnea on 2017-10-04.
 */
public class Perceptron {

    private double[][] weights;

    /**
     * Perceptron which learns to recognize a certain mood
     */
    public Perceptron(){
        weights = new double[20][20];
        for(int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                weights[i][j] = Math.random();
            }
        }

    }

    /**
     * Activates to given image
     * @param image Image
     * @return double
     */
    public double guessMood(Image image) {

        double sum = 0;

        for(int i = 0 ; i < 20 ; i++) {
            for (int j = 0; j < 20; j++) {
                sum += weights[i][j] * image.data[i][j];
            }
        }
        return activationFunction(sum);
    }

    /**
     * Using the sigmoid-function, calculates the activation
     * of the perceptron.
     * @param sum
     * @return
     */
    private double activationFunction (double sum) {
        return (1/( 1 + Math.pow(Math.E,(-1*sum))));
    }

    /**
     * Adjusts the perceptron's weights according to given error and image.
     * @param error double
     * @param image Image
     */
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
