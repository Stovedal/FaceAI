/**
 * Created by Linnea on 2017-10-03.
 */
public class Image {

    public double[][] data;
    public int ID;
    public int answer = 0;

    /**
     * Image object containing 20*20 pixels and
     * the mood of said image.
     * @param data double[][]
     * @param id int
     * @param answer int
     */
    public Image(double[][] data, int id, int answer){
        this.data = data;
        this.ID = id;
        this.answer = answer;
    }

    /**
     * Image object containing 20*20 pixels.
     * @param data double][]
     * @param id int
     */
    public Image(double[][] data, int id){
        this.data = data;
        this.ID = id;
    }

}
