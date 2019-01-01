import java.awt.*;

/**
 * Created by Boat on 3/21/2017 AD.
 */
public class Render {

    public int x;
    public int y;
    public Image image;

    public Render(){

    }


    Render(int x, int y, String imagePath){

        this.x = x;
        this.y = y;
        this.image = Util.loadImage(imagePath);

    }

}
