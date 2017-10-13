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
    public void addAns(Answer a){
        this.answer = a.getMood();
    }

    public boolean check(int perceivedMood, int ID){
        try {
            if (ID == this.ID) {
                if (perceivedMood == answer) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new Exception("Incorrect image ID");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
