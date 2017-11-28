/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.Graphics2D; 

public class ParallaxHSprite extends Sprite 
{
	private Sprite layer1;
    private Sprite layer2;
    private int moveSpeed;

    public ParallaxHSprite(String path, int speed) 
    {
        super(path);
        this.moveSpeed = speed;

        layer1 = this.clone();
        layer1.setCenterPivot(false);
        layer2 = this.clone();
        layer2.setCenterPivot(false);
        layer2.x += layer1.getWidth();
    }

    @Override
    public void draw(Graphics2D g) 
    {
        layer1.draw(g);
        layer2.draw(g);
    }

    @Override
    public void update() 
    {
        this.layer1.x -= this.moveSpeed;
        this.layer2.x -= this.moveSpeed;
        
        if ((this.layer1.x + this.layer1.width) <= 0) 
        {
            this.layer1.x = this.layer1.width;
        }

        if ((this.layer2.x + this.layer2.width) <= 0) 
        {
            this.layer2.x = this.layer1.width;
        }
    }

}
