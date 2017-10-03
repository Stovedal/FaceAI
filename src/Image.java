/**
 * Created by Linnea on 2017-10-03.
 */
public class Image {

    public double[][] data;

    public Image(double[][] data){
        this.data = data;

    }

    public double[][] flipImage(double[][] image){
        double top = 0;
        double bottom = 0;
        double left = 0;
        double right = 0;

        for(int i=0; i < 10; i ++){
            for(int j=0; j < 20; j ++){
                top = top + image[i][j];
            }
        }

        for(int i=10; i < 20; i ++){
            for(int j=0; j < 10; j ++){
                bottom = bottom + image[i][j];
            }
        }

        for(int i=0; i < 20; i ++){
            for(int j=0; j < 10; j ++){
                left = left + image[i][j];
            }
        }

        for(int i=0; i < 200; i ++){
            for(int j=10; j < 20; j ++){
                right = right + image[i][j];
            }
        }


        if(Double.compare(top, bottom) > 0){
            if(Double.compare(left, right)> 0){
                if(Double.compare(top, left)>0){
                    return image;
                }
                else {
                    ////left is largest, rotate right
                }

            }
            else {
                if(Double.compare(top, right)>0){
                    return image;
                }
                else {
                    ////left is largest, rotate left
                }

            }
        }
        else{
            if(Double.compare(left, right)> 0){
                if(Double.compare(bottom, left)>0){
                    //do nothing, bottom is biggest
                }
                else {
                    ////left is largest, rotate right
                }

            }
            else {
                if(Double.compare(bottom, right)>0){
                    //do nothing, bottom is biggest
                }
                else {
                    ////left is largest, rotate left
                }

            }

        }

    }

    public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public double[][] reverseColumns(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[i][m[0].length-j] = m[i][j];
        return temp;
    }

    public double[][] reverseRows(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[m.length-i][j] = m[i][j];
        return temp;
    }


}
