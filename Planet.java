package fullscreentest;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Planet extends gameObject {
    private int planetWidth;
    private int planetHeight;
    private int screenWidth;
    private int screenHeight;
    private int fullHdY = 9;//пропорции по высоте
    private int fullhdX = 16;//пропорции по ширине
    private Image planet;
    
    public Planet(int x, int y, int screenHeight, int screenWidth) {
        super(x, y, ID.Neutral);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        planetWidth = this.screenWidth/fullhdX;
        planetHeight = this.screenWidth/fullhdX;
        ImageIcon planetOpenFile = new ImageIcon("planet.png");
        planet = planetOpenFile.getImage();
        planet = planet.getScaledInstance(planetWidth, planetHeight, Image.SCALE_SMOOTH);
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.drawImage(planet, 
                    x, 
                    y, 
                    planetWidth, 
                    planetHeight, 
                    null);
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
