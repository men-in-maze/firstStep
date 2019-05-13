package fullscreentest;

import java.awt.*;

public abstract class gameObject {
    protected int x, y;
    protected ID id;
    protected int speedX, speedY;
    
    gameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void paint(Graphics2D g2d);
    public abstract void move();
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public ID getID(){
        return id;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
}
