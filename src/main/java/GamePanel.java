import javax.swing.*;
import java.awt.*;

/**
 * Created by Boat on 3/21/2017 AD.
 */
public class GamePanel extends JPanel implements Runnable {

    private Game game;

    public GamePanel(){
        game = new Game();
        //Start the thread
        new Thread(this).start();
    }

    public void update(){
        game.update();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        for (Render r: game.getRenders()){
            ((Graphics2D) g).drawImage(r.image,r.x,r.y,null);
        }
        if(game.started){
            g2D.drawString(Integer.toString(game.getScore()),150,240);
        }


        //g2D.setColor(Color.RED);

    }

    @Override
    public void run() {
        try{
            while(true){
                update();
                //Very crude method to control the refresh rate
                Thread.sleep(30);



            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
