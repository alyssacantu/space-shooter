/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.components;
import mygame.engine.Sprite;

class Bullet extends Sprite 
{
    private int speed;
    private boolean toRemove = false;

    public boolean isToRemove() 
    {
        return toRemove;
    }

    public void setRemove(boolean b) 
    {
        toRemove = b;
    }

    public Bullet() 
    {
        super("SShooter/laserRed.png");
        speed = 10;
    }

    @Override
    public void update() 
    {
        this.y -= speed;

        if (this.y < 0) 
        {
            this.toRemove = true;
        }
    }
}
