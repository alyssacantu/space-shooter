/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.lang.reflect.Constructor; 

public class Stage extends Renderer 
{
	SimpleGame game;

    public Stage(int width, int height, Class c) 
    {
        super(width, height);

        Class[] type = {int.class, int.class};
        try {
            Constructor cons = c.getConstructor(type);
            Object[] obj = {width, height};
            game = (SimpleGame) cons.newInstance(obj);
        } 
        
        catch (Exception ex) 
        {
            System.err.println("Unable to instantiate the Game, make sure its Class extends from \"SimpleGame\"");
            ex.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g) 
    {
        g.setColor(new Color(100, 149, 237));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (game != null) 
        {
            game.draw(g);
        }

        // display frames per second...
        g.setFont(new Font("Courier New", Font.PLAIN, 12));
        g.setColor(Color.WHITE);
        g.drawString(String.format("FPS: %s", this.getFps()), 20, 20);
    }

    @Override
    public void update(int fps) 
    {
        if (game != null) 
        {
            Fps.getInstance().setFps(fps);
            this.setIsGameOver(!game.update());
        }

    }
}
