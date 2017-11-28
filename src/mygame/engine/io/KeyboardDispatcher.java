/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine.io;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;  

public class KeyboardDispatcher implements KeyEventDispatcher 
{
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) 
    {
        switch (e.getID()) 
        {
            case KeyEvent.KEY_PRESSED:
                Keyboard.getInstance().setKeyPressed(e);
                break;
            case KeyEvent.KEY_RELEASED:
                Keyboard.getInstance().setKeyReleased(e);
                break;
            case KeyEvent.KEY_TYPED:
                break;
            default:
                break;
        }
        e.consume();

        return false;
    }
}
