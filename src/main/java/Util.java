import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Boat on 3/21/2017 AD.
 */
public class Util {

    private static HashMap<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage loadImage(String path){
        BufferedImage image = null;

        if(cache.containsKey(path)){
            return cache.get(path);
        }
        try {


            System.out.println(path);
            //Read file form resource folder
            image = ImageIO.read(ClassLoader.class.getResourceAsStream(path));


            if(! cache.containsKey(path)){
                cache.put(path,image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
