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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Szkielet extends JFrame implements MouseListener, MouseMotionListener
{        
    JPanel plansza;  
    static String wybor;
    //String sciezkaDoPliku, path_sciezki; //zmienna kontrolujaca grafike        	
    final File icon = new File("src/KCK/Gra/Plansze/images/menu.jpg");
    final BufferedImage image = ImageIO.read(icon);
    public JTextArea wypiszInfo = new JTextArea("Tu będą wypisywane efekty rozkazów");
    public JTextField komendy = new JTextField("Tu wpisujemy polecenia"); 
    private final JScrollPane scrollPane = new JScrollPane(wypiszInfo);
    private void Interfejs()
    {       
        scrollPane.setViewportView(wypiszInfo);
        javax.swing.GroupLayout planszaLayout = new javax.swing.GroupLayout(plansza);
        plansza.setLayout(planszaLayout);
        planszaLayout.setHorizontalGroup(
            planszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)            
            .addComponent(scrollPane, 300, 662, 962)
            .addComponent(komendy, 300, 662, 962)
        );
        planszaLayout.setVerticalGroup(
            planszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, planszaLayout.createSequentialGroup()
                .addGap(0, 692, 692)
                .addComponent(komendy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plansza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plansza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        ); 
        pack();
    }
    public Szkielet() throws IOException 
    {
        super("Gra Zamek");  
        //setResizable(false);
        wypiszInfo.setColumns(85);
        wypiszInfo.setRows(5);
        plansza = new menu();        
        addMouseListener(this);
        addMouseMotionListener(this);                
        add(plansza);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setVisible(true); 
        setIconImage(image);  
        wypiszInfo.setForeground(Color.BLACK);
        //wypiszInfo.setOpaque(false);//daje przezroczystość      
        wypiszInfo.setEnabled(true);
        wypiszInfo.setEditable(false);
        wypiszInfo.setVisible(true);        
        komendy.setForeground(Color.BLACK);
        komendy.setEditable(true);
        komendy.setEnabled(true);
        komendy.setVisible(true);       
        pack();
    }
    /*
     * zmiana planszy gry
     */
    public void zmiana_planszy(String wybor) throws InterruptedException, IOException
    {
        //plansza.removeAll();
        //Interfejs();
        remove(plansza);         
        //add(scrollPane);
        add(komendy); 
        add(wypiszInfo);
        //komendy.setBounds(250,580,460,25);
        //960x692 - wymiar plansz     
        //wypiszInfo.setBounds(250,600,460,82);//wpierw podajemy współrzedne w poziomie i pionie lewego, górnego rogu, potem wielkość w poziomie i pionie        
        if ("nowa gra".equals(wybor) || "plansza1".equals(wybor))
        {
            plansza = new plansza3();
            add(plansza);
            pack();
        }        
        if ("plansza2".equals(wybor))
        {
            plansza = new plansza2();
            add(plansza);            
            pack();      
        }
        if ("plansza1".equals(wybor))
        {
            plansza = new plansza1();
            add(plansza);            
            pack();      
        }     
        Interfejs();        
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
                    try {
                        zmiana_planszy("nowa gra");
                } catch (IOException ex) {
                    Logger.getLogger(Szkielet.class.getName()).log(Level.SEVERE, null, ex);
                }
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