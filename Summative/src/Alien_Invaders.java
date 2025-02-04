import javax.swing.*; //importing utilites
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Alien_Invaders extends JFrame implements KeyListener {
    ImageIcon shipI = new ImageIcon("space invaders ship.png");  //initializing gui
    JLabel shipImage = new JLabel(shipI);
    JLabel projectileNum;
    JPanel  Ship, projectileNumPanel = new JPanel();
    int xPos = 700, yPos = 850; //initialising int variables
    int incrementX = -10;
    int shipIncrement = 50;
    int trackProjectile = 0;


    boolean continuePlay = true; //for if the game continues
    boolean ifShoot = false;

    LoseScreen lost;
    AlienGrid aGrid = new AlienGrid(this); //setup the alien grid
    Projectile[] projectileArray = new Projectile[176]; //number of projectiles given to the user
    Powerup pill = new Powerup(this);


    Timer tm, gameWinLose; //timers for animation and gameWinLose
    public Alien_Invaders (){
        addKeyListener(this); //add keyListener
        setBounds(0,0, 1400, 1000); //setting bounds of JFrame
        getContentPane().setBackground(Color.black);
        setLayout(null); //setup layout for the JFrame
        setVisible(true);

        shipImage.setVisible(true); //setup GUI for the ship
        Ship = new JPanel();
        Ship.setBounds(xPos, yPos, 82, 50);
        Ship.setVisible(true);
        Ship.setBackground(Color.BLACK);
        Ship.add(shipImage);
        this.add(Ship);

        projectileNum = new JLabel(String.valueOf(175- trackProjectile)); //setup number of projectiles remaining/ammo for the user to view
        projectileNum.setVisible(true);
        projectileNum.setFont(new Font("Serif", Font.PLAIN, 25));
        projectileNum.setForeground(Color.WHITE);
        projectileNum.setBounds(0,0,100,100);

        projectileNumPanel.setBounds(0,0,100,100);
        projectileNumPanel.add(projectileNum);
        projectileNumPanel.setVisible(true);
        projectileNumPanel.setBackground(Color.BLACK);
        this.add(projectileNumPanel);


        tm = new Timer(100, e-> { //new timer for animation
            aGrid.gridAnimate(incrementX);
            pill.animate();
            if (pill.hitScan(xPos + 41, yPos +25, 82, 50)) {
                pill.resetPos();
            }
            if (aGrid.checkWin()) { //if this is true, then lose screen appears and the game ends
                WinLoseScreen(true);
                tm.setRepeats(false);
            }
            updateProjectileNum();
            aGrid.checkHit(projectileArray, trackProjectile);
            for (int i = 0; i<trackProjectile; i++) { //animations for the every projectile fired
                if (ifShoot) {
                    projectileArray[i].fire();
                }
            }
            if (aGrid.wallHit(0, 1325)) { //if the aliens hit a wall then reflect
                incrementX *= -1;
                aGrid.gridMoveDown(50);
            }
            ifAlive();
        });
        if (!continuePlay) { //stops the game
            tm.stop();
        }else {
            tm.start(); //continues game
            tm.restart();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) { //keypressed function
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (trackProjectile <= 175) {
                projectileArray[trackProjectile] = new Projectile(); //creates new projectile depending on the number fired
                projectileArray[trackProjectile].addToJFrame(this);
                projectileArray[trackProjectile].updatePosition(xPos + 41);
                ifShoot = true;
                trackProjectile++; //increment to track num of projectiles fired
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { //moving left
            xPos -= shipIncrement;
            Ship.setBounds(xPos,yPos, 82, 50);
            if (xPos < 0) {
                xPos = 0;
                Ship.setBounds(xPos,yPos, 82, 50); //ensures that user doesn't go off screen
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //moving right
            xPos += 50;
            Ship.setBounds(xPos,yPos, 82, 50);
            if (xPos > 1298) { //ensures that user doesn't go off screen
                xPos = 1298;
                Ship.setBounds(xPos,yPos, 82, 50);
            }
        }
    }

    public void ifAlive() { //checks if aliens are alive
        if(!aGrid.checkAlive(xPos, yPos)) {
            WinLoseScreen(false);
        }
    }
    public void updateProjectileNum() { //updates number of projectiles
        projectileNum.setText(String.valueOf(176-trackProjectile));
    }

    public void WinLoseScreen(boolean winLose) { //creates a win lose screen using loseScreen objects
        Ship.setVisible(false);
        projectileNumPanel.setVisible(false);
        projectileNum.setVisible(false);
        shipImage.setVisible(false);

        for (int i = 0; i< trackProjectile; i++) {
            projectileArray[i].setInvis();
        }
        lost = new LoseScreen(this, winLose);

        gameWinLose = new Timer(5000, a -> {
            Menu.frame.setVisible(true);
            lost.setInvis(this);
        });
        gameWinLose.setRepeats(false);
        gameWinLose.start();
        continuePlay = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
