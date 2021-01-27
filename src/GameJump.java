import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameJump {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 900;
    JFrame frame;
    GamePanel panel;
    
    public GameJump() {
        frame = new JFrame();
        panel = new GamePanel();
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);
        frame.setPreferredSize( new Dimension(WIDTH, HEIGHT) );
        frame.addKeyListener( panel );
        frame.add( panel );
        frame.pack();
    }
    
    public static void main(String[] args) {
        new GameJump();
    }
}
