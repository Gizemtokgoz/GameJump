
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
    BufferedImage wonkaStandImage;
    BufferedImage wonkaJump1Image;
    BufferedImage wonkaJump2Image;
    
    public Wonka(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        try {
            wonkaStandImage = ImageIO.read(this.getClass().getResourceAsStream("wonka_stand.png"));
            wonkaJump1Image = ImageIO.read(this.getClass().getResourceAsStream("wonka_jump1.png"));
            wonkaJump2Image = ImageIO.read(this.getClass().getResourceAsStream("wonka_jump2.png"));
        } catch(Exception e) {
            System.out.println( "ERROR: Unable to load images!" );
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
        if( ySpeed < JUMP_HEIGHT / 2 ) {
            g.drawImage(wonkaJump1Image, x, y, width, height, null);
        } else if (isJumping) {
            g.drawImage(wonkaJump2Image, x, y, width, height, null);
        } else {
            g.drawImage(wonkaStandImage, x, y, width, height, null);
        }
    }
}
