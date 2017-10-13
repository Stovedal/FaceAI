/**
 * Created by Linnea on 2017-10-04.
 */
public class PerceptronLayer {

    private Perceptron[][] perceptrons;

    public PerceptronLayer(){

        perceptrons = new Perceptron[20][20];

        for(int i = 0 ; i < 20 ; i++) {
            for (int j = 0; j < 20; j++) {
                perceptrons[i][j] = new Perceptron();
            }
        }
    }


    public int perceiveImage(Image image){

        int averageMood = 0;
        for(int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                averageMood = perceptrons[i][j].activationFunction(image.data[i][j]) + averageMood;
            }
        }
        return averageMood / (20*20);

    }

    public void train(int correctMood, Image image){
        for(int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                perceptrons[i][j].adjustWeights(correctMood-1, image.data[i][j]);
            }
        }
    }






}
