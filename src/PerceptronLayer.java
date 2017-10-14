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

        int happy = 0;
        int sad = 0;
        int mis = 0;
        int mad = 0;
        double mood;
        for(int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                mood = perceptrons[i][j].guessMood(getInputsForPerceptron(perceptrons[i][j], image));
                if(mood == 1){
                    happy++;
                }
                if( mood == 2){
                    sad++;
                }
                if( mood == 3 ) {
                    mis++;
                }
                if( mood == 4 ) {
                    mad++;
                }
            }
        }
        if(happy >= sad && happy >= mad && happy >= mis){ return 1; }
        if(sad >= happy && sad >= mad && sad >= mis){ return 2; }
        if(mis >= sad && mis >= mad && mis >= happy){ return 3; }
        if(mad >= sad && mad >= happy && mad >= mis){ return 4; }

        return -1;
    }

    public void train(Image image){
        for(int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                perceptrons[i][j].adjustWeights(image.answer, getInputsForPerceptron(perceptrons[i][j], image));
            }
        }
    }


}
