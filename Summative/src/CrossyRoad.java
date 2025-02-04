import javax.swing.*; //importing utilities
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;




class CrossyRoad extends JFrame implements ActionListener, KeyListener { //start of Display class
    int xPos = 350, yPos = 800; //setting up instance variables for character's position
    JPanel frog = new JPanel(); //creating JPanel of character
    JPanel winTile = new JPanel();
    Obstacle obstacle1 = new Obstacle(0, 500, 100, 50); //creating Obstacle objects
    Obstacle obstacle2 = new Obstacle(300, 300, 100, 50);
    Obstacle obstacle3 = new Obstacle(700, 200, 100, 50);
    Obstacle obstacle4 = new Obstacle(350, 700, 100, 50);
    boolean alive = true; //creating a condition where it checks for the frog to be alive
    Timer tm, gameWinLose; //create our timer
    ImageIcon winningTileImage = new ImageIcon("finish line.png");
    JLabel winScreen = new JLabel("YOU WIN!");
    JLabel loseScreen = new JLabel("YOU DIED");
    JLabel winningTile = new JLabel(winningTileImage);
    boolean gamefinished = false;

    LoseScreen lost;

    static ImageIcon[] Icons = new ImageIcon[]{
            new ImageIcon("1.png"), new ImageIcon("2.png"), new ImageIcon("3.png"), new ImageIcon("4.png"), new ImageIcon("5.png"), new ImageIcon("6.png")
    };
    static JLabel frogLabel;
    static Timer timer; //THIS ONE IS FOR ANIMATING


    public CrossyRoad() {
        addKeyListener(this);
        setBounds(0, 0, 800, 900); //setting boundaries of JFrame
        setLayout(null); //no layout to ensure freedom within JFrame
        setVisible(true); //ensures that the JFrame and everything within it is visible


        loseScreen.setBounds(200,0, 800, 900);
        loseScreen.setFont(new Font("Serif", Font.PLAIN, 100));
        loseScreen.setBackground(Color.BLACK);
        loseScreen.setVisible(false);
        this.add(loseScreen);


        winScreen.setBounds(200,0, 800, 900);
        winScreen.setFont(new Font("Serif", Font.PLAIN, 100));
        winScreen.setBackground(Color.BLACK);
        winScreen.setVisible(false);
        this.add(winScreen);


        frogLabel = new JLabel(Icons[0]);
        frog.add(frogLabel);
        frog.setBounds(xPos, yPos, 100, 100); //setting character size and position
        frog.setVisible(true); //ensuring character is visible
        this.add(frog); //adding character to the JFrame


        winningTile.setVisible(true);
        winTile.add(winningTile);


        winTile.setBounds(0, 0, 1100, 100);
        winTile.setBackground(Color.BLACK);
        winTile.setVisible(true);
        this.add(winTile);


        obstacle2.setColor(Color.pink); //sets the color to pink
        obstacle3.setColor(Color.CYAN);
        obstacle4.setColor(Color.BLACK);
        obstacle1.addToJFrame(this); //addToJFrame methods to add the JPanel (myPanel) into the current JFrame
        obstacle2.addToJFrame(this);
        obstacle3.addToJFrame(this);
        obstacle4.addToJFrame(this);


        tm = new Timer(500, e -> { //e -> {} allows us to have an action within the timer, where when timer stop, action start.
            if (!gamefinished) {
                obstacle1.timeFinish(); //when timer finishes, movement will occur\
                obstacle2.timeFinish();
                obstacle3.timeFinish();
                obstacle4.timeFinish();
                if (gamefinished != ifWin()) {
                    gamefinished = ifWin();
                }
                if (gamefinished != ifAlive(obstacle1)) {
                    gamefinished = ifAlive(obstacle1);
                }
                if (!gamefinished) {
                    gamefinished = ifAlive(obstacle2);
                }
                if (!gamefinished) {
                    gamefinished = ifAlive(obstacle3);
                }
                if (!gamefinished) {
                    gamefinished = ifAlive(obstacle4);
                }
            }

        }); //timer so that obstacle moves
        tm.start(); //starts the timer
        tm.restart(); //loops the timer
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) { // method for when arrow keys are pressed, action happens.
        frogJump();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (this.xPos < 700) { //checking x positions so that the sprite doesn't go off the map
                xPos += 100; //incrementing
            }
        } //arrow key right
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.xPos > 0) { //checking x positions so that the sprite doesn't go off the map
                xPos -= 100;//incrementing
            }
        } //arrow key left
        else if (e.getKeyCode() == KeyEvent.VK_UP) {//arrow key up
            if (this.yPos > 0) { //checking y positions so that the sprite doesn't go off the map
                yPos -= 100;//incrementing
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.yPos < 800) { //checking y positions so that the sprite doesn't go off the map
                yPos += 100;//incrementing
            }
        } //arrow key down
        frog.setBounds(xPos, yPos, 100, 100); //restating the blackDot to ensure refresh of its position every time the arrow keys get pressed
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }


    public boolean ifAlive(Obstacle o) { //checking if the user is still alive
        if (o.hitScan(xPos +25, yPos + 50, 50, 100)) {
            lost = new LoseScreen(this, false);
            frog.setVisible(false);
            obstacle1.SetVisible(false);
            obstacle2.SetVisible(false);
            obstacle3.SetVisible(false);
            obstacle4.SetVisible(false);
            winTile.setVisible(false);
            gameWinLose = new Timer(5000, e -> {
                Menu.frame.setVisible(true);
                lost.setInvis(this);
            });
            gameWinLose.start();
            gameWinLose.setRepeats(false);
            return true;
        }
        return false;
    }


    public boolean ifWin() {
        if (yPos < 100) {
            frog.setVisible(false);
            obstacle1.SetVisible(false);
            obstacle2.SetVisible(false);
            obstacle3.SetVisible(false);
            obstacle4.SetVisible(false);
            winTile.setVisible(false);
            winScreen.setVisible(true);
            lost = new LoseScreen(this, true);
            gameWinLose = new Timer(5000, e -> {
                Menu.frame.setVisible(true);
                lost.setInvis(this);
            });
            gameWinLose.setRepeats(false);
            gameWinLose.start();
            return true;
        }
        return false;
    }


    public static void frogJump() { //animation method, static. The point of this class isn't really to have a bunch of objects, just holds a method for animation
        for (int i = 0; i < Icons.length; i++) {
            final int index = i;
            timer = new Timer(35 * (i + 1), e -> {
                frogLabel.setIcon(Icons[index]);
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}
