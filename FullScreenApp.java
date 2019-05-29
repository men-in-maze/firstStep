package fullscreentest;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FullScreenApp  extends JPanel implements ActionListener{
    private int fullhdY = 9;//пропорции по высоте
    private int fullhdX = 16;//пропорции по ширине
    private int screenHeight;
    private int screenWidth;
    private int screenSpeedX = 0;
    private int screenSpeedY = 0;
    private double distance;
    
    private MousePosition mousePosition;
    private List<ship> playerObjects;
    private List<ship> enemyObjects;
    //private List<gameObject> neutralStaticObjects;
    private Planet planet;
    private Player player;
    private List<String> playerResourses;
    private List<Image> playerInterfaceImages;
    private Timer timer;
    private boolean inGame = true;
            
    FullScreenApp(int screenHeight,int screenWidth){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        
        
        player = new Player((int) Math.round(Math.random() * 1000000000));
        
        playerResourses = new ArrayList<>();
        playerResourses.add(Integer.toString(player.getEnergy()));
        playerResourses.add(Integer.toString(player.getIron()));
        playerResourses.add(Integer.toString(player.getTitan()));
        playerResourses.add(Integer.toString(player.getHpOfMainSpaceship()));
        playerResourses.add(Integer.toString(player.getManpower()));
        
        
        playerInterfaceImages = new ArrayList<>();
        playerInterfaceImages.add(new ImageIcon("energy.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        playerInterfaceImages.add(new ImageIcon("iron.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        playerInterfaceImages.add(new ImageIcon("titan.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        playerInterfaceImages.add(new ImageIcon("kr2.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        playerInterfaceImages.add(new ImageIcon("men.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                
        playerObjects = new ArrayList<>();
        playerObjects.add(new spaceShip(screenHeight, screenWidth, ID.Player));
        playerObjects.add(new Fighter(150, 200, ID.Player, screenHeight, screenWidth));
        
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
            }
            @Override
            public void keyTyped(KeyEvent e) {} 
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                int mosePressedX = e.getPoint().x;
                int mosePressedY = e.getPoint().y;
                boolean find = false;
                for (ship playerObject : playerObjects) {
                    distance = Math.pow(mosePressedX - playerObject.getX() - playerObject.getShipWidth() / 2, 2)
                            + Math.pow(mosePressedY - playerObject.getY() - playerObject.getShipHeight() / 2, 2);
                    if(e.getButton() == 3 && playerObject.getspaceshipActive())
                        playerObject.spaceshipMoveTo(mosePressedX - playerObject.getShipWidth() / 2, mosePressedY - playerObject.getShipHeight() / 2);
                    if(e.getButton() == 1 && distance <= 1700){
                        if(find == false)playerObject.setspaceshipActive(true);
                        else if(find == true)playerObject.setspaceshipActive(false);
                        find = true;
                    }
                    else if(e.getButton() == 1 && distance > 1700)playerObject.setspaceshipActive(false);
                }
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
            screenSpeedX = 2 * Math.round((mousePosition.getX() - (screenWidth / 2))/(screenWidth/fullhdX));
            screenSpeedY = -20;
        }
        else if(mousePosition.getY() > (screenHeight - 10)){
            screenSpeedX = 2 * Math.round((mousePosition.getX() - (screenWidth / 2))/(screenWidth / fullhdX));
            screenSpeedY = 20;
        }
        else if(mousePosition.getX() < 10){
            screenSpeedY = 2 * Math.round(((mousePosition.getY() - (screenHeight / 2)) * 2)/(screenHeight/fullhdY));
            screenSpeedX = -20;
        }
        else if(mousePosition.getX() > (screenWidth - 10)){
            screenSpeedY = 2 * Math.round(((mousePosition.getY() - (screenHeight / 2)) * 2)/(screenHeight/fullhdY));
            screenSpeedX = 20;
        }
        else{
            screenSpeedX = 0;
            screenSpeedY = 0;
        }
        for (ship playerObject : playerObjects) {
            playerObject.setX(playerObject.getX() - screenSpeedX);
            playerObject.setY(playerObject.getY() - screenSpeedY);
            playerObject.setenterXPointOfSpaceship(playerObject.getenterXPointOfSpaceship() - screenSpeedX);
            playerObject.setenterYPointOfSpaceship(playerObject.getenterYPointOfSpaceship() - screenSpeedY);
        }
        planet.setX(planet.getX() - screenSpeedX);
        planet.setY(planet.getY() - screenSpeedY);
    }
    
    @Override
    public void actionPerformed(ActionEvent event){
        for (ship playerObject : playerObjects) {
            if(playerObject.getspaceshipMoving())playerObject.move();
        }
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
            for (ship playerObject : playerObjects) {
                playerObject.paint(g2d);
            }
            g2d.setColor(Color.white);
            for(int i = 0, xin = 80, yin = 25; i < playerResourses.size(); i++, xin+=100){
                g2d.drawImage(playerInterfaceImages.get(i), xin - 40, yin - 20, null);
                g2d.drawString(playerResourses.get(i), xin, yin);
            }
            
        }
    }
    
}
