/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.components;
import java.awt.Graphics2D;
import mygame.engine.SimpleGame;

public class Shooter extends SimpleGame 
{
    private StartScreen sScreen;
    private Game inGame;
    private GameOverScreen gameOver;

    public Shooter(int width, int height) 
    {
        super(width, height);
        sScreen = new StartScreen();
        inGame = new Game();
        gameOver = new GameOverScreen(inGame);
    }

    @Override
    public void draw(Graphics2D g) 
    {       
        if (inGame.isGameOver()) 
        {
            gameOver.enable = true;
            gameOver.draw(g);
        }
        
        else {
             inGame.draw(g);
        }
        
        sScreen.draw(g);
    }

    @Override
    public boolean update() 
    {
        if(sScreen.getAlpha() == 0)
        {
        	inGame.update();
        }
               
        gameOver.update();
        
        if (inGame.isGameOver() && gameOver.getType() == 0) 
        {
            sScreen.setAlpha(1);
            sScreen.setEnable(true);
            inGame.init();
            gameOver.setType(-1);
        }
        
         if (inGame.isGameOver() && gameOver.getType() == 1) 
         {
            inGame.init();
            gameOver.setType(-1);
         }
        
        sScreen.update();
        return true;
    }

}
