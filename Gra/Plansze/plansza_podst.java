/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gra.Plansze;

import Gra.Wojsko;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author s384080
 */
public class plansza_podst extends JPanel
{
    Wojsko wojsko=new Wojsko();
    BufferedImage image;    
    public plansza_podst(String plansza) 
    {
        super();          
        File imageFile = new File("src/Gra/Plansze/images/"+plansza);
        System.out.println("src/Gra/Plansze/images/"+plansza);
        try 
        {
            image = ImageIO.read(imageFile);            
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
        } 
        Dimension dimension = new Dimension(960,692);
        setPreferredSize(dimension);
        
    } 
    @Override
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}
