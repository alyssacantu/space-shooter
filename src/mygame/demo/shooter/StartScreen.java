/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.demo.shooter;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import mygame.engine.Sprite;
import mygame.engine.io.Keyboard;

public class StartScreen extends Sprite 
{
    public boolean enable = true;

    public StartScreen() 
    {
        super("SShooter/Background/splash.png");
        this.x += this.width / 2;
        this.y += this.height / 2;
    }

    @Override
    public void draw(Graphics2D g) 
    {
        if (this.getAlpha() > 0.0f) 
        {
            super.draw(g);
        }
    }

    @Override
    public void update() 
    {
        if (!enable && (this.getAlpha() > 0.0f)) 
        {
            this.setAlpha(this.getAlpha() - 0.05f);
        }

        if (enable && Keyboard.getInstance().isKeyPress(KeyEvent.VK_ENTER)) 
        {
            this.enable = false;
        }
    }

    public boolean isEnable() 
    {
        return this.enable;
    }

    void setEnable(boolean b) 
    {
        this.enable = b;
    }
}
