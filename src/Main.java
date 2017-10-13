import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Sofia on 2017-10-03.
 */
public class Main {

    public static void main(String[] args ){

        Scanner scanner= makeScanner("C:\\Users\\Linnea\\IdeaProjects\\FaceAI\\testImages\\training-A.txt");

        Scanner answersScanner= makeScanner("C:\\Users\\Linnea\\IdeaProjects\\FaceAI\\testImages\\facit-A.txt");

        PerceptronLayer faceAI = new PerceptronLayer();


        printComments(scanner);
        printComments(answersScanner);

        int correctAnswers = 0;
        ArrayList<Answer> answersList = getAnswerList(answersScanner, 200);
        ArrayList<Image> imageList = getImageList(scanner, 200);
        for(int i = 0; i< imageList.size(); i++){
            int ans = faceAI.perceiveImage(imageList.get(i));
            boolean correct = answersList.get(i).check(ans, imageList.get(i).ID);
            if(!correct){
                faceAI.train(answersList.get(i).getMood(), imageList.get(i));
            } else {
                correctAnswers = correctAnswers + 1;
            }
        }
        System.out.println("Nr of correctAnswers " + new Double(correctAnswers)/200);

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

        }catch (FileNotFoundException e){
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

    public static Image readImage(Scanner scanner) {

        double[][] image = new double[20][20];
        int id = 1;
        try {
            id = new Integer(scanner.nextLine().replaceAll("\\D+",""));
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    image[i][j] = (double)scanner.nextDouble()/32;
                }
            }
            scanner.nextLine();
            scanner.nextLine();
        } catch (Exception e) {
        }
        return new Image(image, id);
    }

    public static ArrayList getImageList(Scanner scanner, int number){
        ArrayList imageList = new ArrayList();
        for (int i = 0; i < number; i++){
            if(scanner.hasNext()){
                imageList.add(readImage(scanner));
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




