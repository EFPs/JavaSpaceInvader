/**
 * Created by Boat on 3/28/2017 AD.
 */
 abstract class GameObject {

     abstract Render getRender();


    /**
     * update the location
     */
    abstract void update();

    abstract int getX();
    abstract int getY();
    abstract int getWidth();
    abstract int getHeight();

    /**
     * Check if this object is collided with the obj a or not.
     * @param a
     * @return true if collide else, false.
     */
    abstract boolean isCollided(GameObject a);








}
