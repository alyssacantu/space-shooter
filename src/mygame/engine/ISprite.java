/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public interface ISprite 
{
	public void scale(int dWidth, int dHeight); 
    public void rotate( double angdeg);    
    public void destroy();    
    public void draw(Graphics2D g);
    public void update();    
}
