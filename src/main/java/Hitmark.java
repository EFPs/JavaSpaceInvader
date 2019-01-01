import java.awt.image.BufferedImage;

/**
 * Created by Boat on 3/29/2017 AD.
 */
public class Hitmark {
    public BufferedImage image;
    int x;
    int y;
    int delay;
    boolean dead;

    Hitmark(int x,int y){
        this.x =x;
        this.y = y;
        dead = false;
        delay = 5;
    }

    Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;
        if(image == null){
                image = Util.loadImage("/hitmark.png");

        }
        r.image = image;
        return r;
    }

    boolean isDead(){
        return dead;
    }

    void update() {
        delay--;
        if(delay <=0){
            dead =true;
        }

    }

}
