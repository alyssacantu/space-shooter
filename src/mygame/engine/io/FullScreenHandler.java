/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine.io;

import java.awt.DisplayMode;
import javax.swing.JFrame;
import mygame.engine.Renderer;

public class FullScreenHandler 
{
    JFrame frame;
    Renderer rendere;

    public FullScreenHandler(JFrame frame, Renderer rendere) 
    {
        this.frame = frame;
        this.rendere = rendere;
    }

    public void makeFullScreen() 
    {  
    	this.frame.setUndecorated(true); 
    	
        // Change to full screen
        this.rendere.getGd().setFullScreenWindow(this.frame);

        if (this.rendere.getGd().isDisplayChangeSupported()) 
        {
            this.rendere.getGd().setDisplayMode(new DisplayMode(rendere.getWidth(), rendere.getHeight(), 32, DisplayMode.REFRESH_RATE_UNKNOWN));
        } 
    }

    public void exitFullScreen() 
    {
        this.rendere.getGd().setFullScreenWindow(null);
    }

    public static void init(JFrame frame, Renderer rendere) 
    {
        if (FullScreenHolder.INSTANCE == null) 
        {
            FullScreenHolder.INSTANCE = new FullScreenHandler(frame, rendere);
        }
    }

    public static FullScreenHandler getInstance() 
    {
        return FullScreenHolder.INSTANCE;
    }

    private static class FullScreenHolder 
    {
        private static FullScreenHandler INSTANCE = null;
    }
}
