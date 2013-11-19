package Gra;

import Gra.Plansze.menu;
import Gra.Plansze.plansza1;
import Gra.Plansze.plansza2;
import Gra.Plansze.plansza3;
import Gra.Plansze.plansza_podst;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
public class Szkielet extends JFrame implements MouseListener, MouseMotionListener
{        
    private SAXBuilder builder = new SAXBuilder();
    private File xmlFile = new File("src/gwiazdy.xml");
    private Document document;
    private plansza_podst plansza;  
    private static String wybor;           	
    private final File icon = new File("src/KCK/Gra/Plansze/images/menu.jpg");
    private final BufferedImage image;
    private final JTextArea wypiszInfo = new JTextArea("Tu będą wypisywane efekty rozkazów\n\n");
    private final JTextField komendy = new JTextField("Tu wpisujemy polecenia"); 
    private final JScrollPane scrollPane = new JScrollPane(wypiszInfo);    
    private void Interfejs()
    {         
        scrollPane.setViewportView(wypiszInfo);
        javax.swing.GroupLayout planszaLayout = new javax.swing.GroupLayout(plansza);
        plansza.setLayout(planszaLayout);
        planszaLayout.setHorizontalGroup
        (                
            planszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)            
            .addComponent(scrollPane, 300, 662, 962)
            .addComponent(komendy, 300, 662, 962)
        );
        planszaLayout.setVerticalGroup
        (
            planszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, planszaLayout.createSequentialGroup()
                .addGap(0, 692, 692)
                .addComponent(komendy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup
        (
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plansza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup
        (
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plansza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        ); 
        pack();
    }
    /*
    * zdarzenie klawiszowe i obsluga komend
    * tu normalnie będzie za pomocą xml-a, aktualna metoda jest tylko w celach kontrolno/sprawdzających
    */    
    private void komendyActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException, IOException
    {         
        if (!"".equals(komendy.getText()) && komendy.getText()!=null && !"\n".equals(komendy.getText()))
        {
            plansza1 plansza1 = new plansza1();
            plansza2 plansza2 = new plansza2();
            plansza3 plansza3 = new plansza3();
            System.out.println(komendy.getText()+" "+plansza.getClass().toString());//funkcja kontrolna
            wybor=komendy.getText().toLowerCase();       
            if (plansza.getClass().toString().contains("plansza3"))//komendy dla 3 piętra  
            {
                switch(wybor)
                {
                    case "przejdź do 1 piętra":
                        zmiana_planszy("plansza1");
                        wypiszInfo.append("Przeszedłeś na 1 piętro\n");
                        break;
                    case "przejdź do 2 piętra":
                        zmiana_planszy("plansza2");
                        wypiszInfo.append("Przeszedłeś na 2 piętro\n");
                        break;
                    case "przejdź do 3 piętra":
                        wypiszInfo.append("Już jesteś na 3 piętrze\n");
                        break;                     
                    case "walcz":
                        wypiszInfo.append(plansza3.Walka()+"\n");
                        break;
                    case "wyjdź z gry":
                        System.exit(0); 
                        break;
                    default:
                        wypiszInfo.append("Źle wprowadziles polecenie\n");
                }
            }
            else if(plansza.getClass().toString().contains("plansza2"))//komendy dla 2 piętra
            {
                switch(wybor)
                {
                    case "przejdź do 1 piętra":
                        zmiana_planszy("plansza1");
                        wypiszInfo.append("Przeszedłeś na 1 piętro\n");
                        break;
                    case "przejdź do 2 piętra":
                        wypiszInfo.append("Już jesteś na 2 piętrze\n");
                        break;
                    case "przejdź do 3 piętra":
                        zmiana_planszy("plansza3");
                        wypiszInfo.append("Przeszedłeś na 3 piętro\n");
                        break;         
                    case "liczba żołnierzy":
                        wypiszInfo.append(plansza2.Liczebnosc_Wojska()+"\n");
                    case "ulepsz":
                        if (wybor.contains("piechura"))
                            wypiszInfo.append(plansza2.ulepszenia("piechur")+"\n");
                        if (wybor.contains("kusznika"))
                            wypiszInfo.append(plansza2.ulepszenia("kusznik")+"\n");
                        if (wybor.contains("husarza"))
                            wypiszInfo.append(plansza2.ulepszenia("husarz")+"\n");
                        break;
                    case "wyjdź z gry":
                        System.exit(0); 
                        break;
                    default:
                        wypiszInfo.append("Źle wprowadziles polecenie\n");
                }
            }
            else if (plansza.getClass().toString().contains("plansza1"))//komendy dla 1 piętra
            {
                switch(wybor)
                {
                    case "przejdź do 1 piętra":
                        wypiszInfo.append("Już jesteś na 1 piętrze\n");
                        break;
                    case "przejdź do 2 piętra":
                        zmiana_planszy("plansza2");
                        wypiszInfo.append("Przeszedłeś na 2 piętro\n");
                        break;
                    case "przejdź do 3 piętra":
                        zmiana_planszy("plansza3");
                        wypiszInfo.append("Przeszedłeś na 3 piętro\n");
                        break;    
                    case "wyświetl statystyki":
                        wypiszInfo.append(plansza1.wyswietlstatystyki()+"\n");//nie wiem czy to zostawic w tym pietrze, czy dać do zbrojowni
                        break;
                    case "wyświetl zasoby":
                        wypiszInfo.append(plansza1.Zasoby()+"\n");
                        break;
                    case "wyjdź z gry":
                        System.exit(0); 
                        break;
                    default:
                        wypiszInfo.append("Źle wprowadziles polecenie\n");
                }
            }
            else
                wypiszInfo.append("Źle wprowadziles polecenie\n");
            komendy.setText("");
        }        
    }    
    /*public Szkielet(Element node) 
    {
        
        if (!node.getName().equals("gwiazda")) 
        {
            throw new RuntimeException("Wrong element type");
        } 
        this.nazwisko = node.getChildText("nazwisko");
 
        for (Element el : (List<Element>) node.getChildren("adres")) {
            this.adresy.add(el.getText());
        }
 
        Element filmy = node.getChild("filmy");
 
        if (filmy != null) {
            for (Element el : (List<Element>) filmy.getChildren("film")) {
                this.filmy.add(new Film(el));
            }
        }
    }*/
    public Szkielet() throws IOException, JDOMException 
    {        
        super("Gra Zamek");  
        this.image = ImageIO.read(icon);
        /*this.document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement();
        //setResizable(false);
        List list = rootNode.getChildren("gwiazda"); 
        for (int i = 0; i<list.getSize(); i++) 
        {
            Element node = (Element) list.getItem(i);
            Szkielet gw = new Szkielet(node);
            res.add(gw);
        }*/
        wypiszInfo.setColumns(80);
        wypiszInfo.setRows(5);
        plansza = new menu();        
        addMouseListener(this);
        addMouseMotionListener(this);                
        add(plansza);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setVisible(true); 
        setIconImage(image);  
        wypiszInfo.setForeground(Color.BLACK);             
        wypiszInfo.setEnabled(true);
        wypiszInfo.setEditable(false);
        wypiszInfo.setVisible(true);        
        komendy.setForeground(Color.BLACK);
        komendy.setEditable(true);
        komendy.setEnabled(true);
        komendy.setVisible(true);       
        pack();        
        komendy.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                try 
                {
                    komendyActionPerformed(evt);
                } catch (InterruptedException | IOException ex) 
                {
                    Logger.getLogger(Szkielet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });        
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
        if ("nowa gra".equals(wybor) || "plansza3".equals(wybor))
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
                    try 
                    {
                        zmiana_planszy("nowa gra");
                    } catch (IOException ex) 
                    {
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