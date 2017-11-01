/**
 * Created by Linnea on 2017-10-04.
 */
public class Brain {

    private Perceptron happyPerceptron;
    private Perceptron sadPerceptron;
    private Perceptron mischeiviousPerceptron;
    private Perceptron madPerceptron;

    /**
     * A Brain holds 4 perceptrons for each of the moods happy,
     * sad, mischevious and mad. If properly trained it can
     * detect these moods to a degree.
     */
    public Brain(){
        happyPerceptron = new Perceptron();
        sadPerceptron = new Perceptron();
        mischeiviousPerceptron = new Perceptron();
        madPerceptron = new Perceptron();
    }

    /**
     * Trains the ai on the given image.
     * @param image Image
     */
    public void train(Image image){
        double happy = happyPerceptron.guessMood(image);
        double sad = sadPerceptron.guessMood(image);
        double mis = mischeiviousPerceptron.guessMood(image);
        double mad = madPerceptron.guessMood(image);
        happyPerceptron.adjustWeights(computeError(image.answer == 1, happy), image);
        sadPerceptron.adjustWeights(computeError(image.answer == 2, sad), image);
        mischeiviousPerceptron.adjustWeights(computeError(image.answer == 3, mis), image);
        madPerceptron.adjustWeights(computeError(image.answer == 4, mad), image);
    }


    /**
     * Guesses the mood of an image
     * @param image Image
     * @return String
     */
    public String test(Image image){
        double happy = happyPerceptron.guessMood(image);
        double sad = sadPerceptron.guessMood(image);
        double mis = mischeiviousPerceptron.guessMood(image);
        double mad = madPerceptron.guessMood(image);
        System.out.println("Image" + image.ID + " " + getMood(happy, sad, mis, mad));
        return "Image" + image.ID + " " + getMood(happy, sad, mis, mad);
    }

    /**
     * Guesses the mood of an image
     * @param image Image
     * @return String
     */
    public boolean testPerformance(Image image){
        double happy = happyPerceptron.guessMood(image);
        double sad = sadPerceptron.guessMood(image);
        double mis = mischeiviousPerceptron.guessMood(image);
        double mad = madPerceptron.guessMood(image);
        return getMood(happy, sad, mis, mad)==image.answer;
    }

    /**
     * Computes the error between guess and answer of a perceptron
     * @param answer boolean
     * @param guess double
     * @return double
     */
    public double computeError(boolean answer, double guess){
        if (answer){
            return 1 - guess;
        }
        else{
            return 0 - guess;
        }
    }

    /**
     * Guesses the mood of an image by returning the mood of the
     * perceptron which had the highest activation
     * @param happy double
     * @param sad double
     * @param mis double
     * @param mad double
     * @return int
     */
    public int getMood(double happy, double sad, double mis, double mad){
        if(
            Double.compare(happy, sad) >= 0 &&
            Double.compare(happy, mis) >= 0 &&
            Double.compare(happy, mad) >= 0
                ){
            return 1;
        }
        if(
            Double.compare(sad, happy) >= 0 &&
            Double.compare(sad, mis) >= 0 &&
            Double.compare(sad, mad) >= 0
                ){
            return 2;
        }
        if(
            Double.compare(mis, happy) >= 0 &&
            Double.compare(mis, sad) >= 0 &&
            Double.compare(mis, mad) >= 0
                ){
            return 3;
        }
        if(
            Double.compare(mad, happy) >= 0 &&
            Double.compare(mad, sad) >= 0 &&
            Double.compare(mad, mis) >= 0
                ){
            return 4;
        }
        return 0;
    }

}
