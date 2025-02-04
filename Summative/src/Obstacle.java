import javax.swing.*;
import java.awt.*;

public class Obstacle { // start of obstacle class so that we can simplify the process of creating objects.
    private JPanel myPanel ; // creating the JPanel
    private int myX, myY, myW, myH; //
    private int step = 50; //setting up increments so that the objects can move
    public Obstacle (int oX, int oY, int w, int h) { // parameters for initial position size
        ImageIcon carImage = new ImageIcon("car.png");
        JLabel carJLabel = new JLabel(carImage);
        carJLabel.setBounds(oX,oY,w,h);
        carJLabel.setVisible(true);

        myPanel = new JPanel(); // setup JPanel for obstacle
        myPanel.setBackground(Color.BLUE); // obstacle color
        myPanel.add(carJLabel);
        myPanel.setBounds(oX, oY, w, h); // setting obstacle bounds based on input
        myPanel.setVisible(true); // making sure that obstacle is visible
        myX = oX; // saving instance variables to memory so that
        myY = oY;
        myW = w;
        myH = h;
    }
    public void addToJFrame(JFrame J) {
        J.add(myPanel); //adding the JPanel to the
    }
    public void timeFinish () {
        myX += step; //incrementation
        myPanel.setBounds(myX, myY, myW, myH); //resetting bounds from saved instance variables so that the object can reload each time the timeFinish method is called
        if (myX >675) { //if the object hits the border (725), then incrementation aka step is reversed and vice versa
            step = -50;
        }
        else if (myX <50){
            step = 50;
        }
    }
    public void setColor(Color color) {
        myPanel.setBackground(color);
    }
    public boolean hitScan(int X, int Y, int W, int H) { //passing in value is the moving character object
        int LocalCX = myX+myW/2;//local center X of the obstacles
        int LocalCY = myY+myH/2;
        int xDist = Math.abs(X- LocalCX); //calculating the distance between both objects center
        int yDist = Math.abs(Y- LocalCY);

        if ((myW + W)/2 >= xDist && (myH + H)/2 >= yDist) { //adding half of both object hitboxes so that we can compare the distance between the two, when smaller, return true as in hit.
            return true;
        }
        return false; //did not hit
    }
    public void SetVisible(boolean i) {
        if (i == true) {
            myPanel.setVisible(true);
        }
        else {
            myPanel.setVisible(false);
        }
    }
}