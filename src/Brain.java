import java.rmi.server.ExportException;

/**
 * Created by Linnea on 2017-10-04.
 */
public class Brain {

    private Perceptron happyPerceptrons;
    private Perceptron sadPerceptrons;
    private Perceptron mischeiviousPerceptrons;
    private Perceptron madPerceptrons;


    public Brain(){

        happyPerceptrons = new Perceptron();
        sadPerceptrons = new Perceptron();
        mischeiviousPerceptrons = new Perceptron();
        madPerceptrons = new Perceptron();


    }

    public double computeError(boolean answer, double guess){

        if (answer){
            return 1 - guess;
        }
        else{
            return  0 - guess;
        }

    }




    public boolean train(Image image){

        double happy = happyPerceptrons.guessMood(image);
        double sad = sadPerceptrons.guessMood(image);
        double mis = mischeiviousPerceptrons.guessMood(image);
        double mad = madPerceptrons.guessMood(image);

        int answer = getMood(happy, sad, mis, mad);

        if (answer == image.answer){
            happyPerceptrons.adjustWeights(computeError(image.answer == 1, happy), image);
            sadPerceptrons.adjustWeights(computeError(image.answer == 2, sad), image);
            mischeiviousPerceptrons.adjustWeights(computeError(image.answer == 3, mis), image);
            madPerceptrons.adjustWeights(computeError(image.answer == 4, mad), image);
            return true;
        }
        else{
            happyPerceptrons.adjustWeights(computeError(image.answer == 1, happy), image);
            sadPerceptrons.adjustWeights(computeError(image.answer == 2, sad), image);
            mischeiviousPerceptrons.adjustWeights(computeError(image.answer == 3, mis), image);
            madPerceptrons.adjustWeights(computeError(image.answer == 4, mad), image);
        }

        return false;

    }

    public int getMood(double happy, double sad, double mis, double mad){
        if(Double.compare(happy, sad) > 0 && Double.compare(happy, mis) > 0 && Double.compare(happy, mad) > 0 ){
            return 1;
        }
        if(Double.compare(sad, happy) > 0 && Double.compare(sad, mis) > 0 && Double.compare(sad, mad) > 0 ){
            return 2;
        }
        if(Double.compare(mis, happy) > 0 && Double.compare(mis, sad) > 0 && Double.compare(mis, mad) > 0 ){
            return 3;
        }
        if(Double.compare(mad, sad) > 0 && Double.compare(mad, mis) > 0 && Double.compare(mad, happy) > 0 ){
            return 4;
        }
        return 7;
    }


    public boolean test(Image image){
        double happy = happyPerceptrons.guessMood(image);
        double sad = sadPerceptrons.guessMood(image);
        double mis = mischeiviousPerceptrons.guessMood(image);
        double mad = madPerceptrons.guessMood(image);

        int answer = getMood(happy, sad, mis, mad);
        System.out.println("Image" + image.ID + " " + answer + " was correct: " + image.answer);
        return answer==image.answer;

    }

}
