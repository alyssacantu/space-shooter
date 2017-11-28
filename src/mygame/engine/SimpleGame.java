/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

public abstract class SimpleGame implements IRender 
{
    private int fps = 0;
    private final int width;
    private final int height;

    public SimpleGame(int width, int height) 
    {
        this.width = width;
        this.height = height;
    } 
 
    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }
}
