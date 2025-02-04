import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener{
    ImageIcon background = new ImageIcon("Menubackground.jpg");
    JLabel backgroundImage = new JLabel(background);
    JLabel title = new JLabel();
    JPanel titlePanel = new JPanel();
    static JFrame frame;
    CrossyRoad Crossygame;
    Pong Ponggame;
    FloppyBird FloppyGame;
    Alien_Invaders InvaderGame;
    public Menu() {
        frame = new JFrame(); //gui initialization
        JPanel newTab = new JPanel();
        newTab.setLayout(null);
        frame.add(newTab);
        frame.setLayout(null); //layout to null for flexibility
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE)); //exit the system when user clicks x
        frame.setVisible(true);
        frame.setSize(1400, 784);
        frame.setLocation(0, 0);

        title.setVisible(true); //gui of title
        title.add(backgroundImage);
        title.setText("ARCADE MACHINE");
        title.setForeground(Color.WHITE);
        title.setBounds(0,40,1400,600);
        title.setFont(new Font("Serif", Font.BOLD, 90));

        titlePanel.setVisible(true); //adding title to titlePanel as it is a JLabel
        titlePanel.setOpaque(false);
        titlePanel.add(title);
        frame.add(titlePanel);
        titlePanel.setBounds(0,50,1400,600);

        JButton Crossyroad = new JButton("Crossy Road"); //initialization of buttons
        JButton pong = new JButton("Pong");
        JButton FB = new JButton("Floppy Bird");
        JButton Invader = new JButton("Alien Invaders");

        setButtonBackground(Crossyroad); //setting button backgrounds to match the background of JFrame
        setButtonBackground(pong);
        setButtonBackground(FB);
        setButtonBackground(Invader);

        backgroundImage.setBounds(0,0,1400,784); //setting bounds of background image

        frame.add(Crossyroad); //adding buttons to jFrame
        frame.add(pong);
        frame.add(FB);
        frame.add(Invader);
        frame.add(backgroundImage);
        Invader.setBounds(50, 450, 300, 100); //setting bounds of buttons
        FB.setBounds(380, 450, 300,100);
        pong.setBounds(720, 450, 300, 100);
        Crossyroad.setBounds(1050, 450, 300, 100);
        ActionListener buttonPress = e -> { //setting up buttons in actionlistener
            if (e.getSource() == Crossyroad) {
                frame.setVisible(false);
                Crossygame = new CrossyRoad();
            }
            else if (e.getSource() == pong) {
                frame.setVisible(false);
                Ponggame = new Pong();
            }
            else if (e.getSource() == FB) {
                frame.setVisible(false);
                FloppyGame = new FloppyBird();
            }
            else if (e.getSource() == Invader) {
                frame.setVisible(false);
                InvaderGame = new Alien_Invaders();
            }
        };
        Crossyroad.addActionListener(buttonPress); //adding buttons to action listner
        pong.addActionListener(buttonPress);
        FB.addActionListener(buttonPress);
        Invader.addActionListener(buttonPress);

    }
    public void setButtonBackground(JButton j) { //method to put image on button and make it transparent
        j.add(backgroundImage);
        j.setForeground(Color.WHITE);
        j.setContentAreaFilled(false);
        j.setFocusPainted(false);
        j.setOpaque(false);
        j.setFont(new Font("Serif", Font.BOLD, 35));
    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
