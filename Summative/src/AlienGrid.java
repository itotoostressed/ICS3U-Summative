import javax.swing.*;

public class AlienGrid extends JFrame {
    Aliens[][] alienArray; //alien array
    int collumnLim = 12; //number of collumns

    public AlienGrid(JFrame j) {
        alienArray = new Aliens[8][collumnLim]; //initialization of alien array
        int x = 75, y = 50;
        for (int rows = 0; rows < 8; rows++) {
            for (int collumns = 0; collumns < collumnLim; collumns++) { //adding alien arrays to JFrame along with setting their x and y positions accordingly
                alienArray[rows][collumns] = new Aliens(x, y);
                alienArray[rows][collumns].addToJFrame(j);
                x += 75;
            }
            x = 75;
            y += 50;
        }
    }

    public void gridMoveDown(int incrementY) { //moves the alien grid down
        for (int rows = 0; rows < 8; rows++) {
            for (int collumns = 0; collumns < collumnLim; collumns++) {
                alienArray[rows][collumns].moveDown(incrementY);
            }
        }
    }

    public void gridAnimate(int incrementX) { //moves the alien grid left or right
        for (int rows = 0; rows < 8; rows++) {
            for (int collumns = 0; collumns < collumnLim; collumns++) {
                alienArray[rows][collumns].animation(incrementX);
            }
        }
    }
    public boolean wallHit(int wall1, int wall2) { //if the alien grid hits either wall1 or wall2, then reverse the incrementX
        for (int rows = 0; rows < 8; rows++) {
            for (int collumns = 0; collumns < collumnLim; collumns++) {
                Aliens a = alienArray[rows][collumns];
                if (a.checkAmHit()) {
                    continue;
                }
                if (a.getxPos() > wall2 || a.getxPos() < wall1) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkAlive(int x, int y) { //checks if the alien is alive, if it isn't then it skips it in animating next time
        for (int rows = 0; rows < 8; rows++) {
            for (int collumns = 0; collumns < collumnLim; collumns++) {
                Aliens a = alienArray[rows][collumns];
                if (a.checkAmHit()) { //checks if the current alien in question has been hit, if it has then CONTINUE to the next alien
                    continue;
                }
                if (x == a.getxPos() && y == a.getyPos()) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkWin() { //checks if the alien has hit the user
        for (int rows = 0; rows < 8; rows++) {
            for (int collumns = 0; collumns < collumnLim; collumns++) {
                Aliens a = alienArray[rows][collumns];
                if (!a.checkAmHit()) { //checks if the current alien in question has been hit, if it has then CONTINUE to the next alien
                    return false;
                }
            }
        }
        return true;
    }
    public void checkHit(Projectile[] inputProjectile, int activeBullet) {
        for (int rows = 0; rows < 8; rows++) {
            for (int collumns = 0; collumns < collumnLim; collumns++) { //checks every alien from order of row, column
                Aliens a = alienArray[rows][collumns];
                if (a.checkAmHit()) {
                    continue; //goes on to the next alien
                }
                for (int i = 0; i< activeBullet; i++) {
                    if (inputProjectile[i].checkHitTarget()) {
                        continue; //goes on to the next projectile
                    }
                    if (inputProjectile[i].hitScan(a.getxPos(), a.getyPos(), 50, 50)) { //calling the hitScan method to check whether or not the alien and the projectile have collided
                        inputProjectile[i].setInvis(); //if they have collided, then the projectile and alien are set invisible
                        a.setInvis();
                    }
                }
            }
        }
    }
}