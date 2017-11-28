/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.components;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import mygame.engine.Font;
import mygame.engine.Sprite;
import mygame.engine.io.Keyboard;

public class GameOverScreen extends Sprite 
{
    Font text = new Font("spaceArt/Bubblegum.ttf");
    private final Game game;
    public int type = -1;
    public boolean enable = false;

    GameOverScreen(Game game) 
    {
        super("SShooter/Background/bg.png");
        this.game = game;
        this.x += this.width / 2;
        this.y += this.height / 2;
    }

    @Override
    public void draw(Graphics2D g) 
    {
        super.draw(g);
        String lc = "Game Over";
        text.draw(lc, 30, 140, 50, g);
        
        String sc = "Score: " + this.game.gameScore;
        text.draw(sc, 60, 140 + 50, sc.length() * 5, g);
        
        String pw = "PRESS ENTER TO RESTART";
        text.draw(pw, 60, 170 + 50 + sc.length() * 5, sc.length() * 2, g);
        
        String sw = "OR PRESS ESC TO RETURN TO MENU";
        text.draw(sw, 20, 140 + 80 + (sc.length() * 5) + (sc.length() * 4), sc.length() * 2, g);
   }

    @Override
    public void update() 
    {
        if (enable && Keyboard.getInstance().isKeyPress(KeyEvent.VK_ESCAPE)) 
        {
            this.type = 0;
            this.enable = false;
        }

        if (enable && Keyboard.getInstance().isKeyPress(KeyEvent.VK_ENTER)) 
        {
            this.type = 1;
            this.enable = false;
        }
    }

    public int getType() 
    {
        return this.type;
    }

    void setType(int i) 
    {
        this.type = i;
    }
}
