package fullscreentest;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class spaceShip extends gameObject{
    private int fullHdY = 9;//пропорции по высоте
    private int fullhdX = 16;//пропорции по ширине
    
    private int screenHeight;
    private int screenWidth;
    
    private boolean spaceshipMoving = false;
    private boolean spaceshipActive = false;
    private int spaceShipWidth;
    private int spaceShipHeight;
    private int enterXPointOfSpaceship;
    private int enterYPointOfSpaceship;
    private double restX = 0;
    private double restY = 0;
    private double restXImp = 0;
    private double restYImp = 0;
    private double movementAngle;
    
    private Image spaceship, activeSpaceship;
    private AffineTransform atForImages;
    
    spaceShip(int screenHeight, int screenWidth){
        super(screenWidth/2 - screenWidth/32, screenHeight/2 - screenWidth/32, ID.Player);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.spaceShipWidth = screenWidth/fullhdX;
        this.spaceShipHeight = screenWidth/fullhdX;
        ImageIcon spaceshipOpenFile = new ImageIcon("spaceship.png");
        spaceship = spaceshipOpenFile.getImage();
        spaceship = spaceship.getScaledInstance(spaceShipWidth, spaceShipHeight, Image.SCALE_SMOOTH);
        ImageIcon spaceshipActiveOpenFile = new ImageIcon("activeSpaceship.png");
        activeSpaceship = spaceshipActiveOpenFile.getImage();
        activeSpaceship = activeSpaceship.getScaledInstance(this.spaceShipWidth, this.spaceShipHeight, Image.SCALE_SMOOTH);
    }
    
    //SSSSEEEETTTT
    public void setMovementAngle(double movementAngle){
        this.movementAngle = movementAngle;
    }
    public void setenterXPointOfSpaceship(int enterXPointOfSpaceship){
        this.enterXPointOfSpaceship = enterXPointOfSpaceship;
    }
    public void setenterYPointOfSpaceship(int enterYPointOfSpaceship){
        this.enterYPointOfSpaceship = enterYPointOfSpaceship;
    }
    public void setspaceshipMoving(boolean spaceshipMoving){
        this.spaceshipMoving = spaceshipMoving;
    }
    public void setspaceshipActive(boolean spaceshipActive){
        this.spaceshipActive = spaceshipActive;
    }
    public void setrestX(int restX){
        this.restX = restX;
    }
    public void setrestY(int restY){
        this.restY = restY;
    }
    
    //GGGGGEEEEEEETTTTT
    public boolean getspaceshipActive(){
        return this.spaceshipActive;
    }
    public boolean getspaceshipMoving(){
        return this.spaceshipMoving;
    }
    public int getenterXPointOfSpaceship(){
        return enterXPointOfSpaceship;
    }
    public int getenterYPointOfSpaceship(){
        return enterYPointOfSpaceship;
    }
    
    //Mechanics
    public void spaceshipMoveTo(int x, int y){
        enterXPointOfSpaceship = x;
        enterYPointOfSpaceship = y;
        spaceshipMoving = true;
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
        if(Math.pow(this.x - enterXPointOfSpaceship, 2) + Math.pow(this.y - enterYPointOfSpaceship, 2) > 400 ) {
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
            this.x = enterXPointOfSpaceship;
            this.y = enterYPointOfSpaceship;
            spaceshipMoving = false;
            restXImp = 0;
            restYImp = 0;
        }
    }
    @Override
    public void paint(Graphics2D g2d){
        atForImages = AffineTransform.getTranslateInstance(this.x, this.y);
        atForImages.rotate(movementAngle, spaceShipWidth/2, spaceShipWidth/2);
        if(spaceshipActive)
            g2d.drawImage(activeSpaceship,atForImages,null);
        else{
            g2d.drawImage(spaceship,atForImages,null);
        }
    }
    
}
