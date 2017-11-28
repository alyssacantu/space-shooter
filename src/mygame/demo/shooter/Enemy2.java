/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.demo.shooter;
import mygame.engine.Sprite;

class Enemy2 extends Enemy 
{
    public Enemy2(String pimg, Sprite stage, int _damage, int _speed) 
    {
        super(pimg, stage, _damage, _speed);
    }
    
    @Override
    public void update() 
    {
        super.update();
        this.rotation = 1; 
    } 
}
