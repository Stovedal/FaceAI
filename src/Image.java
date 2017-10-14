/**
 * Created by Linnea on 2017-10-03.
 */
public class Image {

    public double[][] data;
    public int ID;
    public int answer = 0;

    public Image(double[][] data, int id, int answer){
        this.data = data;
        this.ID = id;
        this.answer = answer;
    }

    public boolean check(int perceivedMood){
        return perceivedMood == answer;
    }
}
