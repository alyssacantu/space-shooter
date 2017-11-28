/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.engine;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Font {
    java.awt.Font font;
    public Font(String path)
    {
        try 
        {
        	font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File(path));
        } 
        
        catch (FontFormatException ex) 
        {
        	Logger.getLogger(Font.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        catch (IOException ex) 
        {
            Logger.getLogger(Font.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private java.awt.Font make(int size)
    {
        return font.deriveFont(java.awt.Font.PLAIN, size);
    }
    
    public void draw(String text, int x, int y, int size, Graphics2D g)
    {
        java.awt.Font pfont = g.getFont();
        Color pcolor = g.getColor();
        
        g.setFont(make(size)); //(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));//
        g.setColor(Color.WHITE);
        g.drawString(text, x, y+size/2);
        
        g.setFont(pfont);
        g.setColor(pcolor);
        
    }
}