import javax.swing.*; //importing utilities
import java.awt.*;


public class Aliens extends JFrame {
    JPanel alien; //GUI setup
    ImageIcon alienIMG = new ImageIcon("space invaders alien.png");
    JLabel alienImage;
    int xPos, yPos;
    boolean amHit = false; //tracks if the alien is hit or not

    public Aliens(int x, int y) { //constructor for aliens
        xPos = x;
        yPos = y;

        alien = new JPanel();
        alien.setBounds(xPos, yPos, 50, 50);
        alien.setVisible(true);
        alien.setBackground(Color.BLACK);

        alienImage = new JLabel(alienIMG);
        alienImage.setVisible(true);
        alien.add(alienImage);
    }

    public void animation(int incrementX) { //animations for aliens
        xPos -= incrementX;
        alien.setBounds(xPos, yPos, 50, 50);
    }

    public void moveDown(int incrementY) { //moves alien down
        yPos += incrementY;
        alien.setBounds(xPos, yPos, 50, 50);
    }

    public void addToJFrame(JFrame j) { //adds to jFrame
        j.add(alien);
    }
    public int getxPos() { //getter methods
        return xPos;
    }
    public int getyPos() {
        return yPos;
    }
    public void setInvis() { //set invisible
        alien.setVisible(false);
        amHit = true;
    }
    public boolean checkAmHit() { //checks if the alien has been hit
        return amHit;
    }
}