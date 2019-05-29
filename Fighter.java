package fullscreentest;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;


public class Fighter extends ship{
    private int fighterWidth;
    private int fighterHeight;
    private Image fighter, activeFighter;
    private AffineTransform atForImages;
    
    public Fighter(int x, int y, ID id, int screenHeight, int screenWidth) {
        super(x, y, id);
        fighterWidth = screenWidth/(fullhdX * 3);
        fighterHeight = screenWidth/(fullhdX * 3 );
        ImageIcon spaceshipOpenFile = new ImageIcon("fighter.png");
        fighter = spaceshipOpenFile.getImage();
        fighter = fighter.getScaledInstance(fighterWidth, fighterHeight, Image.SCALE_SMOOTH);
        ImageIcon spaceshipActiveOpenFile = new ImageIcon("fighteractive.png");
        activeFighter = spaceshipActiveOpenFile.getImage();
        activeFighter = activeFighter.getScaledInstance(fighterWidth, fighterHeight, Image.SCALE_SMOOTH);
    }
    
    
    @Override
    public void spaceshipMoveTo(int x, int y) {
        enterXPointOfShip = x;
        enterYPointOfShip = y;
        shipMoving = true;
        if(x - this.x == 0){
            restX = 0;
            restY = 0;
            speedY = 10;
        }
        else{
            movementAngle = Math.atan((double)(y - this.y) / (x - this.x));
            if((x - this.x) < 0){
                speedX = -(int) (10 * Math.cos(movementAngle));
                speedY = -(int) (10 * Math.sin(movementAngle));
                restX =  -10 * Math.cos(movementAngle) - speedX;
                restY =  -10 * Math.sin(movementAngle) - speedY;
                movementAngle += Math.PI;
            }
            else{
                speedX = (int) (10 * Math.cos(movementAngle));
                speedY = (int) (10 * Math.sin(movementAngle));
                restX =  10 * Math.cos(movementAngle) - speedX;
                restY =  10 * Math.sin(movementAngle) - speedY;
            }
        }
    }

    @Override
    public void move() {
        if(Math.pow(this.x - enterXPointOfShip, 2) + Math.pow(this.y - enterYPointOfShip, 2) > 400 ) {
            int i = 0;
            int j = 0;
            restXImp += restX;
            restYImp += restY;
            if(restXImp >= 1){
                i++;
                restXImp--;
            }
            else if(restXImp <= -1){
                i--;
                restXImp++;
            }
            if(restYImp >= 1){
                j++;
                restYImp--;
            }
            else if(restYImp <= -1){
                j--;
                restYImp++;
            }
            x += speedX + i;
            y += speedY + j;
        }
        else{
            x = enterXPointOfShip;
            y = enterYPointOfShip;
            shipMoving = false;
            restXImp = 0;
            restYImp = 0;
        }
    }
    
    @Override
    public void paint(Graphics2D g2d) {
        atForImages = AffineTransform.getTranslateInstance(x, y);
        atForImages.rotate(movementAngle + Math.PI / 2, fighterWidth / 2, fighterHeight / 2);
        if(shipActive)
            g2d.drawImage(activeFighter,atForImages,null);
        else{
            g2d.drawImage(fighter,atForImages,null);
        }
    }

    @Override
    public int getShipWidth() {
        return fighterWidth;
    }

    @Override
    public int getShipHeight() {
        return fighterHeight;
    }
}
