/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.Graphics2D;

public interface IRender 
{
	// Draws to screen
     public void draw(Graphics2D g);
     
     // Must turn true in success
     public boolean update();
}
