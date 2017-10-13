/**
 * Created by Linnea on 2017-10-04.
 */
public class Perceptron {

    private double[] moodValues = {Math.random(), Math.random(), Math.random() ,Math.random()};
    private double[] weights;
    public int i;
    public int j;
    public int nrOfInputs;

    public Perceptron(int i, int j){

        this.i = i;
        this.j = j;
        nrOfInputs = getNrOfInputs(i,j);
        weights = new double[nrOfInputs];


        for(int index = 0; index < nrOfInputs; index ++){
            weights[index] = Math.random();
        }

    }

    public int getNrOfInputs(int i, int j){
        int numberOfInputs = 0;

        for(int x = i - 1; x < i + 1; x++){
            for(int y = j - 1; y < j + 1; y++){

                if(i > -1 && i < 20 && j > -1 && j < 20){
                    numberOfInputs ++;
                }

            }
        }
        return numberOfInputs;
    }

    public int guessMood(double[] inputs) {

        double sum = calcSum(inputs);

        return decideMood(sum);

    }

    public double calcSum(double[] inputs){
        double sum = 0;

        for (int i = 0; i < nrOfInputs; i++) {
            sum += inputs[i] * weights[i];
        }
        return sum;
    }


    public int decideMood(double sum){
        double happyDiff = Math.abs(moodValues[0] - sum);
        double sadDiff = Math.abs(moodValues[1] - sum);
        double mischievousDiff = Math.abs(moodValues[2] - sum);
        double madDiff = Math.abs(moodValues[3] - sum);
        if (Double.compare(happyDiff, sadDiff) < 0) {

            if (Double.compare(mischievousDiff, madDiff) < 0) {
                if (Double.compare(happyDiff, mischievousDiff) < 0) {
                    //Return happy
                    return 0;
                } else {
                    //Return mischievous
                    return 2;
                }
            } else {
                if (Double.compare(happyDiff, madDiff) < 0) {
                    //Return happy
                    return 0;
                } else {
                    //Return mad
                    return 3;
                }

            }
        } else {
            if (Double.compare(mischievousDiff, madDiff) < 0) {
                if (Double.compare(sadDiff, mischievousDiff) < 0) {
                    //Return sad
                    return 1;
                } else {
                    //Return mischievous
                    return 2;
                }
            } else {
                if (Double.compare(sadDiff, madDiff) < 0) {
                    //Return sad
                    return 1;
                } else {
                    //Return mad
                    return 3;
                }

            }
        }
    }



    public void adjustWeights(int correctMood, double[] inputs){
        double error = moodValues[correctMood] - calcSum(inputs);
        moodValues[correctMood] = calcSum(inputs);

        for(int i = 0; i < nrOfInputs; i++){
            double delta = 0.5*error*inputs[i];
            weights[i] = weights[i] + delta;
        }

    }

}
