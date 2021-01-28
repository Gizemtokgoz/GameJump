
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Wonka {
    public static final int JUMP_VELOCITY = 60;
    int x;
    int y;
    int width;
    int height;
    int xSpeed;
    int ySpeed;
    
    /*
     * To make gravity more realistic, we need an ever increasing gravity
     * velocity the longer Wonka is in the air. So we need
     * 1) a starting velocity variable
     * 2) a gravity increment variable that increases the total velocity
     * the longer Wonka is in the air.
     */
    int gravityIncrement = 2;
    int startGravity = 1;
    int gravity = startGravity;
    
    /*
     * Wonka will move upwards at a faster rate at the start of his jump than
     * at the end. This variable will slow down how fast he moves upward the
     * longer he is in the air.
     */
    int jumpDecayVelocity = 7;
    
    /*
     * These variables will tell us how much game time has elapsed.
     */
    int currentFrame = 0;
    int startJumpFrame = 0;
    
    /*
     * This variable will indicate if Wonka is currently jumping. Useful for
     * making sure Wonka doesn't 'double jump' while in mid-air.
     */
    boolean jumping = false;
    
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
    
    /*
     * Call this method when you want wonka to jump 
     */
    public void jump() {
        if( ! jumping ){
            ySpeed = Wonka.JUMP_VELOCITY;
            gravity = startGravity;
            startJumpFrame = this.currentFrame;
            jumping = true;
        }
    }
    
    public void update(int currentFrame) {
        this.currentFrame = currentFrame;
        x += xSpeed;
        y += gravity - ySpeed;
        
        /* 
         * Increasing gravity velocity over time
         */
        gravity += gravityIncrement;
        
        /*
         * Decaying jump velocity over time
         */
        if( jumping ) {
            if( (currentFrame - startJumpFrame) % 2 == 0 ) {
                ySpeed -= jumpDecayVelocity;
            }
        }
        
        // On the ground
        if( y > GameJump.HEIGHT - 50 - height) {
            y = GameJump.HEIGHT - 50 - height;
            jumping = false;
        }
    }
    
    public void draw(Graphics g) {
        /*
         * Different Wonka animations depending on whether he's jumping or
         * standing still
         */
        if( jumping ) {
            if( ySpeed > JUMP_VELOCITY / 2 ) {
                g.drawImage(wonkaJump1Image, x, y, width, height, null);
            } else {
                g.drawImage(wonkaJump2Image, x, y, width, height, null);
            }
        } else {
            g.drawImage(wonkaStandImage, x, y, width, height, null);
        }
    }
}
