/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gra.Plansze;

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
 * @author Szymon
 */
public class Komendy extends JPanel
{
    private BufferedImage image;    
    public Komendy()
    {
        super();
        File imageFile = new File("src/Gra/Plansze/images/pole_tekstowe.jpg");
        try 
        {
            image = ImageIO.read(imageFile);            
        } catch (IOException e) 
        {
            System.err.println("Blad odczytu obrazka");
        } 
        Dimension dimension = new Dimension(962,692);
        setPreferredSize(dimension);
    }
    @Override
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 538, this);        
    }    
}   
