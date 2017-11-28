/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.components;
import mygame.engine.Sprite;

class Enemy extends Sprite 
{
    private int speed;
    public int damage;
    private Sprite stage;
    private boolean toRemove = false;

    public Enemy(String pimg, Sprite stage, int _damage, int _speed) 
    {
        super(pimg);
        this.stage = stage;
        this.speed = _speed;
        this.damage = _damage;
    }
       
    @Override
    public void update() 
    {
        this.y += speed;
        this.rotation += speed;

        if (this.y > stage.getHeight() + this.getHeight()) 
        {
            this.toRemove = true;
        }
    }

    public void setRemove(boolean b) 
    {
        toRemove = b;
    }

    public boolean isToRemove() 
    {
        return toRemove;
    }
}
