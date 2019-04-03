package fullscreentest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FullScreenApp  extends JPanel implements ActionListener{
    private int fullHdY = 9;//пропорции по высоте
    private int fullhdX = 16;//пропорции по ширине
    private int xPointOfPlanet=800;
    private int yPointOfPlanet=800;
    private Image planet;
    private Timer timer;
    private boolean inGame = true;
    JButton button;
    
    private int stopPoint = 0;
    
    FullScreenApp(){
        loadImages();
        setBackground(Color.BLACK);
        initGame();
    }
    public void loadImages() {
        ImageIcon planetOpenFile = new ImageIcon("planet2.png");
        planet = planetOpenFile.getImage();
    }
    public void initGame(){
        timer = new Timer(250,this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent event){
        //mining();
        stopPoint++;
        if(stopPoint>20)System.exit(0);//выход их приложения через 12сек
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(planet, 0, 0, this);
            g.drawImage(planet, xPointOfPlanet, yPointOfPlanet, 
                    100, 100, null);
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
