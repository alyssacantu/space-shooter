/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demo;

import mygame.components.Shooter;
import mygame.engine.Window;

public class Main 
{
	public static void main(String[] args) 
	{
    	Window win1 = new Window("Space Shooter", 320, 568, false); 
    	win1.start(60, Shooter.class);
    }

}
