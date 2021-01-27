
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Wonka {
    public static final int JUMP_HEIGHT = -60;
    int x;
    int y;
    int width;
    int height;
    int xSpeed;
    int ySpeed;
    int startGravity = 1;
    int gravity = startGravity;
    boolean isJumping = false;
    BufferedImage wonkaImage;
    
    public Wonka(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        try {
            wonkaImage = ImageIO.read(this.getClass().getResourceAsStream("wonka.png"));
        } catch(Exception e) {
            System.out.println( "ERROR: Unable to load iamge!" );
        }
    }
    
    public void update() {
        x += xSpeed;
        y += ySpeed + gravity;
        
        if( y > GameJump.HEIGHT - 50 - height) {
            y = GameJump.HEIGHT - 50 - height;
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(wonkaImage, x, y, width, height, null);
    }
}
