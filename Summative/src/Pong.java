import javax.swing.*; //importing utilities
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Pong extends JFrame implements ActionListener, KeyListener {
    int xPos = 100, yPos = 300; //initialization of int member variables
    int ballX = 1080, ballY = 480;
    int botX = 2100, botY = 300;
    int incrementX = -10, incrementY = 10;
    int botIncrementY = 25;
    int  randIncrement;
    JPanel character = new JPanel(); //creating GUI
    JPanel ball = new JPanel();
    JPanel bot = new JPanel();

    LoseScreen lost; //declaring lost as lose screen object

    boolean score = false;
    Timer tm, gameWinLose; //declaring timers


    public Pong () {
        addKeyListener(this);
        setBounds(0, 0, 2200, 1000);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK); //When you use getContentPane(), the content pane object then is substituted there so that you can apply a method to it.
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ball.setBounds(ballX, ballY, 20, 20); //initializing GUI
        ball.setVisible(true);
        this.add(ball);

        bot.setBounds(botX, botY, 20, 100);
        bot.setVisible(true);
        this.add(bot);

        character.setBounds(xPos, yPos, 20, 200);
        character.setVisible(true);
        this.add(character);

        tm = new Timer(30, e -> { // allows us to have an action within the timer, where when timer stop, action start.
            animation(); //allows ball to move
            paddleHit(); //checks if ball has hit the paddle
            scoreCheck(); //checks if ball has gone past either paddles
        });
        tm.start(); //start the timer
        tm.restart(); //enables restarting of timer
        if(score) {
            tm.stop(); //if the score is true, then we stop the timer.
            gameWinLose.stop();
        }
    }
    public void actionPerformed(ActionEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) { // method for when arrow keys are pressed, action happens.
        if (e.getKeyCode() == KeyEvent.VK_UP) {//arrow key up
            if (this.yPos > 0) { //checking y positions so that the sprite doesn't go off the map
                yPos -= 100;//incrementing
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.yPos < 800) { //checking y positions so that the sprite doesn't go off the map
                yPos += 100;//incrementing
            }
        } //arrow key down
        character.setBounds(xPos, yPos, 20, 200);
    }




    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    private void animation() {
        randIncrement = (int) (Math.random() * 21);
        if (ballX > 0 && ballX < 2180 && ballY < 0) { //if it hits roof
            incrementY *= -1;
        }
        else if (ballX > 0 && ballX < 2180 && ballY >= 940) { //if it hits the bottom
            incrementY *= -1;
        }
        if (ballX >= botX && ballY > botY && ballY < botY + 100) { // (double check at home!) not an else if as if the other conditions are deemed true, it won't run/work
            incrementX *= -1;
        }
        if (ballY > botY + 50) { //checking for ball, ai for bot
            botIncrementY = randIncrement;
        }
        else if (ballY < botY + 100) {
            botIncrementY = -randIncrement;
        }
        if (botY < 0 ) {
            botIncrementY = 25;
        }
        else if (botY > 880) {
            botIncrementY = -25;
        }
        ballX += incrementX; //increments are last as they refresh and update last after changes
        ballY += incrementY;
        botY += botIncrementY;
        ball.setBounds(ballX, ballY, 20, 20); //resetting bounds to update information
        bot.setBounds(botX, botY, 20,  100);
    }
    private void scoreCheck() {
        if (ballX < 0 && ballY < 1000 && ballY > 0) { //checking if ball has gone past player paddle
            lost = new LoseScreen(this, false); //initalizes lose as loseScreen to create new losescreen object
            gameWinLose = new Timer(5000, e -> { //timer to delay the re-appearance of menu screen
                Menu.frame.setVisible(true);
                lost.setInvis(this);
            });
            gameWinLose.setRepeats(false); //sets everything invisible
            gameWinLose.start();
            character.setVisible(false);
            bot.setVisible(false);
            ball.setVisible(false);
            score = true; //sets score to true to stop timer
        }
        else if (ballX > 0 && ballX > 2180 && ballY > 0 && ballY < 1000) { //repeat of above, but for the bot
            lost = new LoseScreen(this, true);
            gameWinLose = new Timer(5000, e -> {
                Menu.frame.setVisible(true);
                lost.setInvis(this);
            });
            gameWinLose.setRepeats(false);
            gameWinLose.start();
            character.setVisible(false);
            bot.setVisible(false);
            ball.setVisible(false);
            score = true;
        }
    }
    private void paddleHit () {
        if (ballX <= xPos + 20 && ballY > yPos && ballY < yPos + 200) { //if it hits the player ball increment is reversed
            incrementX *= -1;
        }
    }
}
