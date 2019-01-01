import java.awt.image.BufferedImage;

/**
 * Created by Boat on 3/29/2017 AD.
 */
public class Points {public BufferedImage image;
    int x;
    int y;
    int delay;
    boolean dead;

    Points(int x,int y){
        this.x =x;
        this.y = y;
        dead = false;
        delay = 9;
    }

    Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;
        if(image == null){
            image = Util.loadImage("/point2.png");

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
        y--;

    }

}
