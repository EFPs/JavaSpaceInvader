import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Boat on 3/28/2017 AD.
 */
public class Player extends GameObject{

    Game game;
    int health;
    int fireDelay;
    int x;
    int y;
    int width;
    int height;
    int xVel;
    int friction;
    private Keyboard keyboard;
    public BufferedImage image;

    public Player(Game g){
        health = 100;
        game = g;
        x = 250;
        y=400;
        width = 45;
        height = 27;
        xVel = 0;
        friction = 1;
        //Get the same keyboard every time(Singlton pattern)
        keyboard = Keyboard.getInstance();
    }
    public void getHit(){
        health -= 10;
    }
    public boolean playerDead(){
        return health<=0;
    }

    public void update(){

        fireDelay --;

        if(xVel >0){
            xVel --;
        }
        if(xVel <0){
            xVel++;
        }


        if(keyboard.isDown(KeyEvent.VK_0) && fireDelay<0){
            System.out.println("FIRE");
            game.createBullet( x+20,y+10);
            fireDelay = 10;
        }

        //Press 9 move left
        if(keyboard.isDown(KeyEvent.VK_LEFT)){
            xVel = -10;
        }

        //Press 0 move right
        if(keyboard.isDown(KeyEvent.VK_RIGHT)){
            xVel = 10;
        }
        x += xVel;
        //xVel = 0;
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
