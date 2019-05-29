
package fullscreentest;

public abstract class ship extends gameObject{
    protected int fullHdY = 9;//пропорции по высоте
    protected int fullhdX = 16;//пропорции по ширине
        
    protected boolean shipMoving = false;
    protected boolean shipActive = false;

    protected int enterXPointOfShip;
    protected int enterYPointOfShip;
    protected double restX = 0;
    protected double restY = 0;
    protected double restXImp = 0;
    protected double restYImp = 0;
    protected double movementAngle;
    
    public ship(int x, int y, ID id) {
        super(x, y, id);
    }
    //SSSSEEEETTTT
    public void setMovementAngle(double movementAngle){
        this.movementAngle = movementAngle;
    }
    public void setenterXPointOfSpaceship(int enterXPointOfSpaceship){
        this.enterXPointOfShip = enterXPointOfSpaceship;
    }
    public void setenterYPointOfSpaceship(int enterYPointOfSpaceship){
        this.enterYPointOfShip = enterYPointOfSpaceship;
    }
    public void setspaceshipMoving(boolean spaceshipMoving){
        this.shipMoving = spaceshipMoving;
    }
    public void setspaceshipActive(boolean spaceshipActive){
        this.shipActive = spaceshipActive;
    }
    public void setrestX(int restX){
        this.restX = restX;
    }
    public void setrestY(int restY){
        this.restY = restY;
    }
    
    //GGGGGEEEEEEETTTTT
    public boolean getspaceshipActive(){
        return this.shipActive;
    }
    public boolean getspaceshipMoving(){
        return this.shipMoving;
    }
    public int getenterXPointOfSpaceship(){
        return enterXPointOfShip;
    }
    public int getenterYPointOfSpaceship(){
        return enterYPointOfShip;
    }
    public abstract void spaceshipMoveTo(int x, int y);
    public abstract int getShipWidth();
    public abstract int getShipHeight();
}
