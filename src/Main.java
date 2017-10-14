import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Sofia on 2017-10-03.
 */
public class Main {

    public static void main(String[] args ){

        String path = "C:\\Users\\Sofia\\FaceAI\\testImages\\";

        Scanner scanner= makeScanner(path + "training-A.txt");

        Scanner answersScanner= makeScanner(path + "facit-A.txt");

        PerceptronLayer faceAI = new PerceptronLayer();

        printComments(scanner);
        printComments(answersScanner);
        double nrOfCorrectAnswers = 0;
        ArrayList<Image> imageList = getImageList(scanner, 200, answersScanner);
        double best = 0;
        double perceivedImages = 0;
        double time = System.currentTimeMillis();
        while(nrOfCorrectAnswers/200 < 0.65){
            nrOfCorrectAnswers = 0;
            for(int i = 0; i< imageList.size(); i++){
                Image imageToPerceive = imageList.get(i);
                int guess = faceAI.perceiveImage(imageToPerceive);
                boolean isCorrect = imageToPerceive.check(guess);
                if(!isCorrect){
                    faceAI.train(imageToPerceive);
                } else {
                    nrOfCorrectAnswers = nrOfCorrectAnswers + 1;
                }
            }
            if(Double.compare(nrOfCorrectAnswers, best+0.05)>0){
                best = nrOfCorrectAnswers;
                System.out.println(best/200);
            }

            perceivedImages = perceivedImages + imageList.size();

            Collections.shuffle(imageList);
        }
        time = System.currentTimeMillis()-time;
        System.out.println("Training complete with a success-rate of " + nrOfCorrectAnswers/2 +"% and " + perceivedImages + " images processed in " + time/1000 + " seconds");

        System.out.println("Initalizing test");

        Scanner testScanner= makeScanner(path + "test-B.txt");

        Scanner testAnswersScanner= makeScanner(path + "facit-B.txt");
        printComments(scanner);
        printComments(answersScanner);
        nrOfCorrectAnswers = 0;

        time = System.currentTimeMillis();
        ArrayList<Image> testImageList = getImageList(testScanner,100, testAnswersScanner);
        for(int i = 0; i< testImageList.size(); i++){
            Image imageToPerceive = testImageList.get(i);
            int guess = faceAI.perceiveImage(imageToPerceive);
            boolean isCorrect = imageToPerceive.check(guess);
            if(!isCorrect){
                faceAI.train(imageToPerceive);
            } else {
                nrOfCorrectAnswers = nrOfCorrectAnswers + 1;
            }
        }
        time = System.currentTimeMillis()-time;

        if(nrOfCorrectAnswers/100>0.65){
        System.out.println("Test finished successfully with a success-rate of " + nrOfCorrectAnswers/2 + "% in " + time/1000 + " seconds");}




    }

    /**
     * Makes a Filereader, reads the file and returns a scanner.
     * @param path
     * @return scanner
     */
    public static Scanner makeScanner(String path){
        try{
            FileReader fr = new FileReader(new File(path));
            return new Scanner(fr);

        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return null;
    }

    /**
     *
     * @param scanner
     */
    public static void printComments(Scanner scanner){
        while (scanner.hasNext("#") && scanner.hasNext()) {
            scanner.nextLine();
        }
        while(null==scanner.findInLine("I")){
            scanner.nextLine();
        }
    }



    public static Image readImage(Scanner scanner, Scanner answerScanner) {

        double[][] data = new double[20][20];
        String[] answerLine = answerScanner.nextLine().split(" ");
        answerLine[0] = answerLine[0].replaceAll("\\D+","");
        scanner.nextLine();
        try {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    data[i][j] = scanner.nextDouble()/32;
                }
            }
            scanner.nextLine();
            scanner.nextLine();
        } catch (Exception e) {
        }
        return new Image(data, new Integer(answerLine[0]), new Integer(answerLine[1]));
    }

    public static ArrayList getImageList(Scanner scanner, int number, Scanner answerScanner){
        ArrayList imageList = new ArrayList();
        for (int i = 0; i < number; i++){
            if(scanner.hasNext()){
                imageList.add(readImage(scanner, answerScanner));
            }
        }
        return imageList;
    }


    public static ArrayList<Answer> getAnswerList(Scanner scanner, int number){
        ArrayList<Answer> answerList = new ArrayList<Answer>();
        for (int i = 0; i < number; i++){
            if(scanner.hasNext()) {
                answerList.add(new Answer(scanner.nextLine()));
            }
        }
        return answerList;
    }



}




