import javax.swing.*;
import java.awt.*;

public class Projectile extends JPanel {
    JPanel bullet;
    int yPos = 850, xPos;
    boolean hitTarget = false; //if this bullet hits the alien
    public Projectile() {
        bullet = new JPanel();
        bullet.setBackground(Color.green);
        bullet.setVisible(true);
    }
    public void fire() { //handles animation of bullet
        yPos -= 20;
        bullet.setBounds(xPos, yPos, 6, 10);
    }
    public void addToJFrame(JFrame j) { //adds bullet to Jframe
        j.add(bullet);
    }
    public void updatePosition(int x) { //updates current position depending on where the ship is
        xPos = x;
        bullet.setBounds(x, yPos, 6, 10);
    }
    public boolean hitScan(int X, int Y, int W, int H) { //passing in value is the moving character object
        int LocalCX = xPos+3;//local center X of the obstacles
        int LocalCY = yPos+5;
        int xDist = Math.abs(X- LocalCX); //calculating the distance between both objects center
        int yDist = Math.abs(Y- LocalCY);

        if ((5 + W)/2 >= xDist && (10 + H)/2 >= yDist) { //adding half of both object hitboxes so that we can compare the distance between the two, when smaller, return true as in hit.
            return true;
        }
        return false; //did not hit
    }
    public void setInvis() { //sets the bullet inviisble
        bullet.setVisible(false);
        hitTarget = true;
    }
    public boolean checkHitTarget() { //returns hitTarget value
        return hitTarget;
    }
}
