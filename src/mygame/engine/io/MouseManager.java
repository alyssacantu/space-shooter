/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine.io;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener 
{
    public static int M_CLICK_RIGHT = 0;
    public static int M_CLICK_LEFT = 1;
    public static int M_CLICK_CENTER = 2;

    private int mouseX = 0;
    private int mouseY = 0;
    private boolean isFocus;

    private final boolean[] keys;
    
    //return degrees
    public double getAngle(int x, int y)
    { 
        return Math.toDegrees(Math.atan2(y - MouseManager.getInstance().getMouseY(), x - MouseManager.getInstance().getMouseX()));
    }

    public int getMouseX() 
    {
        return mouseX;
    }

    public int getMouseY() 
    {
        return mouseY;
    }

    private MouseManager() 
    {
        keys = new boolean[3];
    }

    public boolean isKeyPress(int key) 
    {
        if (key < 3 || key > 0) 
        {
            return keys[key];
        } 
        
        else {
            return false;
        }
    }

    public static MouseManager getInstance() 
    {
        return MouseManagerHolder.INSTANCE;
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        switch (e.getButton()) 
        {
            case MouseEvent.BUTTON1:
                keys[M_CLICK_RIGHT] = true;
                break;
            case MouseEvent.BUTTON2:
                keys[M_CLICK_CENTER] = true;
                break; 
            case MouseEvent.BUTTON3:
                keys[M_CLICK_LEFT] = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
       switch (e.getButton()) 
       {
            case MouseEvent.BUTTON1:
                keys[M_CLICK_RIGHT] = false;
                break;
            case MouseEvent.BUTTON2:
                keys[M_CLICK_CENTER] = false;
                break; 
            case MouseEvent.BUTTON3:
                keys[M_CLICK_LEFT] = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        this.isFocus = true;
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        this.isFocus = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) 
    {
       mouseX = e.getX();
       mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    void saySomething(String eventDescription, MouseEvent e) 
    {
        System.out.println(eventDescription
                + " (" + e.getX() + "," + e.getY() + ")"
                + " detected on "
                + e.getComponent().getClass().getName()
        );
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {}

    public boolean inFocus() 
    {
        return isFocus;
    } 

    private static class MouseManagerHolder 
    {
        private static final MouseManager INSTANCE = new MouseManager();
    }
}
