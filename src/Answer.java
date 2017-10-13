/**
 * Created by Linnea on 2017-10-06.
 */
public class Answer {

    public int ID;
    public int mood;

    public Answer(String answer){

        String[] data = answer.split(" ");
        data[0] = data[0].replaceAll("\\D+","");
        this.ID = new Integer(data[0]);
        this.mood = new Integer(data[1]);


    }

    public boolean check(int perceivedMood, int ID){
        try {
            if (ID == this.ID) {
                if (perceivedMood == mood) {
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

    public int getMood(){
        return mood;
    }


}
