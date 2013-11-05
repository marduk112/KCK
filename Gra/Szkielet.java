package Gra;

import Gra.Plansze.menu;
import Gra.Plansze.plansza1;
import Gra.Plansze.plansza2;
import Gra.Plansze.plansza3;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
public class Szkielet extends JFrame implements MouseListener, MouseMotionListener
{
    private String wybor;
    private JPanel plansza;     
    //String sciezkaDoPliku, path_sciezki; //zmienna kontrolujaca grafike        	
    private final File icon = new File("src/KCK/Gra/Plansze/images/menu.jpg");
    private final BufferedImage image = ImageIO.read(icon);
    JTextArea wypiszInfo = new JTextArea();
    JTextPane komendy = new JTextPane();
    private final JScrollPane scrollPane = new JScrollPane(wypiszInfo);
    public Szkielet() throws IOException 
    {
        super("Gra Zamek");           
        setResizable(false);
        plansza = new menu();
        addMouseListener(this);
        addMouseMotionListener(this);                    
        add(plansza);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true); 
        setIconImage(image);  
        wypiszInfo.setForeground(Color.WHITE);
        wypiszInfo.setOpaque(false);//daje przezroczystość      
        wypiszInfo.setEnabled(true);
        wypiszInfo.setEditable(false);
        wypiszInfo.setVisible(true);    
        komendy.setForeground(Color.WHITE);
        komendy.setEditable(true);
        komendy.setEnabled(true);
        komendy.setVisible(true);
    }
    /*
     * zmiana planszy gry
     */
    public void zmiana_planszy(String wybor) throws InterruptedException
    {
        plansza.removeAll();
        remove(plansza);        
        add(wypiszInfo);
        add(komendy);         
        komendy.setBounds(250,580,460,20);
        //960x692 - wymiar plansz    
        wypiszInfo.setText(wypiszInfo.getText()+"Tu będą wypisywane efekty rozkazów\n");
        wypiszInfo.setBounds(250,600,460,92);//wpierw podajemy współrzedne w poziomie i pionie lewego, górnego rogu, potem wielkość w poziomie i pionie
        if ("nowa gra".equals(wybor) || "plansza1".equals(wybor))
        {
            plansza = new plansza1();
            add(plansza);            
            pack();  
            JOptionPane.showMessageDialog(null,"Witam w pokoju króla");
        }        
        if ("plansza2".equals(wybor))
        {
            plansza = new plansza2();
            add(plansza);            
            pack();      
        }
        if ("plansza3".equals(wybor))
        {
            plansza = new plansza3();
            add(plansza);            
            pack();      
        }        
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) 
    {        
        wybor = plansza.getClass().toString();
        if (wybor.contains("menu"))
        {
           try 
            {
                if (e.getX()>=555 && e.getX()<=950 && e.getY()>=304 && e.getY()<=367)
                    zmiana_planszy("nowa gra");
                if (e.getX()>=555 && e.getX()<=950 && e.getY()>=510 && e.getY()<=568)
                    System.exit(0);
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(Szkielet.class.getName()).log(Level.SEVERE, null, ex);
            }
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