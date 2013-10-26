package Gra;

import Gra.Plansze.menu;
import Gra.Plansze.plansza1;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class Szkielet extends JFrame implements MouseListener, MouseMotionListener
{
        JPanel menu = new menu();  
        JPanel plansza1 = new plansza1();	
        //JPanel plansza2 = new plansza2();
        //JPanel plansza3 = new plansza3();
	//String sciezkaDoPliku, path_sciezki; //zmienna kontrolujaca grafike        	
        File icon = new File("src/Gra/Plansze/images/menu.jpg");
        BufferedImage image = ImageIO.read(icon);
        JTextArea WypiszInfo = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(WypiszInfo);
	public Szkielet() throws IOException 
        {               
            super("Gra Zamek");            
            addMouseListener(this);
            addMouseMotionListener(this);                    
            add(menu);             
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true); 
            setIconImage(image);  
            WypiszInfo.setForeground(Color.WHITE);
            WypiszInfo.setOpaque(false);//daje przezroczystość      
            WypiszInfo.setEnabled(true);
            WypiszInfo.setEditable(false);
            WypiszInfo.setVisible(true);             
        }
        /*
         * zmiana planszy gry
         */
        public void zmiana_planszy() throws InterruptedException
        {           
            remove(menu);        
            add(WypiszInfo);
            add(plansza1);            
            pack();               
            //960x692 - wymiar plansz                       
            WypiszInfo.setText("Tu będą wypisywane efekty komend"); 
            WypiszInfo.setBounds(250,600,460,92); 
        }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) 
    {
           try 
            {
                if (e.getX()>=555 && e.getX()<=950 && e.getY()>=304 && e.getY()<=367)
                    zmiana_planszy();
                if (e.getX()>=555 && e.getX()<=950 && e.getY()>=510 && e.getY()<=568)
                    System.exit(0);
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(Szkielet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}