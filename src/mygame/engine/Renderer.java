/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.AWTEvent;
import java.awt.Canvas; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import mygame.engine.io.MouseManager;

public abstract class Renderer 
{
	GraphicsDevice gd;

    private final Canvas canvas;
    private final BufferedImage buffer_canvas;
    private BufferStrategy buffer_stratergy;

    private boolean isGameOver = false;

    public void setIsGameOver(boolean isGameOver) 
    {
        this.isGameOver = isGameOver;
    }
    
    private int fps = 0;
    private int total_frames = 0;
    private long startTime = 0;

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }

    private int width;
    private int height;

    public Renderer(int width, int height) 
    {
    	this.width = width;
        this.height = height;

        canvas = new Canvas();
        
        canvas.addMouseListener(MouseManager.getInstance());
        canvas.addMouseMotionListener(MouseManager.getInstance());
        canvas.addMouseWheelListener(MouseManager.getInstance());

        canvas.setIgnoreRepaint(true);

        canvas.setSize(this.width, this.height);

        // Get graphics configuration
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        gd = ge.getDefaultScreenDevice();

        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        // Create off-screen drawing surface
        buffer_canvas = gc.createCompatibleImage(this.width, this.height);
    }

    public GraphicsDevice getGd() 
    {
        return gd;
    }

    public void init() 
    {
        // Create BackBuffer...
        canvas.createBufferStrategy(2);
        buffer_stratergy = canvas.getBufferStrategy();
    }

    public Canvas getCanvas() 
    {
        return canvas;
    }

    public void render() 
    {
    	Graphics2D g2d = buffer_canvas.createGraphics();
         
        this.draw(g2d);
        
        // Blit image and flip
        Graphics graphics = buffer_stratergy.getDrawGraphics();

        graphics.drawImage(buffer_canvas, 0, 0, null);

        if (!buffer_stratergy.contentsLost()) 
        {
            buffer_stratergy.show();
        }

        // release resources
        graphics.dispose();
        g2d.dispose();

    }

    public int getFps() 
    {
        return fps;
    }

    public void tick() 
    {
        total_frames++;
        if (System.currentTimeMillis() - startTime > 1000) 
        {
            startTime = System.currentTimeMillis();
            fps = total_frames;
            total_frames = 0;
        }
        
        if (getFps() > 10) 
        {
        	// wait for frames per seconds get settle
            this.update(getFps());
        }
        	
    }

    abstract public void draw(Graphics2D g);

    abstract public void update(int fps);

    public boolean gameOver() 
    {
        return isGameOver;
    }
}
