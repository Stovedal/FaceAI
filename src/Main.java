import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Sofia on 2017-10-03.
 */
public class Main {

    public static void main(String[] args ){

        String path = "/Users/sofiatovedal/IdeaProjects/FaceAI/testImages/";

        Scanner scanner= makeScanner(path + "training-A.txt");

        Scanner answersScanner= makeScanner(path + "facit-A.txt");

        PrintWriter writer = new PrintWriter("testAnswer.txt");
        Brain faceAI = new Brain();

        printComments(scanner);
        printComments(answersScanner);
        double nrOfCorrectAnswers = 0;
        ArrayList<Image> imageList = getImageList(scanner, 200, answersScanner);
        double best = 0;
        double perceivedImages = 0;
        double time = System.currentTimeMillis();
        int c = 0;
        while(c<300){
            c++;
            nrOfCorrectAnswers = 0;

            for(int i = 0; i< imageList.size(); i++){
                Image imageToPerceive = imageList.get(i);
                boolean isCorrect = faceAI.train(imageToPerceive);
                if(isCorrect){
                    nrOfCorrectAnswers++;
                }
            }
            System.out.println( nrOfCorrectAnswers / 200 );

            if(Double.compare(nrOfCorrectAnswers, best+0.05)>0){
                best = nrOfCorrectAnswers;
                System.out.println(best/200);
            }

            perceivedImages = perceivedImages + imageList.size();

            Collections.shuffle(imageList);
        }

        Scanner scannerTest = makeScanner(path + "test-B.txt");

        Scanner answersScannerTest = makeScanner(path + "facit-B.txt");

        printComments(scannerTest);
        printComments(answersScannerTest);

        ArrayList<Image> testList = getImageList(scannerTest, 100, answersScannerTest);

        nrOfCorrectAnswers = 0;
        for(int i = 0; i < testList.size(); i++){
            writer.println(faceAI.test(testList.get(i)));
        }
        time = System.currentTimeMillis()-time;
        System.out.println("Test complete with a success-rate of " + nrOfCorrectAnswers/100);
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
                    data[i][j] = new Double(scanner.next())/32;
                }
            }
            scanner.nextLine();
            scanner.nextLine();
        } catch (Exception e) {
        }
        return new Image(data, new Integer(answerLine[0]), new Integer(answerLine[1]));
    }

    public static Image readImage(Scanner scanner) {

        double[][] data = new double[20][20];
        String[] id = scanner.nextLine().split(" ");
        id[0] = id[0].replaceAll("\\D+","");
        try {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    data[i][j] = new Double(scanner.next())/32;
                }
            }
            scanner.nextLine();
            scanner.nextLine();
        } catch (Exception e) {
        }
        return new Image(data, new Integer(id[0]));
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

}




