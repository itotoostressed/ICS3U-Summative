import java.awt.event.KeyEvent; //importing utilities
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;


public class FloppyBird extends JFrame implements KeyListener {
    ImageIcon wallIMG = new ImageIcon("birdBackground.jpg"); //when using this image, be sure to change the users to your own user name.
    JLabel wall = new JLabel(wallIMG);
    ImageIcon birdImage = new ImageIcon("Flappybird.png"); //remember to get a square bird. completely square. COMPLETELY.
    JLabel birdIMG = new JLabel(birdImage);
    JPanel bird;
    boolean winLose; //boolean win lose to stop the game when win or lose
    private Random rand = new Random(); //setting up new randoms
    int xPos = 50, yPos = 500;
    Timer tm, gameWinLose;
    boolean IFALIVE = true;
    LoseScreen lost;
    Pillars[] pillarArray = new Pillars[8]; //setting up pillar array for pillars on screen

    boolean alive = true;

    public FloppyBird() { //implement arrays to create infinite generation
        setBounds(0,0, 1200, 1000);
        setLayout(null);
        addKeyListener(this);
        setVisible(true);

        int X = rand.nextInt(1001) + 400; //random x position for pillars
        for (int i = 0; i<8; i++) { //for loop to run through all pillars
            pillarArray[i] = new Pillars(X); //initializing pillars as pillars objects
            pillarArray[i].addToJFrame(this); //adding them to JFrame
            pillarArray[i].randColor(); //random colors for the pillars
            X += 300;
        }

        bird = new JPanel(); //initialization of bird GUI
        bird.setBounds(xPos, yPos, 50,50);
        bird.setVisible(true);
        bird.setBackground(null);
        bird.add(birdIMG);
        this.add(bird);

        add(wall); //adding the background wall to JFrame
        wall.setVisible(true);
        wall.setBounds(0,0,1200,1000);

        tm = new Timer(100, e -> { //timer for animation
            if(IFALIVE) {
                for (int i = 0; i<8; i++) { //for loop to animate all objects
                    pillarArray[i].animation();
                    IFALIVE = ifAlive(pillarArray[i]); //setting IFALIVE to the ifalive of pillarArray, to ensure game end upon true
                }
                yPos += 5;
                bird.setBounds(xPos,yPos,50,50); //refreshing bird bounds
                for (int i = 0; i<8; i++) {
                    if (pillarArray[i].getxPos() < -50) {
                        reset(i); //resetting positions of pillars for endless game
                    }
                }
            }

        });
        if(!alive) {
            tm.stop();
        }
        tm.start();
        tm.restart();
    }


    @Override
    public void keyTyped(KeyEvent e) {


    }


    @Override
    public void keyPressed(KeyEvent e) { //keypressed function
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            yPos -= 50;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE ) {
            yPos -= 50;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            yPos += 100;
        }
        bird.setBounds(xPos,yPos, 50, 50);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    public boolean ifAlive(Pillars Pill) { //checks if the user has hit a pillar, if so make everything invisible and then show the lose screen
        if (Pill.hitScan(xPos + 25, yPos + 25, 50, 50) || yPos >1000 || yPos < 0) {
            winLose = false;
            lost = new LoseScreen(this, winLose);
            bird.setVisible(false);
            wall.setVisible(false);

            for (int i = 0; i<8; i++) {
                pillarArray[i].setInvis();
            }
            gameWinLose = new Timer(5000, e -> {
                Menu.frame.setVisible(true);
                lost.setInvis(this);
            });
            gameWinLose.start();
            gameWinLose.setRepeats(false);
            alive = false;
            return false;
        }
        return true;
    }
    public void reset(int i) { //resets the xPos of the pillar depending on the last pillar
        if (alive) {
            int lasti = i-1;
            if (lasti < 0){
                lasti = 7;
            }
            pillarArray[i].setx(pillarArray[lasti].getxPos() + 300);
            pillarArray[i].setheight(rand.nextInt(601));
            pillarArray[i].randColor();
        }
    }
}
