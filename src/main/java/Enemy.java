import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Boat on 3/28/2017 AD.
 */
public class Enemy extends GameObject{

    boolean dead;
    int x;
    int y;
    int width;
    int height;
    int xVel;
    int friction;
    boolean left;
    int speed;
    int bulletDelay;
    int BULLET_DELAY;

    Game game;
    private Keyboard keyboard;
    public BufferedImage image;

    public Enemy(int y,Game game){
        BULLET_DELAY = getRandomNum(10) +5;
        bulletDelay = BULLET_DELAY;
        this.game = game;
        speed = getRandomNum(4) +1;
        x =getRandomNum(400) ;
        this.y= y;
        width = 45;
        height = 27;
        xVel = 0;
        friction = 1;
        dead = false;
        setDirection();
        //Get the same keyboard every time(Singlton pattern)
        keyboard = Keyboard.getInstance();
    }

    public int getRandomNum(int boundary){
        Random random = new Random();
        return random.nextInt(boundary);
    }

    private void setDirection(){
        int ranNum = getRandomNum(10);
        this.left = true;
        if(ranNum>5){
            this.left = false;
        }
    }

    boolean isDead(){
        return dead;
    }


    private void changeDirection(){
        if(left){
            left = false;
        }else {
            left = true;
        }
    }

    public void update(){
        bulletDelay --;
        if(bulletDelay <= 0){
            bulletDelay = BULLET_DELAY;
            game.createBulletEnemy(x,y);
        }

        if(x >=400 || x <= 0){
            changeDirection();
        }
        if(left){
            xVel = speed;
        }else {
            xVel = -speed;
        }


        //Hit edge condition here?

        x += (int)xVel;
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
            image = Util.loadImage("/spaceShip.png");
        }
        r.image = image;
        return r;
    }

}
