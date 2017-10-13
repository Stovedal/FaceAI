/**
 * Created by Linnea on 2017-10-04.
 */
public class PerceptronLayer {

    private Perceptron[][] perceptrons;

    public PerceptronLayer(){

        perceptrons = new Perceptron[20][20];

        for(int i = 0 ; i < 20 ; i++) {
            for (int j = 0; j < 20; j++) {
                perceptrons[i][j] = new Perceptron(i, j);
            }
        }
    }



    public double[] getInputsForPerceptron(Perceptron p, Image image){
        double[] inputs = new double[p.nrOfInputs];
        int index =0;
        for(int i = p.i - 1; i < p.i + 1; i++){
            for(int j = p.j - 1; j < p.j + 1; j++){
                if(i > -1 && i < 20 && j > -1 && j < 20){

                    inputs[index] = image.data[i][j];
                }
            }
        }
        return inputs;
    }


    public int perceiveImage(Image image){

        double averageMood = 0;
        for(int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                averageMood = perceptrons[i][j].guessMood(getInputsForPerceptron(perceptrons[i][j], image)) + averageMood;
                //System.out.println("hej" + perceptrons[i][j].guessMood(getInputsForPerceptron(perceptrons[i][j], image)));
            }
        }
        return new Integer((int)Math.round(averageMood/400));

    }

    public void train(int correctMood, Image image){
        for(int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                perceptrons[i][j].adjustWeights(correctMood-1, getInputsForPerceptron(perceptrons[i][j], image));
            }
        }
    }






}
