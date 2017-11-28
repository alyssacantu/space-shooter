/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

public interface ICollision 
{
    public boolean outOfSigth(int width,  int height);
    public boolean outOfSigth(int x, int y, int width,  int height);
}

