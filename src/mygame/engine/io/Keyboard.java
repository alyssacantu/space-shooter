/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine.io;

import java.awt.event.KeyEvent;

public class Keyboard 
{
    private final boolean[] keys;

    private Keyboard() 
    {
        keys = new boolean[256];
    }
    
    public boolean isKeyPress(int key)
    {
        return keys[key];
    }   

    public static Keyboard getInstance()
    {
        return KeyboardHolder.INSTANCE;
    }

    public void setKeyReleased(KeyEvent e) 
    {
        keys[e.getKeyCode()] = false;
    }

    public void setKeyPressed(KeyEvent e) 
    {
        keys[e.getKeyCode()] = true;
    }

    private static class KeyboardHolder 
    {
        private static final Keyboard INSTANCE = new Keyboard();
    }
}
