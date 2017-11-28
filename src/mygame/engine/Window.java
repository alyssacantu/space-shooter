/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import javax.swing.JFrame;
import mygame.engine.io.FullScreenHandler;
import mygame.engine.io.KeyboardDispatcher;

public class Window 
{
    private static final int MAX_FRAMESKIP = 5;
    public static boolean DEBUG = false;

    private final Dimension windowSize;
    private final JFrame app;
    private Renderer stage;
    private final boolean isFullScreen;

    private final int width;
    private final int height;

    public Window(String title, int width, int height, boolean isFullScreen) 
    {
        this.width = width;
        this.height = height;

        // Get the size of the screen
        windowSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Create game window
        app = new JFrame();
        app.setTitle(title);
        app.setResizable(false);
        app.setLocationRelativeTo(null);
        app.setIgnoreRepaint(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.isFullScreen = isFullScreen;
    }

    public void start(int frames_per_second, Class game) 
    {
        if (isFullScreen) 
        {
            stage = new Stage(windowSize.width, windowSize.height, game);
            FullScreenHandler.init(app, stage);
            FullScreenHandler.getInstance().makeFullScreen();

        } 
        
        else {
            stage = new Stage(this.width, this.height, game);
        }

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyboardDispatcher());

        // Add canvas to game window
        app.add(stage.getCanvas());
        app.pack();
        app.setVisible(true);
        stage.init();

        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (true) 
        {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick
                    && loops < MAX_FRAMESKIP) 
            {
                stage.tick();

                next_game_tick += (1000 / frames_per_second);
                loops++;
            }
            
            // clear back buffer
            stage.render();

            if (stage.gameOver()) 
            {
                break;
            }

            // Let the OS have a little time
            Thread.yield();
        }

        if (isFullScreen) 
        {
            FullScreenHandler.getInstance().exitFullScreen();
        }

        System.exit(0);
    }
}
