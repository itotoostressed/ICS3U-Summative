import javax.swing.*;

public class LoseScreen extends JFrame {
    ImageIcon loseScreenImage = new ImageIcon("game over.jpg");
    JLabel loseLabel = new JLabel(loseScreenImage);
    JPanel losePanel = new JPanel();
    ImageIcon winScreenImage = new ImageIcon("C:\\Users\\matth\\IdeaProjects\\Summative\\you win.jpg");
    JLabel winLabel = new JLabel(winScreenImage);
    JPanel winPanel = new JPanel();
    int w = 640, h = 360;
    public LoseScreen (JFrame j, boolean winLose) {
        if (!winLose) {
            loseLabel.setVisible(true);
            loseLabel.setBounds(0,0,w,h);

            losePanel.setVisible(true);
            losePanel.setBounds(0,0,w,h);
            losePanel.add(loseLabel);

            j.add(losePanel);
            j.setBounds(0,0,w,h);
        }
        else {
            winLabel.setVisible(true);
            winLabel.setBounds(0,0,w,h);

            winPanel.setVisible(true);
            winPanel.setBounds(0,0,w,h);
            winPanel.add(winLabel);

            j.add(winPanel);
            j.setBounds(0,0,w,h);
        }

    }
    public void setInvis(JFrame j) {
       j.setVisible(false);
    }
}
