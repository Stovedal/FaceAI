import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Sofia on 2017-10-03.
 */
public class Faces {

    public static void main(String[] args ) throws Exception {

        //Create image-lists and Ai
        PrintWriter writer = new PrintWriter("result.txt");
        Brain brain = new Brain();
        Scanner scanner= makeScanner(args[0]);
        Scanner correctMoodsScanner= makeScanner(args[1]);
        Scanner testScanner = makeScanner(args[2]);
        ArrayList<Image> trainingList = getImageList(scanner, correctMoodsScanner);
        ArrayList<Image> testList = getImageList(testScanner);
        ArrayList<Image> performanceTestList = new ArrayList<>();

        //Split the trainingList into training and performanceTesting lists
        //(ratio 70:30)
        double sizeOfTrainingSet = trainingList.size();
        for(int i = 0; i < (int)Math.round(sizeOfTrainingSet/3) ; i++ ){
            performanceTestList.add(trainingList.remove(i));
        }

        //Train until achieving 70% accuracy
        double accuracy = 0;
        while(Double.compare(accuracy, 0.7)<0){
            train(trainingList, brain);
            accuracy = testPerformance(performanceTestList, brain);
        }

        //Perform
        performFaceRecognition(testList, brain, writer);

    }

    /**
     * Makes ai perceive all images of given list and write its id and guessed mood to a file
     * using given PrintWriter.
     * @param imageList ArrayList
     * @param brain Brain
     * @param writer PrintWriter
     */
    private static void performFaceRecognition( ArrayList<Image> imageList, Brain brain, PrintWriter writer ){
        for(int i = 0; i < imageList.size(); i++){
            writer.println(brain.test(imageList.get(i)));
        }
        writer.close();
    }

    /**
     * Trains on given imageList once;
     * @param imageList ArrayList
     * @param brain Brain
     */
    private static void train( ArrayList<Image> imageList , Brain brain){
        for(int i = 0; i<imageList.size(); i++){
            brain.train(imageList.get(i));
        }
        Collections.shuffle(imageList);
    }

    /**
     * Tests the performace of the ai on a imageList and returns percentage of correctness
     * (0-1)
     * @param imageList ArrayList
     * @param brain Brain
     * @return double
     */
    private static double testPerformance(ArrayList<Image> imageList, Brain brain){
        double nrOfCorrectAns = 0;
        for(int i = 0; i < imageList.size(); i++){
            if(brain.testTraining(imageList.get(i))){
                nrOfCorrectAns++;
            }
        }
        return nrOfCorrectAns/imageList.size();
    }

    /**
     * Makes a Filereader, reads the file and returns a scanner.
     * @param path String
     * @return scanner
     */
    private static Scanner makeScanner(String path){
        try{
            FileReader fr = new FileReader(new File(path));
            return new Scanner(fr);

        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return null;
    }

    /**
     * Method intended to skip the top-most comments of standard
     * image and facit-files if there are any.
     * @param scanner Scanner
     */
    private static void skipComments(Scanner scanner){
        while (scanner.hasNext("#") && scanner.hasNext()) {
            scanner.nextLine();
        }
        while(null==scanner.findInLine("I")){
            scanner.nextLine();
        }

    }

    /**
     * Reads and Image and and answer from scanners into an Image
     * object and returns it
     * @param scanner Scanner
     * @param answerScanner Scanner
     * @return Image
     */
    private static Image readImage(Scanner scanner, Scanner answerScanner) {
        double[][] data = new double[20][20];
        String[] answerLine = answerScanner.nextLine().split(" ");

        answerLine[0] = answerLine[0].replaceAll("\\D+","");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                data[i][j] = new Double(scanner.next())/32;
            }
        }
        scanner.nextLine();
        scanner.nextLine();
        return new Image(data, new Integer(answerLine[0]), new Integer(answerLine[1]));
    }

    /**
     * Reads and Image from scanner into an Image
     * object and returns it
     * @param scanner Scanner
     * @return Image
     */
    private static Image readImage(Scanner scanner) {

        double[][] data = new double[20][20];
        String[] id = scanner.nextLine().split(" ");
        id[0] = id[0].replaceAll("\\D+","");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                data[i][j] = new Double(scanner.next())/32;
            }
        }
        scanner.nextLine();
        scanner.nextLine();
        return new Image(data, new Integer(id[0]));
    }


    /**
     * Creates a list of image-objects containing images and 
     * moods when given a scanner for images and one for moods.
     * @param scanner Scanner
     * @param correctMoodScanner Scanner
     * @return ArrayList
     */
    private static ArrayList<Image> getImageList(Scanner scanner, Scanner correctMoodScanner){
        skipComments(scanner);
        skipComments(correctMoodScanner);
        ArrayList<Image> imageList = new ArrayList<>();
        while (null!=scanner.findInLine("mage")){
            imageList.add(readImage(scanner, correctMoodScanner));
        }
        return imageList;
    }

    /**
     * Creates a list of image-objects containing images when
     * given a scanner for images.
     * @param scanner Scanner
     * @return ArrayList
     */
    private static ArrayList<Image> getImageList(Scanner scanner){
        skipComments(scanner);
        ArrayList<Image> imageList = new ArrayList<>();
        while (null!=scanner.findInLine("mage")){
            imageList.add(readImage(scanner));
        }
        return imageList;
    }

}




