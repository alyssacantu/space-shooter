/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.image.BufferedImage;
import org.w3c.dom.Element;

public class SubTextureAttrib 
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int rotated;
    private String name = "";
    private final TextureAtlas atlas;

    public String getName() 
    {
        return name;
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }

    public int getRotated() 
    {
        return rotated;
    }

    public SubTextureAttrib(TextureAtlas atlas, Element subTextureElement) 
    {
        this.atlas = atlas;
        name = subTextureElement.getAttribute("name");
        x = Integer.parseInt(subTextureElement.getAttribute("x"));
        y = Integer.parseInt(subTextureElement.getAttribute("y"));
        width = Integer.parseInt(subTextureElement.getAttribute("width"));
        height = Integer.parseInt(subTextureElement.getAttribute("height"));
        //rotated = Integer.parseInt(subTextureElement.getAttribute("rotated"));
    }

    BufferedImage getTexture() 
    {
        return atlas.cropImage(this);
    }
}
