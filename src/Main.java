import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        while(nrOfCorrectAnswers < 100){
            nrOfCorrectAnswers = 0;
            double count = 0;
            double ones = 0;
            double twos = 0;
            double threes = 0;
            for(int i = 0; i< imageList.size(); i++){
                Image imageToPerceive = imageList.get(i);
                int guess = faceAI.perceiveImage(imageToPerceive);
                boolean isCorrect = imageToPerceive.check(guess);
                if(guess == 4){
                    count++;
                }
                if(guess == 1){
                    ones++;
                }
                if(guess == 2){
                    twos++;
                }
                if(guess == 3){
                    threes++;
                }
                if(!isCorrect){
                    faceAI.train(imageToPerceive);
                } else {
                    nrOfCorrectAnswers = nrOfCorrectAnswers + 1;
                }
            }
            if(Double.compare(nrOfCorrectAnswers, best)>0){
                best = nrOfCorrectAnswers;
            }

            System.out.println("Nr of correct Answers " + nrOfCorrectAnswers/200);

            Collections.shuffle(imageList);
        }

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
            System.out.println(scanner.nextLine());
        }
        while(null==scanner.findInLine("I")){
            System.out.println(scanner.nextLine());
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




