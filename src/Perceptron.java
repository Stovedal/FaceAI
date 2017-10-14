import static java.lang.System.err;
import static java.lang.System.exit;

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

        for(int x = i - 1; x < i + 2; x++){
            for(int y = j - 1; y < j + 2; y++){

                if(x > -1 && x < 20 && y > -1 && y < 20){
                    numberOfInputs++;
                }
            }
        }
        return numberOfInputs;
    }

    public int guessMood(double[] inputs) {

        double sum = calcSum(inputs);
        return decideMood(sum);

    }

    private double calcSum(double[] inputs){
        double sum = 0;
        for (int i = 0; i < nrOfInputs; i++) {
            sum += inputs[i] * weights[i];
        }
        if(Double.isNaN(sum)){
            System.out.println("Number of inputs " + nrOfInputs + " for perceptron " + i + " " + j);
            for (int i = 0; i < nrOfInputs; i++) {
                sum += inputs[i] * weights[i];
                System.out.println("input " + inputs[i]);
                System.out.println("weight " + weights[i]);
            }
            throw new NumberFormatException("NaN found in calcsum");
        }
        return sum;
    }


    private int decideMood(double sum){
        double happyDiff = Math.abs(moodValues[0] - sum);
        double sadDiff = Math.abs(moodValues[1] - sum);
        double misDiff = Math.abs(moodValues[2] - sum);
        double madDiff = Math.abs(moodValues[3] - sum);
        if(Double.compare(happyDiff, sadDiff)<0 && Double.compare(happyDiff, misDiff)<0 && Double.compare(happyDiff, madDiff)<0){
            return 1;
        }
        if(Double.compare(sadDiff, happyDiff)<0 && Double.compare(sadDiff, misDiff)<0 && Double.compare(sadDiff, madDiff)<0){
            return 2;
        }
        if(Double.compare(misDiff, sadDiff)<0 && Double.compare(misDiff, happyDiff)<0 && Double.compare(misDiff, madDiff)<0){
            return 3;
        }
        if(Double.compare(madDiff, sadDiff)<0 && Double.compare(madDiff, misDiff)<0 && Double.compare(madDiff, happyDiff)<0){
            return 4;
        }
        return 5;

    }


    public void adjustWeights(int correctMood, double[] inputs){
        double error = moodValues[correctMood-1] - calcSum(inputs);
        if(Double.isNaN(error) || Double.isInfinite(error)){
            System.out.println("moodValues correct " + moodValues[correctMood-1] + " calcSum " + calcSum(inputs));
            //throw new NumberFormatException("error is incorreft in adjustweights " + error);

        }
        double learningRate = 1;
        for(int i = 0; i < nrOfInputs; i++){
            double delta = learningRate*error*inputs[i];
            if(Double.isNaN(delta)){
                System.out.println("learningrate " + learningRate + " error " + error + " input " + inputs[i]);
                //throw new NumberFormatException("Delta is Nan in adjustweights");
            }

            weights[i] = weights[i] + delta;
        }
        moodValues[correctMood-1] = moodValues[correctMood-1] - learningRate*error*calcSum(inputs);
    }

}
