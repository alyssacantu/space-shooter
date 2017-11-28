/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.demo.shooter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import mygame.engine.Font;
import mygame.engine.Sprite;

public class Hud extends Sprite 
{
    Font text = new Font("spaceArt/Bubblegum.ttf");
    Sprite stage;
    int score = 0;
    int life = 0;

    public Hud(Sprite stage) 
    {
        super(new BufferedImage((int) stage.getWidth(), (int) stage.getHeight(), BufferedImage.TYPE_INT_ARGB));
        this.stage = stage;
    }

    @Override
    public void draw(Graphics2D g) 
    {
        super.draw(g);
        String lc = " Lives: " + this.life;
        text.draw(lc, this.x, this.y + 15, 20, g);
        
        String sc = " Score: " + this.score;
        text.draw(sc, (int) (stage.getWidth() - sc.length() * 10), this.y + 15, 20, g);
    }

    public void setScore(int x) 
    {
        this.score = x;
    }

    public void setLives(int x) 
    {
        this.life = x;
    }

}
