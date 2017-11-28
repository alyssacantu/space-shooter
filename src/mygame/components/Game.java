/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.components;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mygame.engine.AudioPlayer;
import mygame.engine.Sprite;

public class Game extends Sprite 
{
    private Hud hud;
    private Player pl;
    List<Bullet> bullets = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    public int gameScore = 0;
    private boolean gameOver;
    AudioPlayer explosion;

    public boolean isGameOver() 
    {
        return gameOver;
    }

    public Game() 
    {
        super("SShooter/Background/bg.png");
        this.x += this.width / 2;
        this.y += this.height / 2;

        pl = new Player(this);

        init();
    }

    @Override
    public void draw(Graphics2D g) 
    {
        super.draw(g);

        pl.draw(g);
        hud.draw(g);

        for (int j = bullets.size() - 1; j >= 0; j--) 
        {
            bullets.get(j).draw(g);
        }

        for (int j = enemies.size() - 1; j >= 0; j--) 
        {
            enemies.get(j).draw(g);
        }
    }

    @Override
    public void update() 
    {
        if (gameOver) 
        {
            return;
        }

        hud.setLives(this.pl.health);

        pl.update();

        if (enemies.size() < 3) 
        {
            spawnEnemies();
        }

        for (int j = bullets.size() - 1; j >= 0; j--) 
        {
            bullets.get(j).update();

            for (int i = enemies.size() - 1; i >= 0; i--) 
            {
                if (bullets.get(j).rectOverlap(enemies.get(i))) 
                {
                    enemies.get(i).setRemove(true);
                    bullets.get(j).setRemove(true);
                    gameScore++;
                    hud.setScore(gameScore);

                    explosion = new AudioPlayer("SShooter/bangSmall.wav", true);
                    explosion.play();
                }
            }

            if (bullets.get(j).isToRemove()) 
            {
                bullets.get(j).destroy();
                bullets.remove(j);
            }
        }

        for (int j = enemies.size() - 1; j >= 0; j--) 
        {
            enemies.get(j).update();

            if (enemies.get(j).rectOverlap(pl)) 
            {
                pl.health -= enemies.get(j).damage;
                enemies.get(j).setRemove(true);
            }

            if (enemies.get(j).isToRemove()) 
            {
                enemies.get(j).destroy();
                enemies.remove(j);
            }
        }

        if (pl.health <= 0) 
        {
            gameOver = true;
            for (int j = enemies.size() - 1; j >= 0; j--) 
            {
                enemies.get(j).destroy();
                enemies.remove(enemies.get(j));
            }
            
            for (int j = bullets.size() - 1; j >= 0; j--) 
            {
                bullets.get(j).destroy();
                bullets.remove(bullets.get(j));
            }
        }
    }

    private void spawnEnemies() 
    {
    	Enemy b;
    	
    	if (randomRange(1,2) == 2)
    	{
    		b = new Enemy2("SShooter/enemyShip.png", this, 1, randomRange(1, 2));
    	}
    	
    	else {
    		b = new Enemy("SShooter/meteorSmall.png", this, 1, randomRange(1, 4));;
    	}
    	
        b.x = randomRange(pl.width / 2, (int) (this.getWidth() - pl.getWidth() / 2));
        b.y = 0;
        enemies.add(b);
    }

    private int randomRange(int min, int max) 
    {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void init() 
    {
        pl.init();
        pl.x = (int) this.getWidth() / 2;
        pl.y = (int) (this.getHeight() + 20 - pl.getHeight());
        hud = new Hud(this);
        hud.y = 10;

        gameScore = 0;
        hud.setScore(0);
        hud.setLives(this.pl.health);
        this.gameOver = false;
    }
}
