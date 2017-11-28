/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.demo.shooter;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import mygame.engine.AudioPlayer;
import mygame.engine.Sprite;
import mygame.engine.io.Keyboard;

public class Player extends Sprite 
{
    private boolean leftDown = false;
    private boolean rightDown = false;
    private int shootCounter;
    private int shootCoolDown;
    private int speed;
    public int health;
    private boolean shootBullets = false;
    private Sprite stage;
    AudioPlayer laser;

    public Player(Sprite stage) 
    {
        super("SShooter/player.png");
        this.stage = stage;
        init();
    }

    @Override
    public void draw(Graphics2D g) 
    {
        super.draw(g);
    }

    @Override
    public void update() 
    {
        shootCounter++;
        if (leftDown) 
        {
            this.x -= speed;
        }

        if (rightDown) 
        {
            this.x += speed;
        }

        if (shootBullets) 
        {
            shoot();
        }

        //keeping the main character within bounds
        if (this.x <= this.width / 2) 
        {
            this.x += speed;
        }
        
        if (this.x >= stage.getWidth() - this.width / 2) 
        {
            this.x -= speed;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_LEFT)) 
        {
            leftDown = true;
        } 
        
        else {
            leftDown = false;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_RIGHT)) 
        {
            rightDown = true;
        } 
        
        else {
            rightDown = false;
        }

        if (Keyboard.getInstance().isKeyPress(KeyEvent.VK_SPACE)) {
            shootBullets = true;
        } 
        
        else {
            shootBullets = false;
        }
    }

    private void shoot() 
    {
        if (shootCounter > shootCoolDown) 
        {
            shootCounter = 0;

            laser = new AudioPlayer("SShooter/shoot.wav", true);
            laser.play();;

            Bullet b = new Bullet();
            b.x = this.x;
            b.y = this.y - 6 - this.height / 2;
            Game g = (Game) (stage);
            g.bullets.add(b);
        }
    }

    void init() 
    {
        speed = 8;
        health = 3;
        shootCoolDown = 10;
        shootCounter = 0;
    }
}
