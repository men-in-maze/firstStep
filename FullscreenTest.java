package fullscreentest;

import java.awt.*;
import javax.swing.*;


public class FullscreenTest extends JFrame{
    public FullscreenTest(){
        setTitle("Planet");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        ImageIcon img = new ImageIcon("planet2.png");
        setIconImage(img.getImage());
    }
    public static void main(String[] args) {
        FullscreenTest gw = new FullscreenTest();
/*
        gw.setResizable(false);
        if (!gw.isDisplayable()){
            gw.setUndecorated(true);           
        }
*/
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(gw);
        gw.add(new FullScreenApp(gw.getHeight(), gw.getWidth()));
        gw.setVisible(true);
    }
}
