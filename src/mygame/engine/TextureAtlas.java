/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project (Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TextureAtlas 
{
    private BufferedImage img;
    private int width;
    private int height;
    Map<String, SubTextureAttrib> items = new HashMap<String, SubTextureAttrib>();
    
    public TextureAtlas(String xmlPath) 
    {
        try {
            FileInputStream file = new FileInputStream(xmlPath);
            
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            
            String spritePath = document.getElementsByTagName("TextureAtlas").item(0).getAttributes().getNamedItem("imagePath").getNodeValue();
            spritePath = xmlPath.substring(0, xmlPath.indexOf("/", -1) + 1) + spritePath;
            
            img = ImageIO.read(new FileInputStream(spritePath));
            width = img.getWidth();
            height = img.getHeight();
            
            NodeList subTextureNodeList = document.getElementsByTagName("SubTexture");
            
            for (int i = 0; i < subTextureNodeList.getLength(); i++) 
            {
                Element subTextureElement = (Element) subTextureNodeList.item(i);
                String name = subTextureElement.getAttribute("name");

                items.put(name, new SubTextureAttrib(this,subTextureElement));
            }
            
        } 
        
        catch (ParserConfigurationException | SAXException | IOException ex) 
        {
            Logger.getLogger(TextureAtlas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage getTexture(String name) 
    {
        SubTextureAttrib text = items.get(name);
        return cropImage(text.getX(), text.getY(), text.getWidth(), text.getHeight());
    }
    
    public BufferedImage cropImage(int x, int y, int width, int height) 
    {
        return img.getSubimage(x, y, width, height); //fill in the corners of the desired crop location here 
    }
    
    public void destroy() 
    {
        this.img.flush();;
    }
    
    public Sprite getSprite(String name) 
    {
        return new Sprite(getTexture(name));
    }
    
    public MovieClip getMovieClip(String name)
    {
        return new MovieClip(this.getTextures(name));
    }
    
    public SubTextureAttrib[] getTextures(String prefix) 
    {
        List<SubTextureAttrib> textures = new ArrayList<SubTextureAttrib>();
        
        for (Map.Entry<String, SubTextureAttrib> entry : items.entrySet()) 
        {
            String key = entry.getKey();
            SubTextureAttrib val = entry.getValue();
            if (key.indexOf(prefix) == 0) 
            {                
                textures.add(val);
            }
        }
        
         Collections.sort(textures, new Comparator<SubTextureAttrib>() 
         {
                @Override
                public int compare(SubTextureAttrib lhs, SubTextureAttrib rhs) 
                { 
                    return lhs.getName().toLowerCase().compareTo(rhs.getName().toLowerCase());
                }
            });
        
         return textures.toArray(new SubTextureAttrib[textures.size()]);
    }

    BufferedImage cropImage(SubTextureAttrib attr) 
    {
        return this.cropImage(attr.getX(), attr.getY(), attr.getWidth(), attr.getHeight());
    }
    
}
