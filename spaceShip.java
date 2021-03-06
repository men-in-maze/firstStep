package fullscreentest;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class spaceShip extends ship{
    private int spaceShipWidth;
    private int spaceShipHeight;
    private Image spaceship, activeSpaceship;
    private AffineTransform atForImages;
    
    spaceShip(int screenHeight, int screenWidth, ID type){
        super(screenWidth/2 - screenWidth/32, screenHeight/2 - screenWidth/32, type);
        spaceShipWidth = screenWidth/fullhdX;
        spaceShipHeight = screenWidth/fullhdX;
        ImageIcon spaceshipOpenFile = new ImageIcon("spaceship.png");
        spaceship = spaceshipOpenFile.getImage();
        spaceship = spaceship.getScaledInstance(spaceShipWidth, spaceShipHeight, Image.SCALE_SMOOTH);
        ImageIcon spaceshipActiveOpenFile = new ImageIcon("activeSpaceship.png");
        activeSpaceship = spaceshipActiveOpenFile.getImage();
        activeSpaceship = activeSpaceship.getScaledInstance(spaceShipWidth, spaceShipHeight, Image.SCALE_SMOOTH);
    }
    
    //Mechanics
    @Override
    public void spaceshipMoveTo(int x, int y){
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
    public void move(){
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
            this.x += speedX + i;
            this.y += speedY + j;
        }
        else{
            this.x = enterXPointOfShip;
            this.y = enterYPointOfShip;
            shipMoving = false;
            restXImp = 0;
            restYImp = 0;
        }
    }
    @Override
    public void paint(Graphics2D g2d){
        atForImages = AffineTransform.getTranslateInstance(this.x, this.y);
        atForImages.rotate(movementAngle, spaceShipWidth/2, spaceShipHeight/2);
        if(shipActive)
            g2d.drawImage(activeSpaceship,atForImages,null);
        else{
            g2d.drawImage(spaceship,atForImages,null);
        }
    }

    @Override
    public int getShipWidth() {
        return spaceShipWidth;
    }

    @Override
    public int getShipHeight() {
        return spaceShipHeight;
    }
    
}
