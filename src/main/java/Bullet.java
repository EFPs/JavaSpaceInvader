import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Boat on 3/28/2017 AD.
 */
public class Bullet extends GameObject {


    boolean dead;
    int x;
    int y;
    int width;
    int height;
    int yVel;
    int friction;
    private Keyboard keyboard;
    public BufferedImage image;

    public Bullet(int xPlayer,int yPlayer){
        dead =false;
        x = xPlayer;
        y= yPlayer;
        width = 10;
        height = 10;
        yVel = 0;
        friction = 1;

        //Get the same keyboard every time(Singlton pattern)
        keyboard = Keyboard.getInstance();
    }
    @Override
    public int getX(){
        return x;
    }
    @Override
    public int getY(){
        return y;
    }
    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public int getHeight(){
        return height;
    }

    boolean isDead(){
        return dead;
    }


    public void update(){

        yVel = -7;
        y += (int)yVel;
    }

    void setDead(){
        dead = true;
    }

    @Override
    boolean isCollided(GameObject a) {
        int x = a.getX();
        int y = a.getY();
        int width = a.getWidth();
        int height = a.getHeight();
        int bulletWidth = x+width;
        int bulletHeight = y-height;

        int enemyHeight = this.y+this.height;
        int enemyWidth = this.x+this.width;

        if(bulletHeight < enemyHeight && bulletHeight >= this.y){
            if(bulletWidth < enemyWidth && bulletWidth >= this.x){
                dead = true;
                return true;
            }
        }


        return false;
    }

    public Render getRender(){
        Render r = new Render();
        r.x = x;
        r.y = y;
        if(image == null){

            image = Util.loadImage("/bullet2.png");

        }
        r.image = image;
        return r;
    }


}
