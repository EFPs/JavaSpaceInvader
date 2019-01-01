import javax.swing.*;

/**
 * Created by Boat on 3/21/2017 AD.
 */
public class App {

    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    public static void main (String[] args){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Program that get close its not gonna kill your operation(Not in this case)
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);


        GamePanel panel = new GamePanel();
        frame.add(panel);

        frame.validate();
    }

}
