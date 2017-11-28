/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

public class Fps 
{
	int fps = 0;
    private Fps() {}

    public int getFps() 
    {
        return fps;
    }

    public void setFps(int fps) 
    {
        this.fps = fps;
    } 
    
    public static Fps getInstance() 
    {
        return FpsHolder.INSTANCE;
    }
    
    private static class FpsHolder 
    {
    	private static final Fps INSTANCE = new Fps();
    }
}
