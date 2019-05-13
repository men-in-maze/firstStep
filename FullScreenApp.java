package fullscreentest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FullScreenApp  extends JPanel implements ActionListener{
    private int fullhdY = 9;//пропорции по высоте
    private int fullhdX = 16;//пропорции по ширине
    private int screenHeight;
    private int screenWidth;
    private int screenSpeedX = 0;
    private int screenSpeedY = 0;
    
    MousePosition mousePosition;
    spaceShip spaceship;
    Planet planet;
    
    private Timer timer;
    private boolean inGame = true;
            
    FullScreenApp(int screenHeight,int screenWidth){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        spaceship = new spaceShip(screenHeight, screenWidth);
        planet = new Planet(1250, 300, screenHeight, screenWidth);
        mousePosition = new MousePosition(screenWidth / 2, screenHeight / 2);
        setFocusable(true);
        setBackground(Color.BLACK);
        initGame();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == 27){
                    System.exit(0);
                }
                //System.out.println(e.getKeyCode());
            }
            @Override
            public void keyTyped(KeyEvent e) {} 
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                double dist = Math.pow(e.getPoint().x - spaceship.getX() - screenWidth/32, 2) 
                        + Math.pow(e.getPoint().y - spaceship.getY() - screenWidth/32, 2);
                if(e.getButton() == 3 && spaceship.getspaceshipActive())
                    spaceship.spaceshipMoveTo(e.getPoint().x - screenWidth/32, e.getPoint().y - screenWidth/32);
                if(e.getButton() == 1 && dist <= 1700)spaceship.setspaceshipActive(true);
                if(e.getButton() == 1 && dist > 1700)spaceship.setspaceshipActive(false);
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        this.addMouseMotionListener(new MouseAdapter(){
            @Override public void mouseMoved(MouseEvent event) {
                mousePosition.setX(event.getX());
                mousePosition.setY(event.getY());
                
            } 
        });
        
    }
    
    public void initGame(){
        timer = new Timer(1,this);
        timer.start();
    }
    
    public void screenMoving(){
        if(mousePosition.getY() < 10){
            screenSpeedX = Math.round((mousePosition.getX() - (screenWidth / 2))/(screenWidth/fullhdX));
            screenSpeedY = -10;
        }
        else if(mousePosition.getY() > (screenHeight - 10)){
            screenSpeedX = Math.round((mousePosition.getX() - (screenWidth / 2))/(screenWidth / fullhdX));
            screenSpeedY = 10;
        }
        else if(mousePosition.getX() < 10){
            screenSpeedY = Math.round(((mousePosition.getY() - (screenHeight / 2)) * 2)/(screenHeight/fullhdY));
            screenSpeedX = -10;
        }
        else if(mousePosition.getX() > (screenWidth - 10)){
            screenSpeedY = Math.round(((mousePosition.getY() - (screenHeight / 2)) * 2)/(screenHeight/fullhdY));
            screenSpeedX = 10;
        }
        else{
            screenSpeedX = 0;
            screenSpeedY = 0;
        }
        spaceship.setX(spaceship.getX() - screenSpeedX);
        spaceship.setY(spaceship.getY() - screenSpeedY);
        spaceship.setenterXPointOfSpaceship(spaceship.getenterXPointOfSpaceship() - screenSpeedX);
        spaceship.setenterYPointOfSpaceship(spaceship.getenterYPointOfSpaceship() - screenSpeedY);
        planet.setX(planet.getX() - screenSpeedX);
        planet.setY(planet.getY() - screenSpeedY);
    }
    
    @Override
    public void actionPerformed(ActionEvent event){
        if(spaceship.getspaceshipMoving())spaceship.move();
        screenMoving();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.requestFocusInWindow();
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g2d);
        if(inGame){
            planet.paint(g2d);
            spaceship.paint(g2d);
        }
    }
    
}
