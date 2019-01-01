import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Boat on 3/23/2017 AD.
 */
public class Keyboard implements KeyListener {

    private static Keyboard instance ;
    private boolean[] keys;
    //private Microphone microphone;


    private Keyboard(){
        keys = new boolean[256];
       // microphone = new Microphone();
    }

    public static Keyboard getInstance(){

        //To create instance only on the first time calling it.
        if(instance == null){
            instance = new Keyboard();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        //If key is press, true
        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length){
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //if key not press, false
        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length){
            keys[e.getKeyCode()] = false;
        }
    }

    public boolean isDown(int key){

        if(key == KeyEvent.VK_SPACE){
            return true;
        }

        if(key >= 0 && key < keys.length){
            return keys[key];
        }
        return false;
    }


}
