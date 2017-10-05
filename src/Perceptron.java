/**
 * Created by Linnea on 2017-10-04.
 */
public class Perceptron {

    private double[] moodValues = {Math.random(), Math.random(), Math.random() ,Math.random()};

    public Perceptron(){

    }

    public int activationFunction(double input){
        double happyDiff = Math.abs(moodValues[0]-input);
        double sadDiff = Math.abs(moodValues[1]-input);
        double mischievousDiff = Math.abs(moodValues[2]-input);
        double madDiff = Math.abs(moodValues[3]-input);

        if(Double.compare(happyDiff, sadDiff) < 0){

            if(Double.compare(mischievousDiff, madDiff) < 0){
                if(Double.compare(happyDiff,mischievousDiff) < 0){
                    //Return happy
                    return 0;
                }
                else{
                    //Return mischievous
                    return 2;
                }
            }
            else{
                if(Double.compare(happyDiff,madDiff) < 0){
                    //Return happy
                    return 0;
                }
                else{
                    //Return mad
                    return 3;
                }

            }
            if(Double.compare(mischievousDiff, madDiff) < 0){
                if(Double.compare(happyDiff,mischievousDiff) < 0){
                    //Return happy
                    return 0;
                }
                else{
                    //Return mischievous
                    return 2;
                }
            }
            else{
                if(Double.compare(happyDiff,madDiff) < 0){
                    //Return happy
                    return 0;
                }
                else{
                    //Return mad
                    return 3;
                }

            }

        }
    }

}
