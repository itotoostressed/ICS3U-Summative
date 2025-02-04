import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class Powerup extends JFrame {
    JPanel powerupImage;
    Random  rand = new Random();
    int xPos = rand.nextInt(1200) + 200, yPos = 0;
    public Powerup(JFrame j) {
        powerupImage = new JPanel();
        powerupImage.setBounds(xPos, yPos, 50, 50);
        powerupImage.setBackground(Color.WHITE);
        powerupImage.setVisible(true);
        j.add(powerupImage);
    }
    public void animate() {
        yPos += 25;
        powerupImage.setBounds(xPos,yPos, 50, 50);
    }
    public boolean hitScan(int X, int Y, int W, int H) { //passing in value is the moving character object
        int LocalCX = xPos+25;//local center X of the obstacles
        int LocalCY = yPos+25;
        int xDist = Math.abs(X- LocalCX); //calculating the distance between both objects center
        int yDist = Math.abs(Y- LocalCY);

        if ((25 + W)/2 >= xDist && (25 + H)/2 >= yDist) { //adding half of both object hitboxes so that we can compare the distance between the two, when smaller, return true as in hit.
            return true;
        }
        else if(yPos >= 1000) {
            return true;
        }
        return false; //did not hit
    }
    public void resetPos() {
        xPos = rand.nextInt(1200) + 200;
        yPos = 0;
    }
}
