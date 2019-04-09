package fullscreentest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FullScreenApp  extends JPanel implements ActionListener{
    private int fullHdY = 9;//пропорции по высоте
    private int fullhdX = 16;//пропорции по ширине
    private int screenHeight;
    private int screenWidth;
    private int xPointOfPlanet = 250;
    private int yPointOfPlanet = 600;
    private int xPointOfSpaceship;
    private int yPointOfSpaceship;
    private Image planet,spaceship;
    private Timer timer;
    private boolean inGame = true;
    JButton exitButton;
    
    FullScreenApp(int screenHeight,int screenWidth){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        xPointOfSpaceship = screenWidth/2 - screenWidth/32 + 12;
        yPointOfSpaceship = screenHeight/2 - screenHeight/32 - 2;
        loadImages();
        setBackground(Color.BLACK);
        initGame();
        exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        }); 
        add(exitButton);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println(e.getPoint().x + " : " + e.getPoint().y);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                xPointOfPlanet += xPointOfSpaceship + screenWidth/32 - e.getPoint().x;
                yPointOfPlanet += yPointOfSpaceship + screenWidth/32 - e.getPoint().y;
                System.out.println(e.getPoint().x + " : " + e.getPoint().y);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }

        });
    }
    public void loadImages() {
        ImageIcon planetOpenFile = new ImageIcon("planet.png");
        planet = planetOpenFile.getImage();
        ImageIcon spaceshipOpenFile = new ImageIcon("spaceship.png");
        spaceship = spaceshipOpenFile.getImage();
        
    }
    public void initGame(){
        timer = new Timer(200,this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent event){
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(planet, 
                    xPointOfPlanet, 
                    yPointOfPlanet, 
                    100, 
                    100, 
                    null);
            g.drawImage(spaceship, 
                    xPointOfSpaceship, 
                    yPointOfSpaceship, 
                    screenWidth/16, 
                    screenWidth/16, 
                    null);
        }
/*
        super.paintComponent(g);
        if(inGame){
            g.drawImage(worker, 
                    worker1.getCoorX(), 
                    worker1.getCoorY(), 
                    this);
            g.drawImage(stone, 
                    xPointOfStone, 
                    yPointOfStone, 
                    this);
            String stoneMine = Integer.toString(stoneMined);
            g.setColor(Color.white);
            g.drawString(stoneMine, 20, 30);
        }
*/
    }
}
