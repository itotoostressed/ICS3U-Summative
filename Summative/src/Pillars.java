import javax.swing.*; //importing utilities
import java.awt.*;
import java.util.Random;
public class Pillars extends JFrame {
    JPanel Pillar1 = new JPanel(); //initialization of Jpanels
    JPanel Pillar2 = new JPanel();
    Random rand = new Random(); //setting up random object
    int xPos, yPos = 0, yPos2, h, h2;
    int pillarX, pillar1Y, pillar2Y,  p1H, p2H; //variables to save Pillar1's information
    public Pillars(int X) { //default constructor. no parameters because random generation
        xPos = X;
        h = rand.nextInt(601); //random height
        h2 = 1000-h-200;
        yPos2 = h + 200;
        Pillar1.setBounds( xPos, yPos,40, h); //initialization of GUI for pillars
        Pillar1.setVisible(true);
        Pillar1.setBackground(Color.BLACK);


        Pillar2.setBounds(xPos, yPos2, 40, h2);
        Pillar2.setBackground(Color.BLACK);
        Pillar2.setVisible(true);


        p1H = h; //storing variables as memory
        p2H = h2;
        pillarX = xPos;
        pillar1Y = yPos;
        pillar2Y = yPos2;
    }
    public void addToJFrame(JFrame j) {
        j.add(Pillar1);
        j.add(Pillar2);
    }
    public void animation() { //function to increment and move the pillars towards the player
        xPos -= 20;
        Pillar1.setBounds(xPos,0, 40, p1H);
        Pillar2.setBounds(xPos,pillar2Y,40, p2H);
    }
    public boolean hitScan(int X, int Y, int W, int H) { //passing in value is the moving character object
        int LocalCX = xPos + 40;//local center X of the obstacles
        int LocalCY = yPos+p1H/2;
        int LocalCenterY2 = pillar2Y + p2H/2;


        int xDist = Math.abs(X- LocalCX); //calculating the distance between both objects center
        int yDist = Math.abs(Y- LocalCY);
        int yDist2 = Math.abs(Y - LocalCenterY2);
        if ((40 + W)/2 >= xDist && (p1H + H)/2 >= yDist) { //adding half of both object hitboxes so that we can compare the distance between the two, when smaller, return true as in hit.
            return true;
        }if ((40 + W)/2 >= xDist && (p2H + H)/2 >= yDist2) {
            return true;
        }
        return false; //did not hit
    }
    public void setInvis() {
        Pillar1.setVisible(false);
        Pillar2.setVisible(false);
    }

    public void setx(int x) {
        xPos = x;
    }
    public int getxPos() {
        return xPos;
    }
    public void setheight(int height) {
        p1H = height;
        p2H = 1000-height-200;
        pillar2Y = height + 200;
    }
    public void randColor() {
        int newColor = rand.nextInt(6); //random int
        switch (newColor) { //depending on integer, the switch statement decides the color
            case 1:
                Pillar1.setBackground(Color.CYAN);
                Pillar2.setBackground(Color.CYAN);
                break;
            case 2:
                Pillar1.setBackground(Color.YELLOW);
                Pillar2.setBackground(Color.YELLOW);
                break;
            case 3:
                Pillar1.setBackground(Color.RED);
                Pillar2.setBackground(Color.RED);
                break;
            case 4:
                Pillar1.setBackground(Color.BLUE);
                Pillar2.setBackground(Color.BLUE);
                break;
            case 5:
                Pillar1.setBackground(Color.GREEN);
                Pillar2.setBackground(Color.GREEN);
                break;
        }
    }
}
