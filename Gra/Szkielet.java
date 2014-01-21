package Gra;

import Gra.Plansze.Komendy;
import Gra.Plansze.Statystyki;
import Gra.Plansze.menu;
import Gra.Plansze.plansza1;
import Gra.Plansze.plansza2;
import Gra.Plansze.plansza3;
import Gra.Plansze.plansza_podst;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Szkielet extends JFrame implements MouseListener, MouseMotionListener, KeyListener
{        
    /*private SAXBuilder builder = new SAXBuilder();
    private File xmlFile = new File("src/interpreter.xml");
    private Document document;*/
    //private Player animacja = Manager.createPlayer(myURL);
    private plansza_podst plansza;//laduje menu
    private static String wybor;//przechowuje wprowadzone przez uzytkownika polecenie           	
    private final File icon = new File("src/Gra/Plansze/images/menu.jpg");//ikona programu
    private BufferedImage image;//ladowanie ikony
    //tablica z wszystkimi misjami
    private final String[] misje={"","- Krolu! Brakuje nam zlota w skarbcu, wyslij armie do pobliskiej wioski, aby zebrali podatki.","- Witaj krolu, to ja Twoj  blazen. Wyjrzyj przez okno jaka jest dzis pogoda, a dostaniesz 300 sztuk zlota."};
    //tablica z aktualnie wykonywanymi misjami,przechowuje nr indeksu z tablicy misje
    private List<Integer> aktualne = new ArrayList<>();
    //rodzaje pogody wystepujace w grze
    private String[] pogoda={"slonecznie","pochmurno","pada deszcz"};
    private WypiszInfo wypiszInfo = new WypiszInfo();
    private final JTextField komendy = new JTextField(); //pole w ktorym wpisujemy polecenia
    private final JScrollPane scrollPane = new JScrollPane(wypiszInfo); //pasek zawijania dla pola tekstowego przechowujacego efekty dzialania komend(odpowiedzi komputera)
    private final JScrollPane zawijanie = new JScrollPane();
    private final Warunki warunki = new Warunki();
    private plansza1 plansza1 = new plansza1();//I pietro-ladowanie planszy
    private plansza2 plansza2 = new plansza2();//II pietro-ladowanie planszy
    private plansza3 plansza3 = new plansza3();//III pietro-ladowanie planszy
    private boolean pogoda_wykonana=false; // sprawdzanie czy misja sprawdzenia pogody zostala wykonana
    private int misja=-1;//wybor nr misji
    private int dzien=0;//dzien w grze
    private int dzien_misja_1=0;
    private boolean zdarzenie_losowe_wiesniacy=false;
    private boolean tworzenie_piechurow=false;
    private boolean tworzenie_kusznikow=false;
    private boolean tworzenie_husarzy=false;
    private int liczba_jednostek;
    private boolean malo_kamien=false;
    private boolean malo_drewno=false;
    private boolean malo_diament=false;
    private boolean zamiana_zasobow;
    private boolean przyjecie_zasobow;
    private int planowany_atak=-1;
    private String max_zasob;
    private String inny_zasob;
    private String zasob;
    private boolean pomoc;
    private Interpreter xml=new Interpreter();
    private int interpreter;
    private String temp;
    private boolean misja2=false;
    private int poziom_trudnosci_bandyci=0;
    private boolean atak_bandytow;
    private int poziom_trudnosci_wrog=0;
    private boolean misja3=false; 
    private Statystyki Statystyki; 
    private final Komendy komendy_pole = new Komendy();
    private javax.swing.JLabel Statystyki_Wartosc;
    private void Interfejs() // wstawanianie pol tekstowych, ikon, paneli
    {        
        scrollPane.setViewportView(wypiszInfo);        
        warunki.setColumns(10);
        warunki.setRows(3);
        zawijanie.setViewportView(warunki);
        javax.swing.GroupLayout planszaLayout = new javax.swing.GroupLayout(plansza);
        plansza.setLayout(planszaLayout);
        javax.swing.GroupLayout StatystykiLayout = new javax.swing.GroupLayout(Statystyki);
        Statystyki.setLayout(StatystykiLayout);
        StatystykiLayout.setHorizontalGroup(//wspolrzedne osi OX
            StatystykiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)                         
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StatystykiLayout.createSequentialGroup()
                        .addContainerGap(480, Short.MAX_VALUE)
                        .addComponent(Statystyki_Wartosc)  
                          .addGap(380, 380, 380))                                        
        );
        StatystykiLayout.setVerticalGroup(//wspolrzedne osi OY
            StatystykiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)            
                .addGroup(StatystykiLayout.createSequentialGroup()                 
                .addGap(250, 250, 250)     
                .addComponent(Statystyki_Wartosc)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        planszaLayout.setHorizontalGroup
        (                
            planszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)             
                //.addGap()               
            .addComponent(scrollPane, 300, 962, 962)
            .addComponent(komendy, 300, 662, 962)
                    .addComponent(komendy_pole, 300, 662, 962)
            .addGroup(planszaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(zawijanie, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(285, Short.MAX_VALUE))                
                .addComponent(Statystyki,javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 582, 962)
                //.addContainerGap(111, Short.MAX_VALUE));           
        );
        planszaLayout.setVerticalGroup
        (
            planszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, planszaLayout.createSequentialGroup()
                .addGap(0, 692, 692)
                .addComponent(komendy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(planszaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zawijanie, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
                .addGap(451, 551, 692)
                .addComponent(Statystyki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)                    
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, planszaLayout.createSequentialGroup()
                .addContainerGap(640, 640)
                .addComponent(komendy_pole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 692, 692))
               // .addContainerGap(102, Short.MAX_VALUE))
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
    * tu normalnie bedzie za pomoca xml-a, aktualna metoda jest tylko w celach kontrolno/sprawdzajacych
    */    
    private void komendyActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException, IOException
    {                 
        boolean wykonane = false;        
        wybor=komendy.getText().toLowerCase().trim();       
        warunki.setText("");                
        if (aktualne.contains(Integer.valueOf(0)))
        {
            if (plansza3.Armia_Zasoby().getDefence()>=100)
            {
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Spelniles warunki misji(zdobycie obrony armii >=100)\n");
                wypiszInfo.setEditable(false);
            }
            else
            {                
                warunki.append("-Masz "+plansza3.Armia_Zasoby().getDefence()+" punktow obrony,\npotrzebujesz 100(pozostalo "+(5-dzien)+" dni)\n");
            }
        }
        if (dzien==6 && misja2==false)
        {
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Masz kolejna misje do wykonania, wygraj\n co najmniej 5 walk\n");
            misja2=true;  
            wypiszInfo.setEditable(false);
        }
        if (misja2==true)
            warunki.append("Wygrales "+plansza3.Liczba_Walk_Wygranych()+" walk na 5");
        if (misja2==true && plansza3.Liczba_Walk_Wygranych() >= 5)
        {
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.black,"Brawo, wygrales "+plansza3.Liczba_Walk_Wygranych()+" walk\n");
            misja2=false;
            wypiszInfo.setEditable(false);
        }
        if (dzien>6 && misja2==false)
        {
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Sasiad szykuje inwazje na twoje krolestwo.\nZaatakuj go pierwszy\n");
            warunki.append("Sasiad szykuje inwazje na twoje krolestwo.\nZaatakuj go pierwszy\n");
            misja3=true;
            wypiszInfo.setEditable(false);
        }
        if (misja3==true)
        {
            warunki.append("Sasiad szykuje inwazje na twoje krolestwo.\nZaatakuj go pierwszy\n");            
        }
        if (wybor.contains("jak") && (wybor.contains("cos") || wybor.contains("cos")) && (wybor.contains("robic") || wybor.contains("robic")))
        {
            wykonane = true;
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.RED,"Wpisz odpowiednie polecenie w konsoli. Jesli chcesz modyfikowac wojsko przejdz wczesniej do zbrojowni,\n dostepne zasoby sprawdzisz w skarbcu,\n a w komnacie krola przejdziesz do trybu walki.\n Czy juz wiesz co zrobic?\n");
            pomoc=true;
            wypiszInfo.setEditable(false);
        }        
        //komendy wspolne       
        if (plansza.Armia_Zasoby().getZloto()<60)
        {
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"- Krolu! Brakuje nam zlota w skarbcu, wyslij armie do pobliskiej wioski, aby zebrali podatki.\n"); 
            warunki.append("-"+misje[1]+"\n");
            aktualne.add(1);
            wypiszInfo.setEditable(false);
        }        
        Random generator = new Random();
        if (generator.nextInt(100)==1)//generator 1% szansy na zdarzenie, zdarzenie losowe
        {
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"- Twoi poddani sa niezadowoleni, zyja bardzo ubogo. Rozdaj wiesniakom 200 sztuk zlota, aby poprawic swoj autorytet.\n");
            warunki.append("-Rozdaj wiesniakom 200 sztuk zlota, aby poprawic swoj autorytet.\n\n");
            zdarzenie_losowe_wiesniacy=true;
            wypiszInfo.setEditable(false);
        }
        if (generator.nextInt(50)==2)//zdarzenie losowe
        {
            int max=Math.max(Math.max(Math.max(plansza1.getDiament(), plansza1.getDrewno()), plansza1.getZloto()), plansza1.getKamien());            
            int pomoc1;
            if (max==plansza1.getDrewno())
                max_zasob="drewna";
            else if (max==plansza1.getDiament())
                max_zasob="diamentow";
            else if (max==plansza1.getZloto())
                max_zasob="zlota";
            else        //sprawdzamy kamien
                max_zasob="kamienia";
            pomoc1=generator.nextInt(4);
            if (pomoc1==0)
                inny_zasob="drewna";
            else if (pomoc1==1)
                inny_zasob="diamentow";
            else if (pomoc1==2)
                inny_zasob="zlota";
            else
                inny_zasob="kamienia";
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Sasiednie krolestwo chcialoby zamienic 10 sztuk "+max_zasob+" na 10 sztuk "+inny_zasob+" Przyjmujesz?\n");
            wypiszInfo.setEditable(false);
            zamiana_zasobow=true;
        }
        if (przyjecie_zasobow==true && !wybor.contains("tak") && !wybor.contains("przyjmuje"))//obsluga odmow
        {
            wykonane = true;
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Wypowiedziales wojne, za 5 dni zostaniesz zaatakowany\n"); 
            wypiszInfo.setEditable(false);
            planowany_atak=5;
            warunki.append("-Czas pozostaly do ataku wrogich wojsk wynosi "+planowany_atak+" dni\n");
            plansza.Wrog_Zasoby().addPiechur(-plansza.Wrog_Zasoby().getPiechur());
            plansza.Wrog_Zasoby().addKusznik(-plansza.Wrog_Zasoby().getKusznik());
            plansza.Wrog_Zasoby().addHusarz(-plansza.Wrog_Zasoby().getHusarz());
            plansza.Wrog_Zasoby().addPiechur(generator.nextInt(10)+poziom_trudnosci_wrog);
            plansza.Wrog_Zasoby().addKusznik(generator.nextInt(10)+poziom_trudnosci_wrog);
            plansza.Wrog_Zasoby().addHusarz(generator.nextInt(10)+poziom_trudnosci_wrog);
            poziom_trudnosci_wrog++;
            przyjecie_zasobow=false;
        }
        if (generator.nextInt(50)==1)//zdarzenie losowe
        {
            int pomoc1=generator.nextInt(4);            
            if (pomoc1==0)
                zasob="drewna";
            else if (pomoc1==1)
                zasob="diamentow";
            else if (pomoc1==2)
                zasob="zlota";
            else
                zasob="kamienia";
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Sasiednie krolestwo w ramach sojuszu przesyla 15 "+zasob+". Przyjmujesz?\n");
            wypiszInfo.setEditable(false);
            przyjecie_zasobow=true;
        }
        if (generator.nextInt(30)==1)
        {
            plansza.Bandyci_Zasoby().addPiechur(-plansza.Bandyci_Zasoby().getPiechur());
            plansza.Bandyci_Zasoby().addKusznik(-plansza.Bandyci_Zasoby().getKusznik());   
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Krolu, w poblizu twojego terenu pojawili sie klusownicy\nrozpraw sie z nimi.\n");
            wypiszInfo.setEditable(false);
            warunki.append("Krolu, w poblizu twojego terenu pojawili sie klusownicy, rozpraw sie z nimi.\n");
            plansza.Bandyci_Zasoby().addPiechur(generator.nextInt(7)+poziom_trudnosci_bandyci);
            plansza.Bandyci_Zasoby().addKusznik(generator.nextInt(6)+poziom_trudnosci_bandyci);
            atak_bandytow=true;
            poziom_trudnosci_bandyci++;
        }
        if (wybor.contains("nie") && pomoc==true)
        {
            wykonane = true;
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.RED,"To moze skorzystaj z komendy \"pomoc\"\n");
            wypiszInfo.setEditable(false);
            pomoc=false;
        }
        if (wybor.contains("tak") || wybor.contains("przyjmuje"))//tu beda przyjmowane misje,zdarzenia
        {          
            wykonane = true;
            if (pomoc==true)
            {
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.RED,"Wobec tego powodzenia\n");
                wypiszInfo.setEditable(false);
                pomoc=false;
            }
            else if (malo_kamien==true)
            {
                plansza.Armia_Zasoby().dodaj("piechur", -1);
                plansza.Armia_Zasoby().addKamien(50);
                malo_kamien=false;
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Zdobyto 50 sztuk kamienia\n");
                wypiszInfo.setEditable(false);
            }
            else if (zamiana_zasobow==true)// misja zamiany zasobow
            {
                if ("drewna".equals(max_zasob))                
                    plansza.Armia_Zasoby().addDrewno(-10);      
                if ("zlota".equals(max_zasob))                
                    plansza.Armia_Zasoby().addZloto(-10);
                if ("kamienia".equals(max_zasob))                
                    plansza.Armia_Zasoby().addKamien(-10);
                if ("diamentow".equals(max_zasob))                
                    plansza.Armia_Zasoby().addDiament(-10);
                if ("drewna".equals(inny_zasob))                
                    plansza.Armia_Zasoby().addDrewno(10);
                if ("zlota".equals(inny_zasob))                
                    plansza.Armia_Zasoby().addZloto(10);
                if ("kamienia".equals(inny_zasob))                
                    plansza.Armia_Zasoby().addKamien(10);
                if ("diamentow".equals(inny_zasob))                
                    plansza.Armia_Zasoby().addDiament(10);
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Dokonano transferu zasobow\n");
                wypiszInfo.setEditable(false);
            }
            else if (przyjecie_zasobow==true)//misja przyjecia zasobow
            {
                if ("drewna".equals(zasob))
                {     
                    wypiszInfo.setEditable(true);
                    wypiszInfo.append(Color.BLACK,"Otrzymales 15 sztuk "+zasob+"\n");  
                    wypiszInfo.setEditable(false);
                    plansza.Armia_Zasoby().addDrewno(15);
                }
                if ("kamienia".equals(zasob))
                {
                    wypiszInfo.setEditable(true);
                    wypiszInfo.append(Color.BLACK,"Otrzymales 15 sztuk "+zasob+"\n");
                    wypiszInfo.setEditable(false);
                    plansza.Armia_Zasoby().addKamien(15);
                }
                if ("diamentow".equals(zasob))
                {
                    wypiszInfo.setEditable(true);
                    wypiszInfo.append(Color.BLACK,"Otrzymales 15 sztuk "+zasob+"\n");
                    wypiszInfo.setEditable(false);
                    plansza.Armia_Zasoby().addDiament(15);
                }
                if ("zlota".equals(zasob))
                {
                    wypiszInfo.setEditable(true);
                    wypiszInfo.append(Color.BLACK,"Otrzymales 15 sztuk "+zasob+"\n");
                    wypiszInfo.setEditable(false);
                    plansza.Armia_Zasoby().addZloto(15);
                }
                przyjecie_zasobow=false;
            }
            else if (malo_drewno==true)
            {
                plansza.Armia_Zasoby().dodaj("piechur", -1);
                plansza.Armia_Zasoby().addDrewno(50);
                malo_drewno=false;
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Zdobyto 50 sztuk drewna\n");
                wypiszInfo.setEditable(false);
            }
            else if (malo_diament==true)
            {
                plansza.Armia_Zasoby().dodaj("piechur", -1);
                plansza.Armia_Zasoby().addDiament(50);
                malo_diament=false;
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Zdobyto 50 sztuk diamentow\n");
                wypiszInfo.setEditable(false);
            }
            else if (tworzenie_piechurow==true)
            {
                plansza2.kup_jednostke("wytrenuj piechura", 1);
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Stworzyles "+1+" piechura/ow\n");
                wypiszInfo.setEditable(false);
                tworzenie_piechurow=false;
            }
            else if (tworzenie_kusznikow==true)
            {
                plansza2.kup_jednostke("wytrenuj kusznika", 1);
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Stworzyles "+1+" kusznika/ow\n");
                wypiszInfo.setEditable(false);
                tworzenie_kusznikow=false;
            }
            else if (tworzenie_husarzy==true)
            {
                plansza2.kup_jednostke("wytrenuj husarza", 1);
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Stworzyles "+1+" husarza/y\n");
                wypiszInfo.setEditable(false);
                tworzenie_husarzy=false;
            }
            else if (misja!=-1)
            {
                aktualne.add(misja);
                warunki.append("-"+misje[misja]);
                misja=-1;
            }             
            else         
            {
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.RED,"Nie wybrales zadnej misji\n");
                wypiszInfo.setEditable(false);
            }
        }         
        if (planowany_atak==0)
        {
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Zostales zaatakowany w zwiazku z nieprzyjeciem zasobow\n");
            wypiszInfo.append(Color.BLACK,plansza3.Walka()+"\n");
            wypiszInfo.setEditable(false);
        }        
        if (wybor.contains("wybieram") && (wybor.contains("misje") || wybor.contains("misje")) || wybor.contains("nr") || wybor.contains("numer"))
        {
            wykonane = true;
            //wypiszInfo.append("Na pewno?\n");              
            for (int i=0;i<wybor.length();i++)
            {
                if (i<wybor.length()-1)
                {
                    if(Character.isDigit(wybor.charAt(i)) && Character.isDigit(wybor.charAt(i+1)))
                    {
                        misja=(Integer.parseInt(Character.toString(wybor.charAt(i+1)))*10+Integer.parseInt(Character.toString(wybor.charAt(i))));
                        break;
                    }   
                }
                if (Character.isDigit(wybor.charAt(i)))
                {
                    misja=(Integer.parseInt(Character.toString(wybor.charAt(i))));
                    break;
                }            
            }         
            if (misja!=0)
            {
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Czy na pewno chcesz przyjac misje\n"+misje[misja]+"\n");
                wypiszInfo.setEditable(false);
            }
        }
        if (wybor.contains("wypisz") && (wybor.contains("dostepne") || wybor.contains("dostepne")) && wybor.contains("misje"))
        {
            wykonane = true;
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Mozesz wykonac nastepujace misje:\n");
            for (int i=0;i<misje.length;i++)             
                wypiszInfo.append(Color.BLACK,i+": "+misje[i]+"\n");  
            wypiszInfo.setEditable(false);
        }
        if (wybor.contains("koniec tury"))
        {
            wykonane = true;
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,"Rozpoczyna sie "+Integer.toString(Nastepny_Dzien())+" dzien\n");  
            wypiszInfo.setEditable(false);
        }
        if (zdarzenie_losowe_wiesniacy==true && (wybor.contains("przekaz") || wybor.contains("przekaz")) && (wybor.contains("zloto") || wybor.contains("zloto")) && (wybor.contains("wiesniakom") || wybor.contains("wiesniakom")))
        {
            wykonane = true;
            if (plansza.Armia_Zasoby().getZloto()>200)
            {
                plansza.Armia_Zasoby().addZloto(-200);
                plansza.Armia_Zasoby().addPiechur(1);
                zdarzenie_losowe_wiesniacy=false;
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.BLACK,"Wiesniacy sa zadowoleni, w zamian jeden z nich dolacza do twojej armii(piechur)");
                wypiszInfo.setEditable(false);
            }
            else
            {
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.red,"Nie masz wystarczajacej ilosci zlota\n");
                wypiszInfo.setEditable(false);
            }
        }        
        if (wybor.contains("wyslij") && wybor.contains("armie") && wybor.contains("podatki"))
        {
            wykonane = true;
            if (aktualne.contains(Integer.valueOf(1)))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=3)
                {
                    plansza.Armia_Zasoby().addPiechur(-3);                
                }
                else
                {
                    wypiszInfo.setEditable(true);
                    wypiszInfo.append(Color.RED,"Przykro mi, nie spelniles warunkow misji\n");
                    wypiszInfo.setEditable(false);
                    dzien_misja_1=0;
                    aktualne.remove(Integer.valueOf(1));
                }
                if (dzien_misja_1==3)
                {
                    plansza.Armia_Zasoby().addPiechur(3);
                    plansza.Armia_Zasoby().addZloto(500);
                    aktualne.remove(Integer.valueOf(1));
                    wypiszInfo.setEditable(true);
                    wypiszInfo.append(Color.RED,"Brawo, wykonales misje\n"+misje[1]+"\nWyslani zolnierze wrocili oraz zdobywasz 500 sztuk zlota\n");
                    wypiszInfo.setEditable(false);
                    dzien_misja_1=0;
                }
            }
            else
            {
                wypiszInfo.setEditable(true);
                wypiszInfo.append(Color.RED,"Nie masz dostepu do misji\n"+misje[1]+"\n");
                wypiszInfo.setEditable(false);
            }
        }  
        if (wybor.contains("zaatakuj") && (wybor.contains("bandytow") || wybor.contains("bandytow")) && atak_bandytow==true)
        {
            wykonane = true;
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.BLACK,plansza3.Walka_Z_Bandytami()+"\n");
            wypiszInfo.setEditable(false);
            atak_bandytow=false;
        }    
        else if (atak_bandytow==true)        
            warunki.append("Krolu, w poblizu twojego terenu pojawili sie klusownicy, rozpraw sie z nimi.\n");
        
            Statystyki.setVisible(false);
            Statystyki_Wartosc.setVisible(false);
            wypiszInfo.setEditable(true);
            wypiszInfo.append(Color.ORANGE,"Komunikat uzytkownika: "+wybor+"\n");  
            wypiszInfo.setEditable(false);
            interpreter=0;
            //xmlintepreter           
            if ((interpreter=xml.sprawdzPolecenie(wybor, 1)) == 0)
                if ((interpreter=xml.sprawdzPolecenie(wybor, 2)) == 0)
                    if ((interpreter=xml.sprawdzPolecenie(wybor, 3)) == 0)
                        interpreter=xml.sprawdzPolecenie(wybor, 0);
            System.out.println(interpreter+"  "+plansza.getClass().toString().charAt(plansza.getClass().toString().length() - 1));
            wypiszInfo.setEditable(true);
            switch(interpreter)
            {
                case 1://wykonujemy czynnosc przypisana id 1                    
                    pomoc();                    
                    break;
                case 2:
                    zmiana_planszy("plansza1");
                    break;
                case 3:
                    zmiana_planszy("plansza2");                    
                    break;
                case 4:
                    zmiana_planszy("plansza3");
                    break;              
                case 5:
                    if (wybor.contains("sprawdz") && wybor.contains("pogode"))
                    {      
                        
                        if (aktualne.contains(Integer.valueOf(2)) && pogoda_wykonana==false)
                        {                              
                            wypiszInfo.append(Color.RED,"Brawo, spelniles warunki misji\n"+misje[2]+"\nOtrzymujesz 300 sztuk zlota\n");
                            plansza.Armia_Zasoby().addZloto(300);
                            aktualne.remove(Integer.valueOf(2));
                            pogoda_wykonana=true;
                        }            
                        wypiszInfo.append(Color.BLACK,"Dzisiaj jest "+pogoda[generator.nextInt(pogoda.length)]+"\n");
                        
                    } 
                    break;
                case 6:                         
                    wypiszInfo.append(Color.BLACK,"Dzis jest dzien "+Wyswietl_Dzien()+"\n"); 
                    break;   
                case 7:
                    if (generator.nextInt(3) == 0)
                        wypiszInfo.append(Color.BLACK,"Poddani sa zadowoleni\n");
                    else if (generator.nextInt(3) == 1)
                        wypiszInfo.append(Color.BLACK,"Poddani sa niezadowoleni\n");
                    else
                        wypiszInfo.append(Color.BLACK,"Poddani sa obojetnie nastawieni\n");
                    break;
                case 12:
                    wypiszInfo.append(Color.BLACK,plansza1.Zasoby()+"\n");                     
                    break;
                case 13:
                    wypiszInfo.append(Color.BLACK,plansza1.getZloto()+"\n");
                    Statystyki.setVisible(true);
                    Statystyki_Wartosc.setVisible(true);                                                             
                    Statystyki_Wartosc.setText("Ilosc zlota:"+String.valueOf(plansza1.getZloto()));
                    break;
                case 14:
                    wypiszInfo.append(Color.BLACK,plansza1.getDiament()+"\n");
                    Statystyki.setVisible(true);
                    Statystyki_Wartosc.setVisible(true);                                                             
                    Statystyki_Wartosc.setText("Ilosc diament:"+String.valueOf(plansza1.getDiament()));
                    break;
                case 15:
                    Statystyki.setVisible(true);
                    Statystyki_Wartosc.setVisible(true);                                                             
                    Statystyki_Wartosc.setText("Ilosc kamienia:"+String.valueOf(plansza1.getKamien()));
                    wypiszInfo.append(Color.BLACK,plansza1.getKamien()+"\n");
                    break;
                case 16:
                    wypiszInfo.append(Color.BLACK,plansza1.getDrewno()+"\n"); 
                    Statystyki.setVisible(true);
                    Statystyki_Wartosc.setVisible(true);                                                             
                    Statystyki_Wartosc.setText("Ilosc drewna:"+String.valueOf(plansza1.getDrewno()));
                    break;  
                case 22:
                    wypiszInfo.append(Color.BLACK,plansza2.ulepszenia("husarz")+"\n"); 
                    break;
                case 23:
                    if (plansza.Armia_Zasoby().getZloto()>=200*Liczba_Jednostek() && plansza.Armia_Zasoby().getDiament()>=10*Liczba_Jednostek())
                        {
                            //if (Liczba_Jednostek()>0)
                            {
                                try
                                {         
                                    //liczba_jednostek=Liczba_Jednostek();
                                    wypiszInfo.append(Color.BLACK,"Zuzyjesz na to: "+200/*Liczba_Jednostek()*/+" zlota, "+10/*Liczba_Jednostek()*/+" diamentow\nJestes pewien swojej decyzji\n");
                                    tworzenie_husarzy=true;
                                }
                                catch(NumberFormatException e)
                                {         
                                    //wypiszInfo.setForeground(Color.RED);
                                    wypiszInfo.append(Color.RED," Podana liczba musi byc liczba calkowita dodatnia \n");
                                    //wypiszInfo.setForeground(Color.BLACK);
                                }
                            }
                            //else 
                            {
                                //wypiszInfo.setForeground(Color.RED);
                                //wypiszInfo.append(Color.RED,"Podana liczba musi byc liczba calkowita dodatnia \n");
                                //wypiszInfo.setForeground(Color.BLACK);
                            }
                        }                            
                        else
                        {
                            //wypiszInfo.setForeground(Color.RED);
                            wypiszInfo.append(Color.RED,"Nie masz wystarczajacej ilosci surowcow \n");
                            //wypiszInfo.setForeground(Color.BLACK);
                        }
                    break;
                case 24:
                    wypiszInfo.append(Color.BLACK,plansza2.ulepszenia("kusznik")+"\n");
                    break;
                case 25:
                     if (plansza.Armia_Zasoby().getZloto()>=140*Liczba_Jednostek() && plansza.Armia_Zasoby().getKamien()>=7*Liczba_Jednostek())
                        {
                            //if (Liczba_Jednostek()>0)
                            {
                                try
                                {      
                                    //liczba_jednostek=Liczba_Jednostek();
                                    wypiszInfo.append(Color.BLACK,"Zuzyjesz na to: "+140/*Liczba_Jednostek()*/+" zlota, "+7/*Liczba_Jednostek()*/+" kamienia\nJestes pewien swojej decyzji\n");
                                    tworzenie_kusznikow=true;
                                }
                                catch(NumberFormatException e)
                                {
                                    //wypiszInfo.setForeground(Color.RED);
                                    wypiszInfo.append(Color.RED,"Podana liczba musi byc liczba calkowita dodatnia\n");
                                    //wypiszInfo.setForeground(Color.BLACK);
                                }
                            }
                            //else 
                            {
                                //wypiszInfo.setForeground(Color.RED);
                                //wypiszInfo.append(Color.RED,"Podana liczba musi byc liczba calkowita dodatnia\n");
                                //wypiszInfo.setForeground(Color.BLACK);
                            }
                        }                            
                        else
                        {
                            //wypiszInfo.setForeground(Color.RED);
                            wypiszInfo.append(Color.RED,"Nie masz wystarczajacej ilosci surowcow\n");
                            //wypiszInfo.setForeground(Color.BLACK);
                        }
                    break;
                case 26:
                    //wypiszInfo.append(plansza2.ulepszenia("piechur")+"\n");
                    break;
                case 27:
                    if (plansza.Armia_Zasoby().getZloto()>=90*Liczba_Jednostek() && plansza.Armia_Zasoby().getDrewno()>=10*Liczba_Jednostek())
                        {
                            //if (Liczba_Jednostek()>0)
                            {
                                try
                                {                 
                                    //liczba_jednostek=Liczba_Jednostek();
                                    wypiszInfo.append(Color.BLACK,"Zuzyjesz na to: "+90/*Liczba_Jednostek()*/+" zlota, "+10/*Liczba_Jednostek()*/+" drewna\nJestes pewien swojej decyzji\n");
                                    tworzenie_piechurow=true;
                                }
                                catch(NumberFormatException e)
                                {
                                    //wypiszInfo.setForeground(Color.RED);
                                    wypiszInfo.append(Color.RED,"Podana liczba musi byc liczba calkowita dodatnia\n");
                                    //wypiszInfo.setForeground(Color.BLACK);
                                }
                            }
                            //else 
                            {
                                //wypiszInfo.setForeground(Color.RED);
                                //wypiszInfo.append(Color.RED,"Podana liczba musi byc liczba calkowita dodatnia\n");
                                //wypiszInfo.setForeground(Color.BLACK);
                            }
                        }                            
                        else
                        {
                            //wypiszInfo.setForeground(Color.RED);
                            wypiszInfo.append(Color.RED,"Nie masz wystarczajacej ilosci surowcow\n");
                            //wypiszInfo.setForeground(Color.BLACK);
                        }
                    break;
                case 29:
                    wypiszInfo.append(Color.BLACK,"Najsilniejsza jednostka jest "+plansza2.Najsilniejsza_Jednostka());
                    break;
                case 30:
                    wypiszInfo.append(Color.BLACK,"Najslabsza jednostka jest "+plansza2.Najslabsza_Jednostka());
                    break;
                case 31:
                    wypiszInfo.append(Color.BLACK,"Atak Armii: "+Integer.toString(plansza3.Wyswietlenie_Ataku_Armii())+"\nObrona Armii: "+Integer.toString(plansza3.Wyswietlenie_Obrony_Armii())+"\n");
                    break;
                case 32:
                    wypiszInfo.append(Color.BLACK,"Liczba piechurow "+plansza3.Armia_Zasoby().getPiechur());
                    break;
                case 33:
                    wypiszInfo.append(Color.BLACK,"Liczba husarzy "+plansza3.Armia_Zasoby().getHusarz());
                    break;
                case 34:
                    wypiszInfo.append(Color.BLACK,"Liczba kusznikow "+plansza3.Armia_Zasoby().getKusznik()); 
                case 35:                    
                    wypiszInfo.append(Color.BLACK,plansza3.Liczebnosc_Wojska()+"\n");
                    wypiszInfo.append(Color.BLACK,"Atak Armii: "+Integer.toString(plansza3.Wyswietlenie_Ataku_Armii())+"\nObrona Armii: "+Integer.toString(plansza3.Wyswietlenie_Obrony_Armii())+"\n");
                    wypiszInfo.append(Color.BLACK,plansza1.wyswietlstatystyki()+"\n");
                    break;
                case 36:
                    wypiszInfo.append(Color.BLACK,plansza3.Walka());
                case 40:
                    plansza.Wrog_Zasoby().addPiechur(-plansza.Wrog_Zasoby().getPiechur());
                    plansza.Wrog_Zasoby().addKusznik(-plansza.Wrog_Zasoby().getKusznik());
                    plansza.Wrog_Zasoby().addHusarz(-plansza.Wrog_Zasoby().getHusarz());
                    plansza.Wrog_Zasoby().addPiechur(generator.nextInt(30)+poziom_trudnosci_wrog);
                    plansza.Wrog_Zasoby().addKusznik(generator.nextInt(15)+poziom_trudnosci_wrog);
                    plansza.Wrog_Zasoby().addHusarz(generator.nextInt(5)+poziom_trudnosci_wrog);
                    wypiszInfo.append(Color.BLACK,plansza3.Walka());
                    break;
                default:
                    if (wykonane == false)
                    {                    
                        wypiszInfo.append(Color.RED,"Zle wprowadziles polecenie\nMoze ci pomoc?\n");
                        wypiszInfo.append(Color.RED,"Wpisz odpowiednie polecenie w konsoli. Jesli chcesz modyfikowac wojsko przejdz wczesniej do zbrojowni, dostepne zasoby sprawdzisz w skarbcu a w komnacie krola przejdziesz do trybu walki. Czy juz wiesz co zrobic? *\n");
                        pomoc=true;
                    }                    
            }     
            
            /*if (plansza.getClass().toString().contains("plansza3"))//komendy dla 3 pietra  
            {
                wypiszInfo.append("Witaj w pokoju krola\n");
                if (wybor.contains("pomoc"))//pomoc
                {
                    wypiszInfo.append("Na tym pietrze mozesz wydawac rozkazy ataku, sprawdzic sily swoje i wroga,\nwyslac armie po podatki po wyborze misji\n"
                            + "komendy jakie mozesz wykonac to:\n-wyswietl parametry armii wroga\n-wyslij armie po podatki(tylko jesli posiadasz odpowiednia misje)\n-wypisz liczebnosc armii wroga\n"
                            + "-przejdz na pietro 1\n-przejdz na pietro 2\n-przejdz na pietro 3\n");//\n ma byc na koncu
                }              
                
                
                else if(wybor.contains("przejdz na pietro 1"))
                {
                    zmiana_planszy("plansza1");
                    wypiszInfo.append("Przeszedles na 1 pietro\n");                        
                }
                else if(wybor.contains("przejdz na pietro 3"))
                {
                    wypiszInfo.append("Juz jestes na 3 pietrze\n");
                }
                else if(wybor.contains("przejdz na pietro 2"))
                {
                    zmiana_planszy("plansza2");
                    wypiszInfo.append("Przeszedles na 2 pietro\n");
                }   
                else if(wybor.contains("wyswietl parametry armii wroga"))
                    wypiszInfo.append("Atak Armii: "+Integer.toString(plansza3.Wyswietlenie_Ataku_Wroga())+"\nObrona Armii: "+Integer.toString(plansza3.Wyswietlenie_Obrony_Wroga())+"\n");                
                else if (wybor.contains("wyslij") && wybor.contains("armie") && wybor.contains("podatki"))
                {
                    if (aktualne.contains(Integer.valueOf(1)))
                    {
                        if (plansza.Armia_Zasoby().getPiechur()>=3)
                        {
                            plansza.Armia_Zasoby().addPiechur(-3);                
                        }
                        else
                        {
                            wypiszInfo.append("Przykro mi, nie spelniles warunkow misji\n");
                            dzien_misja_1=0;
                            aktualne.remove(Integer.valueOf(1));
                        }
                        if (dzien_misja_1==3)
                        {
                            plansza.Armia_Zasoby().addPiechur(3);
                            plansza.Armia_Zasoby().addZloto(500);
                            aktualne.remove(Integer.valueOf(1));
                            wypiszInfo.append("Brawo, wykonales misje\n"+misje[1]+"\nWyslani zolnierze wrocili oraz zdobywasz 500 sztuk zlota\n");
                            dzien_misja_1=0;
                        }
                    }
                    else
                        wypiszInfo.append("Nie masz dostepu do misji\n"+misje[1]+"\n");
                }
                else if(wybor.contains("wypisz") && wybor.contains("liczebnosc") && wybor.contains("armii") && wybor.contains("wroga"))
                    wypiszInfo.append(plansza3.Liczebnosc_Wojska_Wroga()+"\n");
                //else if(wybor.contains("walcz"))
                    //wypiszInfo.append(plansza3.Walka());                
                else
                {
                    wypiszInfo.append("zle wprowadziles polecenie\nMoze ci pomoc?");
                    wypiszInfo.append("Wpisz odpowiednie polecenie w konsoli. Jesli chcesz modyfikowac wojsko przejdz wczesniej do zbrojowni, dostepne zasoby sprawdzisz w skarbcu a w komnacie krola przejdziesz do trybu walki. Czy juz wiesz co zrobic?\n");
                    pomoc=true;
                }
                if (plansza.Armia_Zasoby().getKamien()<20)
                {
                    wypiszInfo.append("Uwaga, zauwazylem, ze masz malo kamienia, jesli wyslesz piechura to mozesz zdobyc 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
                    malo_kamien=true;                    
                }
                if (plansza.Armia_Zasoby().getDrewno()<20)
                {
                    wypiszInfo.append("Uwaga, zauwazylem, ze masz malo drewna, jesli wyslesz piechura to mozesz zdobyc 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
                    malo_drewno=true;   
                }
                if (plansza.Armia_Zasoby().getDiament()<2)
                {
                    wypiszInfo.append("Uwaga, zauwazylem, ze masz malo diamentow, jesli wyslesz piechura to mozesz zdobyc 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
                    malo_diament=true;                                    
                }               
            }
            else if(plansza.getClass().toString().contains("plansza2"))//komendy dla 2 pietra
            {
                //switch(wybor)
                //{moze tak bedzie wygladac jak nie bedzie xml-a
                    if (wybor.contains("pomoc"))//pomoc
                    {
                        wypiszInfo.append("\n");//\n ma byc na koncu
                    }
                    else if(wybor.contains("przejdz na pietro 1"))
                    {
                        zmiana_planszy("plansza1");
                        wypiszInfo.append("Przeszedles na 1 pietro\n");                        
                    }
                    else if(wybor.contains("przejdz na pietro 2"))
                    {
                        wypiszInfo.append("Juz jestes na 2 pietrze\n");
                    }
                    else if(wybor.contains("przejdz na pietro 3"))
                    {
                        zmiana_planszy("plansza3");
                        wypiszInfo.append("Przeszedles na 3 pietro\n");                        
                    }
                    else if(wybor.contains("pokaz stan wojska"))
                        wypiszInfo.append("Atak Armii: "+Integer.toString(plansza3.Wyswietlenie_Ataku_Armii())+"\nObrona Armii: "+Integer.toString(plansza3.Wyswietlenie_Obrony_Armii())+"\n");                   
                    else if(aktualne.contains(1) && (wybor.contains("poslij") && wybor.contains("zolnierzy")))
                    {
                        if (plansza.Armia_Zasoby().getPiechur()>=3)
                            plansza.Armia_Zasoby().addPiechur(-3);
                        else
                            wypiszInfo.append("Masz za malo zolnierzy\n");
                    }
                    else if(wybor.contains("liczba") && wybor.contains("zolnierzy"))
                        wypiszInfo.append(plansza3.Liczebnosc_Wojska()+"\n");                                      
                    //case "wytrenuj husarza"                    
                    else if(wybor.contains("wytrenuj piechura"))//ulepsz
                    {
                        wypiszInfo.append(plansza2.ulepszenia("piechur")+"\n");
                    }
                    else if (wybor.contains("wytrenuj kusznika"))//ulepsz
                        wypiszInfo.append(plansza2.ulepszenia("kusznik")+"\n");
                    else if (wybor.contains("wytrenuj husarza"))//ulepsz
                        wypiszInfo.append(plansza2.ulepszenia("husarz")+"\n");                        
                    else if(wybor.contains("dodaj") && (wybor.contains("piechura") || wybor.contains("piechurow") || wybor.contains("piechurow")))
                    { 
                        //Kup_Jednostke("wytrenuj piechura");  
                        System.out.println(plansza.Armia_Zasoby().getZloto()+" "+plansza.Armia_Zasoby().getDrewno()+" "+Liczba_Jednostek());
                        if (plansza.Armia_Zasoby().getZloto()>=90*Liczba_Jednostek() && plansza.Armia_Zasoby().getDrewno()>=10*Liczba_Jednostek())
                        {
                            if (Liczba_Jednostek()>0)
                            {
                                try
                                {                 
                                    liczba_jednostek=Liczba_Jednostek();
                                    wypiszInfo.append("Zuzyjesz na to: "+90*Liczba_Jednostek()+" zlota, "+10*Liczba_Jednostek()+" drewna\nJestes pewien swojej decyzji\n");
                                    tworzenie_piechurow=true;
                                }
                                catch(NumberFormatException e)
                                {
                                    wypiszInfo.append("Podana liczba musi byc liczba calkowita dodatnia\n");
                                }
                            }
                            else 
                                wypiszInfo.append("Podana liczba musi byc liczba calkowita dodatnia\n");
                        }                            
                        else
                            wypiszInfo.append("Nie masz wystarczajacej ilosci surowcow\n");
                    }
                    else if(wybor.contains("dodaj") && (wybor.contains("kusznika") || wybor.contains("kusznikow") || wybor.contains("kusznikow")))
                    {                        
                        if (plansza.Armia_Zasoby().getZloto()>=140*Liczba_Jednostek() && plansza.Armia_Zasoby().getKamien()>=7*Liczba_Jednostek())
                        {
                            if (Liczba_Jednostek()>0)
                            {
                                try
                                {      
                                    liczba_jednostek=Liczba_Jednostek();
                                    wypiszInfo.append("Zuzyjesz na to: "+140*Liczba_Jednostek()+" zlota, "+7*Liczba_Jednostek()+" kamienia\nJestes pewien swojej decyzji\n");
                                    tworzenie_kusznikow=true;
                                }
                                catch(NumberFormatException e)
                                {
                                    wypiszInfo.append("Podana liczba musi byc liczba calkowita dodatnia\n");
                                }
                            }
                            else 
                                wypiszInfo.append("Podana liczba musi byc liczba calkowita dodatnia\n");
                        }                            
                        else
                            wypiszInfo.append("Nie masz wystarczajacej ilosci surowcow\n");
                        //Kup_Jednostke("wytrenuj kusznika");
                    }
                    else if(wybor.contains("dodaj") && (wybor.contains("husarza") || wybor.contains("husarzy")))
                    {
                        if (plansza.Armia_Zasoby().getZloto()>=200*Liczba_Jednostek() && plansza.Armia_Zasoby().getDiament()>=10*Liczba_Jednostek())
                        {
                            if (Liczba_Jednostek()>0)
                            {
                                try
                                {         
                                    liczba_jednostek=Liczba_Jednostek();
                                    wypiszInfo.append("Zuzyjesz na to: "+200*Liczba_Jednostek()+" zlota, "+10*Liczba_Jednostek()+" diamentow\nJestes pewien swojej decyzji\n");
                                    tworzenie_husarzy=true;
                                }
                                catch(NumberFormatException e)
                                {                                    
                                    wypiszInfo.append("Podana liczba musi byc liczba calkowita dodatnia\n");
                                }
                            }
                            else 
                                wypiszInfo.append("Podana liczba musi byc liczba calkowita dodatnia\n");
                        }                            
                        else
                            wypiszInfo.append("Nie masz wystarczajacej ilosci surowcow\n");
                        //Kup_Jednostke("wytrenuj husarza");                       
                    }
                    else if(wybor.contains("wyjdz z gry"))
                    {
                        System.exit(0); 
                    }                    
                    else
                    {
                        wypiszInfo.append("zle wprowadziles polecenie\nMoze ci pomoc?\n");
                        wypiszInfo.append("Wpisz odpowiednie polecenie w konsoli. Jesli chcesz modyfikowac wojsko przejdz wczesniej do zbrojowni, dostepne zasoby sprawdzisz w skarbcu a w komnacie krola przejdziesz do trybu walki. Czy juz wiesz co zrobic?\n");
                        pomoc=true;
                    }
                //}                
                if (wybor.contains("wytrenuj piechura"))
                {
                    plansza2.kup_jednostke("wytrenuj piechura", wybor.);
                }
            }
            else if (plansza.getClass().toString().contains("plansza1"))//komendy dla 1 pietra
            {
                if (wybor.contains("pomoc"))//pomoc
                {
                    wypiszInfo.append("\n");//\n ma byc na koncu
                }
                else if(wybor.contains("przejdz na pietro 1"))
                    wypiszInfo.append("Juz jestes na 1 pietrze\n");
                else if(wybor.contains("przejdz na pietro 2"))
                {
                   zmiana_planszy("plansza2");
                   wypiszInfo.append("Przeszedles na 2 pietro\n");
                }                        
                else if(wybor.contains("przejdz na pietro 3"))
                {
                   zmiana_planszy("plansza3");
                   wypiszInfo.append("Przeszedles na 3 pietro\n");
                }
                else if(wybor.contains("wyswietl statystyki"))
                   wypiszInfo.append(plansza1.wyswietlstatystyki()+"\n");//nie wiem czy to zostawic w tym pietrze, czy dac do zbrojowni
                else if(wybor.contains("pokaz zasoby") || wybor.contains("pokaz zasoby"))
                   wypiszInfo.append(plansza1.Zasoby()+"\n"); 
                else if(wybor.contains("pokaz ile zlota") || wybor.contains("pokaz ile zlota"))
                   wypiszInfo.append(plansza1.getZloto()+"\n"); 
                else if(wybor.contains("pokaz ile kamienia") || wybor.contains("pokaz ile kamienia"))
                   wypiszInfo.append(plansza1.getKamien()+"\n"); 
                else if(wybor.contains("pokaz ile drewna") || wybor.contains("pokaz ile drewna"))
                   wypiszInfo.append(plansza1.getDrewno()+"\n"); 
                else if(wybor.contains("pokaz ile diamentow") || wybor.contains("pokaz ile diamentow"))
                   wypiszInfo.append(plansza1.getDiament()+"\n"); 
                else if (zdarzenie_losowe_wiesniacy==true && (wybor.contains("przekaz") || wybor.contains("przekaz")) && (wybor.contains("zloto") || wybor.contains("zloto")) && (wybor.contains("wiesniakom") || wybor.contains("wiesniakom")))
                {
                    if (plansza.Armia_Zasoby().getZloto()>200)
                    {
                        plansza.Armia_Zasoby().addZloto(-200);
                        plansza.Armia_Zasoby().addPiechur(1);
                        zdarzenie_losowe_wiesniacy=false;
                        wypiszInfo.append("Wiesniacy sa zadowoleni, w zamian jeden z nich dolacza do twojej armii(piechur)");
                    }
                    else
                        wypiszInfo.append("Nie masz wystarczajacej ilosci zlota\n");
                }
                else
                {
                    wypiszInfo.append("zle wprowadziles polecenie\nMoze ci pomoc?\n");
                    wypiszInfo.append("Wpisz odpowiednie polecenie w konsoli. Jesli chcesz modyfikowac wojsko przejdz wczesniej do zbrojowni, dostepne zasoby sprawdzisz w skarbcu a w komnacie krola przejdziesz do trybu walki. Czy juz wiesz co zrobic?\n");
                    pomoc=true;
                }
                
            }             */     
        warunki.append("Zasoby:\n-zloto: "+plansza1.Armia_Zasoby().getZloto()+"\n-drewno: "+plansza1.Armia_Zasoby().getDrewno()
        +"\n-kamien: "+plansza1.Armia_Zasoby().getKamien()+"\n-diament: "+plansza1.Armia_Zasoby().getDiament());
        komendy.setText("");
        wypiszInfo.setEditable(false);       
    }       
    private int Liczba_Jednostek()
    {
        for (int i=0;i<wybor.length();i++)
        {
            if(Character.isDigit(wybor.charAt(i)) && Character.isDigit(wybor.charAt(i+1)))
            {
                return Integer.parseInt(String.valueOf(wybor.charAt(i)*10+wybor.charAt(i+1)));
            }
            else if (Character.isDigit(wybor.charAt(i)))
            {
                return Integer.parseInt(String.valueOf(wybor.charAt(i)));
            }
        }
        return -1;
    }
    private int Wyswietl_Dzien()
    {
        return dzien;
    }
    private int Nastepny_Dzien()
    {
        dzien++;
        wypiszInfo.setEditable(true);
        if (dzien_misja_1>0)
            dzien_misja_1++;
        if (dzien==5 && aktualne.contains(0))
        {            
            if (plansza.Armia_Zasoby().getDefence()>=100)
            {
                
                wypiszInfo.append(Color.BLACK,"Brawo, spelniles warunki misji "+misje[0]+"\n");
                aktualne.remove(Integer.valueOf(0));                
            }
            else
            {
                wypiszInfo.append(Color.BLACK,"Przykro mi, nie spelniles warunkow misji\n"+misje[0]+"\n");
                try 
                {
                    Thread.sleep(6000);//czekanie 6 sekund                    
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(Szkielet.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }        
        if (planowany_atak>0)
        {
            warunki.append("-Czas pozostaly do ataku wrogich wojsk wynosi "+planowany_atak+" dni\n");
            planowany_atak--;
        }
        if (planowany_atak==0)
        {
            planowany_atak=-1;
            plansza3.Armia_Zasoby().addPiechur(-plansza3.Armia_Zasoby().getPiechur());
            plansza3.Armia_Zasoby().addKusznik(-plansza3.Armia_Zasoby().getKusznik());
            plansza3.Armia_Zasoby().addHusarz(-plansza3.Armia_Zasoby().getHusarz());
        }
        //dodawanie zasobow przy przejsciu do nastepnego dnia
        plansza1.Armia_Zasoby().addZloto(500);
        plansza1.Armia_Zasoby().addDrewno(60);
        plansza1.Armia_Zasoby().addKamien(30);
        plansza1.Armia_Zasoby().addDiament(6);
        wypiszInfo.setEditable(false);
        return dzien;
    }   
    public Szkielet() throws IOException
    {        
        super("Gra Zamek");     
        image = ImageIO.read(icon);
        komendy.setText("Tu wpisujemy polecenia");        
        //wypiszInfo.setOpaque(true);
        /*document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement();
        //setResizable(false);
        List<Element> list=rootNode.getChildren("wybor");*/        
        //wypiszInfo.setColumns(80);        
        //wypiszInfo.setRows(5);
        plansza = new menu();        
        addMouseListener(this);
        addMouseMotionListener(this); 
        addKeyListener(this);
        add(plansza);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setVisible(true); 
        setIconImage(image);  
        //wypiszInfo.setForeground(Color.BLACK); 
        Statystyki_Wartosc = new javax.swing.JLabel();
        Statystyki_Wartosc.setVisible(false);
        Statystyki_Wartosc.setFont(new java.awt.Font("Old English Text MT", 0, 24));           
        Statystyki = new Statystyki("zasoby.jpg");
        Statystyki.setOpaque(false);        
        Statystyki.setPreferredSize(new Dimension(962,692));
        Statystyki.setVisible(false);     
        //wypiszInfo.setEnabled(true);
        wypiszInfo.setEditable(false);        
        wypiszInfo.setVisible(true); 
        wypiszInfo.setEnabled(true);
        wypiszInfo.setOpaque(false);         
        wypiszInfo.setFont(new java.awt.Font("Old English Text MT", 0, 24));
        wypiszInfo.setText("Witaj krolu. Wrogie jednostki zblizaja sie do naszego zamku. Rozbuduj armie,\nzadbaj o wlasciwa ilosc zasobow i uchron zamek przed inwazja.\n");                                
        komendy.setEditable(true);
        komendy.setFont(new java.awt.Font("Old English Text MT", 0, 24));
        komendy.setEnabled(true);
        komendy.setOpaque(false);
        komendy.setVisible(true); 
        //wypiszInfo.setWrapStyleWord(true);
        //warunki.setWrapStyleWord(true);
        warunki.setFont(new java.awt.Font("Old English Text MT", 0, 24));
        warunki.setOpaque(false);
        warunki.append("-Masz "+plansza3.Armia_Zasoby().getDefence()+" punktow obrony,\npotrzebujesz 100(pozostalo "+(5-dzien)+" dni)\n");
        warunki.append("Zasoby:\n-zloto: "+plansza1.Armia_Zasoby().getZloto()+"\n-drewno: "+plansza1.Armia_Zasoby().getDrewno()
        +"\n-kamien: "+plansza1.Armia_Zasoby().getKamien()+"\n-diament: "+plansza1.Armia_Zasoby().getDiament());         
        warunki.setEditable(false);
        setResizable(false);
        aktualne.add(0);
        pack();        
        komendy.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                try
                {
                    komendyActionPerformed(evt);
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Szkielet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        });  
        komendy.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                komendyMouseClicked(evt);
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
        
        //komendy.setBounds(250,580,460,25);
        //960x692 - wymiar plansz     
        //wypiszInfo.setBounds(250,600,460,82);//wpierw podajemy wspolrzedne w poziomie i pionie lewego, gornego rogu, potem wielkosc w poziomie i pionie        
        if ("nowa gra".equals(wybor) || "plansza3".equals(wybor))
        {
            plansza = new plansza3();
            add(plansza);
            add(komendy_pole);
            pack();
        }        
        if ("plansza2".equals(wybor))
        {
            plansza = new plansza2();
            add(plansza);  
            add(komendy_pole);
            pack();      
        }
        if ("plansza1".equals(wybor))
        {
            plansza = new plansza1();
            add(plansza);      
            add(komendy_pole);
            pack();      
        }     
        Interfejs();        
    }    
    public void komendyMouseClicked(java.awt.event.MouseEvent evt) 
    {  
        komendy.setText("");
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
    public void mouseMoved(MouseEvent e) //tu beda sprawdzane warunki niektorych misji
    {        
                
        // wypiszInfo.append("Przykro mi, nie spelniles warunkow misji
        // wypiszInfo.append("Brawo, spelniles warunki misji        
    }  
      
    //metoda obslugujaca pomoc dla uzytkownika
    private void pomoc() throws InterruptedException 
    {
        wypiszInfo.setEditable(true);
        if (plansza.getClass().toString().contains("plansza3"))
        {
            temp = "Na tym pietrze mozesz wydawac rozkazy ataku, sprawdzic sily swoje i wroga,\nwyslac armie po podatki po wyborze misji\n"
                            + "komendy jakie mozesz wykonac to:\n-wyswietl parametry armii wroga\n-wyslij armie po podatki(tylko jesli posiadasz odpowiednia misje)\n-wypisz liczebnosc armii wroga\n"
                            + "-przejdz na pietro 1\n-przejdz na pietro 2\n-przejdz na pietro 3\n";
            //wypiszInfo.append(temp);
            for(int i=0;i<temp.length();i++)
            {                                        
                wypiszInfo.append(Color.RED,String.valueOf(temp.charAt(i)));                                       
            }
        }
        if (plansza.getClass().toString().contains("plansza2"))
            wypiszInfo.append(Color.RED,"Na tym pietrze znajduje sie zbrojowania"
                    + "\n, mozesz w niej szkolic nowych oraz ulepszac"
                    + "\naktualnych zolnierzy.\n"
                    + "Na tym pietrze mozesz wykonac nastepujace polecenia:"
                    + "\n-wytrenuj husarza\n-dodaj husarza\n-wytrenuj kusznika\n"
                    + "-dodaj kusznika\n-wytrenuj piechura\n-dodaj piechura"
                    + "\n-pokaz najslabsza jednostke\n-pokaz stan wojska"+" *\n");
        if (plansza.getClass().toString().contains("plansza1"))
            wypiszInfo.append(Color.RED,"Na tym pietrze znajduje sie skarbiec.\nKomendy jakie mozesz"
                    + "wykonac to:\n-pokaz zasoby\n-pokaz ile zlota\n-pokaz ile diamentow"
                    + "\n-pokaz ile kamieni\n-pokaz ile drewna\n-jakich zasobow mamy najwiecej\n"
                    + "-jakich zasobow mamy najmniej\n-pokaz ilosc walk wygranych\n-pokaz ilosc walki przegranych"+" *\n");
        wypiszInfo.setEditable(false);
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyChar()=='~')
        {
            try 
            {
                pomoc();
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(Szkielet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}

class WypiszInfo extends JTextPane //dzieki tej klasie mozna miec roznokolorwy tekst w polu tekstowym
{
    private BufferedImage image;   
    public WypiszInfo()
    {
        super();            
        setOpaque(false);        
        File imageFile = new File("src/Gra/Plansze/images/pole_tekstowe2.jpg");
        try 
        {
            image = ImageIO.read(imageFile);            
        } catch (IOException e) 
        {
            System.err.println("Blad odczytu obrazka");
        } 
        /*Dimension dimension = new Dimension(962,692);
        setPreferredSize(dimension);*/
    }
    public void append(Color c, String s) 
    {      
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
        StyleConstants.Foreground, c);
        int len = getDocument().getLength();
        setCaretPosition(len);
        setCharacterAttributes(aset, false);
        replaceSelection(s);        
    }     
    @Override
    public void paintComponent(Graphics g) 
    {
        int i=0;
        Graphics2D g2d = (Graphics2D) g;        
        while (i < 21474830)
        {
            g2d.drawImage(image, 0, i, this);
            i+=117;
        }
        super.paintComponent(g2d);
    }
}

class Warunki extends JTextArea //dzieki tej klasie mozna miec roznokolorwy tekst w polu tekstowym
{
    private BufferedImage image;   
    public Warunki()
    {
        super();            
        setOpaque(false);        
        File imageFile = new File("src/Gra/Plansze/images/pole_tekstowe3.jpg");
        try 
        {
            image = ImageIO.read(imageFile);            
        } catch (IOException e) 
        {
            System.err.println("Blad odczytu obrazka");
        } 
        /*Dimension dimension = new Dimension(962,692);
        setPreferredSize(dimension);*/
    }       
    @Override
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this); 
        g2d.drawImage(image, 0, 409, this); 
        super.paintComponent(g2d);
    }
}