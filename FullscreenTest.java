package fullscreentest;

import java.awt.*;
import javax.swing.*;


public class FullscreenTest extends JFrame{
    public FullscreenTest(){
        setTitle("Planet");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new FullScreenApp());
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
        System.out.println(gw.getHeight());
        //высота полноэкранного приложения(1080 для фулhd)
        gw.setVisible(true);
    }
}
