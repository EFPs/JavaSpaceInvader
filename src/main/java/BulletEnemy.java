import java.awt.image.BufferedImage;

/**
 * Created by Boat on 3/29/2017 AD.
 */
public class BulletEnemy extends GameObject {

    boolean dead;
    int x;
    int y;
    int width;
    int height;
    int yVel;
    int friction;


    public BufferedImage image;

    public BulletEnemy(int xPlayer,int yPlayer){
        dead =false;
        x = xPlayer;
        y= yPlayer;
        width = 10;
        height = 10;
        yVel = 0;
        friction = 1;

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
        if(y > 500){
            setDead();
            return;
        }
        yVel = 7;
        y += yVel;

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
        int enemyWidth = x+width;
        int enemyHeight = y-height;

        int bulletHeight = this.y+this.height;
        int bulletWidth = this.x+this.width;

        if(this.y <= y && this.y > enemyHeight){
            if(this.x >= x && this.x < enemyWidth){
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
            image = Util.loadImage("/bull.png");
        }
        r.image = image;
        return r;
    }


}