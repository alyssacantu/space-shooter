/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */

package mygame.engine;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer implements LineListener 
{
	private Clip clip;
    private Boolean autoDestroy;
    AudioInputStream ais;
    AudioInputStream dais;

    public AudioPlayer(String path) 
    {
        init(path, false);
    }

    public AudioPlayer(String s, Boolean autoDestroy) 
    {
        init(s, autoDestroy);
    }

    public void setVolume(float vol) 
    {
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float gainAmount = (float) (Math.log(vol) / Math.log(10.0) * 20.0);
        volume.setValue(gainAmount);
    }

    public void play() 
    {
        if (clip == null) 
        {
            return;
        }
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() 
    {
        if (clip.isRunning()) 
        {
            clip.stop();
        }
    }

    public void close() 
    {
        stop();
        clip.close();
    }

    @Override
    public void update(LineEvent event) 
    {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) 
        {
            clip.close();
            try 
            {
                dais.close();
                ais.close();
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void init(String path, boolean autoDestroy) 
    {
        this.autoDestroy = autoDestroy;
        try {
            File file = new File(path);
            ais = AudioSystem.getAudioInputStream(file);

            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            
            dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();

            if (autoDestroy) 
            {
                clip.addLineListener(this);
            }

            clip.open(dais);
        } 
        
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) 
        {
            e.printStackTrace();
        }
    }
}
