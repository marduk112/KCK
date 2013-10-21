/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra.Plansze;

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
    private BufferedImage image; 
    public plansza_podst() 
    {
        super(); 
        File imageFile = new File("src/KCK/Gra/menu.jpg");//tu będzie ładowana plansza
        try 
        {
            image = ImageIO.read(imageFile);
        } catch (IOException e) 
        {            
            System.err.println("Błąd odczytu obrazka");
        } 
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    } 
    @Override
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

}
