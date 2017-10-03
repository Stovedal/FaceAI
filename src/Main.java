import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Sofia on 2017-10-03.
 */
public class Main {

    public static void main(String[] args ){

        Scanner scanner= makeScanner("C:\\Users\\Linnea\\IdeaProjects\\FaceAI\\testImages\\training-A.txt");

        printComments(scanner);

        double[][] firstImg = readImage(scanner);

        for(int i = 0; i <20; i++){
            String line = "";
            for (int j = 0; j <20; j++){
                line = line + Double.toString(firstImg[i][j]) + " ";
            }
            System.out.println(line);

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
        try{

            while(scanner.hasNext("#")){
                System.out.println(scanner.nextLine());
            }

        } catch (Exception e){}

    }

    public static double[][] readImage(Scanner scanner) {

        double[][] image = new double[20][20];
        try {
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    image[i][j] = (double)scanner.nextDouble()/32;

                }
            }
        } catch (Exception e) {
        }

        return image;

    }

}
