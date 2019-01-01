import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by Boat on 3/28/2017 AD.
 */
public class Game {

    public static int EDGE_RIGHT = 500;
    public static int EDGE_DOWN = 500;

    private int score = 0;

    private List<Enemy> enemyList = new ArrayList<>();
    private List<Bullet> bulletList = new ArrayList<>();
    private List<BulletEnemy> bulletEnemyList = new ArrayList<>();
    private List<Hitmark> hitmarkList = new ArrayList<>();
    private List<Points> pointsList = new ArrayList<>();

    private Player player;
    public boolean started = false;
    private Keyboard keyboard;
    public boolean gameOver;



    public Game(){
        gameOver = false;
        player = new Player(this);
        for(int i =0 ; i<5 ; i++){
            if(i < 3){enemyList.add(new Enemy(5,this));}
            enemyList.add(new Enemy(80,this));
        }
        keyboard = Keyboard.getInstance();
    }

    public int getScore(){
        return score;
    }

    public void update(){
        watchForStart();
        if(!started){
            return;
        }

        List<GameObject> deleteObj = new ArrayList<>();
        List<Enemy> deleteEnemy = new ArrayList<>();
        List<Bullet> deleteBullet = new ArrayList<>();
        List<Hitmark>deleteHitmark = new ArrayList<>();
        List<BulletEnemy>deleteBulletEnemy = new ArrayList<>();
        List<Points> deletePoints = new ArrayList<>();
        //Check if any collide with anything
        for(Enemy each : enemyList){
            for(Bullet eachBullet : bulletList){
                if(each.isCollided(eachBullet)){
                    int x = eachBullet.getX();
                    int y = eachBullet.getY();
                    pointsList.add(new Points(x,y));
                    hitmarkList.add(new Hitmark(x,y));
                    score +=100;
                    eachBullet.setDead();
                }
            }
        }

        //Select dead enemy
        for(Enemy each : enemyList){
            if(each.isDead()){
                deleteEnemy.add(each);
                continue;
            }
            each.update();
        }

        //Select if player got hit
        for(BulletEnemy each : bulletEnemyList){
            if(each.isDead()){
                deleteBulletEnemy.add(each);
                System.out.println(bulletEnemyList.size());
                //continue;
            }
            if(each.isCollided(player)){
                each.setDead();
                player.getHit();
                hitmarkList.add(new Hitmark(each.getX(),each.getY()));
                deleteBulletEnemy.add(each);
               // System.out.println("HITTTTTT");

            }
            each.update();
        }
        if(player.playerDead()){
            gameOver = true;
        }else {
            player.update();
        }


        //Run through all the bullet
        for(Bullet each : bulletList){

            if(each.isDead()){
                deleteBullet.add(each);
            }
            if(each.getY() <=0){
                deleteBullet.add(each);
            }
            each.update();
        }

        for(Hitmark each : hitmarkList){
            if(each.isDead()){
                deleteHitmark.add(each);
            }else {
                each.update();
            }

        }

        for(Points each : pointsList){
            if(each.isDead()){
                deletePoints.add(each);
            }else {
                each.update();
            }

        }
        //Delete hitmark
        for(Hitmark each : deleteHitmark){
            hitmarkList.remove(each);
        }

        for(Points each : deletePoints){
            pointsList.remove(each);
        }
        //Delete enemy bullet
        for(BulletEnemy each : deleteBulletEnemy){
            bulletEnemyList.remove(each);
        }

        //Delete bullet
        for(Bullet each : deleteBullet){
            bulletList.remove(each);
        }
        //Delete dead enemy
        for(Enemy each : deleteEnemy){
            enemyList.remove(each);
        }

    }

    private void watchForStart(){
        if(!started && keyboard.isDown(KeyEvent.VK_S)){
            started = true;

        }
    }

    void createBulletEnemy(int x,int y){
        if(bulletEnemyList.size() >= 10){
            return;
        }
        bulletEnemyList.add(new BulletEnemy(x,y));
    }

    void createBullet(int x,int y){
        if(bulletList.size() >= 5){
            return;
        }
        bulletList.add(new Bullet(x,y));
    }





/*
    private void checkForCondition(){
        for(Enemy each: enemyList){
            if(each.collided(cat.x,cat.y,cat.width,cat.height)){
                gameOver = true;
        }
    }*/

    //Return things that need to be drawn to screen
    public ArrayList<Render> getRenders(){
        ArrayList<Render> renders = new ArrayList<>();
        if(!started){
            renders.add(new Render(0,0,"/background.png"));
            renders.add(new Render(-5,10,"/menu.png"));
            renders.add(new Render(200,300,"/nyan.gif"));

            return renders;
        }



        renders.add(new Render(0,0,"/background.png"));
        for(Enemy enemy : enemyList){
            renders.add(enemy.getRender());
        }
        renders.add(player.getRender());
        for(Bullet each : bulletList){
            renders.add(each.getRender());
        }
        for(Hitmark each : hitmarkList){
            renders.add(each.getRender());
        }
        for(Points each : pointsList){
            renders.add(each.getRender());
        }
        for(BulletEnemy each : bulletEnemyList){
            renders.add(each.getRender());
        }
        return renders;
    }
}



