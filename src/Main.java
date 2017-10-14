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
        double correctAnswers = 0;
        ArrayList<Image> imageList = getImageList(scanner, 200, answersScanner);
        while(correctAnswers < 100){
            correctAnswers = 0;
            double count = 0;
            double ones = 0;
            double twos = 0;
            double threes = 0;
            for(int i = 0; i< imageList.size(); i++){
                int ans = faceAI.perceiveImage(imageList.get(i));
                boolean correct = imageList.get(i).check(ans);
                if(ans == 4){
                    count++;
                }
                if(ans == 1){
                    ones++;
                }
                if(ans == 2){
                    twos++;
                }
                if(ans == 3){
                    threes++;
                }
                if(!correct){
                    faceAI.train(imageList.get(i).answer, imageList.get(i));
                } else {
                    correctAnswers = correctAnswers + 1;
                }
            }
            System.out.println("Nr of correctAnswers " + correctAnswers/200 + " fours " + count/200 + " ones " + ones/200 + " twos " + twos/200 + " threes " + threes/200);
            System.out.println((count+ones+twos+threes));

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




