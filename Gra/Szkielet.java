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
import org.jdom2.JDOMException;
public class Szkielet extends JFrame implements MouseListener, MouseMotionListener
{        
    //private SAXBuilder builder = new SAXBuilder();
    //private File xmlFile = new File("src/gwiazdy.xml");
    //private Document document;
    private plansza_podst plansza;  
    private static String wybor;           	
    private final File icon = new File("src/Gra/Plansze/images/menu.jpg");
    private final BufferedImage image;
    //tablica z wszystkimi misjami
    private final String[] misje={"- W przeciągu 5 dni w świecie gry rozbuduj armię do poziomu obrony 100","- Królu! Brakuje nam złota w skarbcu, wyślij armię do pobliskiej wioski, aby zebrali podatki.","- Witaj królu, to ja Twój  błazen. Wyjrzyj przez okno jaka jest dziś pogoda, a dostaniesz 300 sztuk złota."};
    //tablica z aktualnie wykonywanymi misjami,przechowuje nr indeksu z tablicy misje
    private List<Integer> aktualne = new ArrayList<>();
    //rodzaje pogody występujące w grze
    private String[] pogoda={"słonecznie","pochmurno","pada deszcz"};
    private final JTextArea wypiszInfo = new JTextArea("Królu! Nadciąga potężna armia wroga. Musimy wzmocnić naszą obronę.\nPoniższa misja jest obowiązkowa\n" +
misje[0]+"\n");
    private final JTextField komendy = new JTextField(); 
    private final JScrollPane scrollPane = new JScrollPane(wypiszInfo); 
    private plansza1 plansza1 = new plansza1();
    private plansza2 plansza2 = new plansza2();
    private plansza3 plansza3 = new plansza3();   
    private boolean pogoda_wykonana=false;   
    private int misja=-1;
    private int dzien=1;    
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
    private int planowany_atak;
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
        wybor=komendy.getText().toLowerCase().trim();
        //komendy wspólne       
        if (plansza.Armia_Zasoby().getZloto()<60)
        {
            wypiszInfo.append("- Królu! Brakuje nam złota w skarbcu, wyślij armię do pobliskiej wioski, aby zebrali podatki.\n");            
            aktualne.add(1);
        }        
        //if (plansza.Armia_Zasoby().getKamien()<20)
        {
            //wypiszInfo.append("Uwaga, zauważyłem, że masz mało kamienia, jeśli wyślesz piechura to możesz zdobyć 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
            /*if (wybor.contains("diament"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addDiament(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }
            else if (wybor.contains("drewno"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addDrewno(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }
            else if (wybor.contains("złoto") || wybor.contains("zloto"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addZloto(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }*/           
        }
        //if (plansza.Armia_Zasoby().getDrewno()<20)
        {
            //wypiszInfo.append("Uwaga, zauważyłem, że masz mało drewna, jeśli wyślesz piechura to możesz zdobyć 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
            /*if (wybor.contains("diament"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addDiament(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }
            else if (wybor.contains("kamień") || wybor.contains("kamien"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addKamien(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }
            else if (wybor.contains("złoto") || wybor.contains("zloto"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addZloto(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }*/            
        }
        //if (plansza.Armia_Zasoby().getDiament()<2)
        {
            //wypiszInfo.append("Uwaga, zauważyłem, że masz mało diamentów, jeśli wyślesz piechura to możesz zdobyć 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
            /*if (wybor.contains("kamień") || wybor.contains("kamien"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addKamien(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }
            else if (wybor.contains("drewno"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addDrewno(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }
            else if (wybor.contains("złoto") || wybor.contains("zloto"))
            {
                if (plansza.Armia_Zasoby().getPiechur()>=1)
                {
                    plansza.Armia_Zasoby().addPiechur(-1);
                    plansza.Armia_Zasoby().addZloto(50);
                }
                else
                    wypiszInfo.append("Masz za mało piechurów\n");
            }*/
        }
        Random generator = new Random();        
        if (generator.nextInt(100)==1)//generator 1% szansy na zdarzenie
        {
            wypiszInfo.append("- Twoi poddani są niezadowoleni, żyją bardzo ubogo. Rozdaj wieśniakom 200 sztuk złota, aby poprawić swój autorytet.\n");
            zdarzenie_losowe_wiesniacy=true;
        }
        if (generator.nextInt(10)==2)
        {
            int max=Math.max(Math.max(Math.max(plansza1.getDiament(), plansza1.getDrewno()), plansza1.getZloto()), plansza1.getKamien());
            String max_zasob;
            String inny_zasob;
            int pomoc;
            if (max==plansza1.getDrewno())
                max_zasob="drewna";
            else if (max==plansza1.getDiament())
                max_zasob="diamentów";
            else if (max==plansza1.getZloto())
                max_zasob="złota";
            else        //sprawdzamy kamień
                max_zasob="kamienia";
            pomoc=generator.nextInt(4);
            if (pomoc==0)
                inny_zasob="drewna";
            else if (pomoc==1)
                inny_zasob="diamentów";
            else if (pomoc==2)
                inny_zasob="złota";
            else
                inny_zasob="kamienia";
            wypiszInfo.append("Sąsiednie królestwo chciałoby zamienić 10 sztuk "+max_zasob+" na 10 sztuk "+inny_zasob+" Przyjmujesz?\n");
            zamiana_zasobow=true;
        }
        if (generator.nextInt(50)==1)
        {
            int pomoc=generator.nextInt(4);
            String zasob;
            if (pomoc==0)
                zasob="drewna";
            else if (pomoc==1)
                zasob="diamentów";
            else if (pomoc==2)
                zasob="złota";
            else
                zasob="kamienia";
            wypiszInfo.append("Sąsiednie królestwo w ramach sojuszu przesyła 15 "+zasob+". Przyjmujesz?\n");
            przyjecie_zasobow=true;
        }
        if (wybor.contains("tak") || wybor.contains("przyjmuję"))//tu będą przyjmowane misje,zdarzenia
        {      
            System.out.println("mi");
            if (malo_kamien==true)
            {
                plansza.Armia_Zasoby().dodaj("piechur", -1);
                plansza.Armia_Zasoby().addKamien(50);
                malo_kamien=false;
                wypiszInfo.append("Zdobyto 50 sztuk kamienia\n");
            }
            else if (malo_drewno==true)
            {
                plansza.Armia_Zasoby().dodaj("piechur", -1);
                plansza.Armia_Zasoby().addDrewno(50);
                malo_drewno=false;
                wypiszInfo.append("Zdobyto 50 sztuk drewna\n");
            }
            else if (malo_diament==true)
            {
                plansza.Armia_Zasoby().dodaj("piechur", -1);
                plansza.Armia_Zasoby().addDiament(50);
                malo_diament=false;
                wypiszInfo.append("Zdobyto 50 sztuk diamentów\n");
            }
            else if (tworzenie_piechurow==true)
            {
                plansza2.kup_jednostke("wytrenuj piechura", liczba_jednostek);
                wypiszInfo.append("Stworzyłeś "+liczba_jednostek+" piechura/ów\n");
                tworzenie_piechurow=false;
            }
            else if (tworzenie_kusznikow==true)
            {
                plansza2.kup_jednostke("wytrenuj kusznika", liczba_jednostek);
                wypiszInfo.append("Stworzyłeś "+liczba_jednostek+" kusznika/ów\n");
                tworzenie_kusznikow=false;
            }
            else if (tworzenie_husarzy==true)
            {
                plansza2.kup_jednostke("wytrenuj husarza", liczba_jednostek);
                wypiszInfo.append("Stworzyłeś "+liczba_jednostek+" husarza/y\n");
                tworzenie_husarzy=false;
            }
            else if (misja!=-1)
            {
                aktualne.add(misja);
                misja=-1;
            }
            else if (przyjecie_zasobow==true)
            {
                wypiszInfo.append("Brawo. Przyjąłeś\n");                
                przyjecie_zasobow=false;
            }
            else if (zamiana_zasobow==true)
            {
                //plansza1
                wypiszInfo.append("Dokonano transferu zasobów\n");
                zamiana_zasobow=false;
            }
            else            
                wypiszInfo.append("Nie wybrałeś żadnej misji\n");            
        }         
        else if (planowany_atak==0)
        {
            wypiszInfo.append("Zostałeś zaatakowany w związku z nieprzyjęciem zasobów\n");
            wypiszInfo.append(plansza3.Walka());
        }
        if (przyjecie_zasobow==true)//obsługa odmów
        {
            wypiszInfo.append("Wypowiedziałeś wojnę, za 5 dni zostaniesz zaatakowany\n");
            planowany_atak=5;
            przyjecie_zasobow=false;
        }
        else if (wybor.contains("wybieram") && (wybor.contains("misje") || wybor.contains("misję")) || wybor.contains("nr") || wybor.contains("numer"))
        {
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
            wypiszInfo.append("Czy na pewno chcesz przyjąć misję\n"+misje[misja]+"\n");
        }
        else if (wybor.contains("wypisz") && wybor.contains("dostępne") && wybor.contains("misje"))
        {
            wypiszInfo.append("Możesz wykonać następujące misje:\n");
            for (int i=0;i<misje.length;i++)             
                wypiszInfo.append(i+": "+misje[i]+"\n");            
        }
        else if (wybor.contains("koniec tury"))
            wypiszInfo.append("Rozpoczyna się "+Integer.toString(Nastepny_Dzien())+" dzień\n");        
        else if (wybor.contains("który") && wybor.contains("jest") && wybor.contains("dzień") || wybor.contains("dziś"))
            wypiszInfo.append("Dziś jest dzień "+Wyswietl_Dzien()+"\n"); 
        else if (wybor.contains("sprawdź") && wybor.contains("pogodę"))
        {            
            if (aktualne.contains(Integer.valueOf(2)) && pogoda_wykonana==false)
            {            
                wypiszInfo.append("Brawo, spełniłeś warunki misji\n"+misje[2]+"\nOtrzymujesz 300 sztuk złota\n");
                plansza.Armia_Zasoby().addZloto(300);
                aktualne.remove(Integer.valueOf(2));
                pogoda_wykonana=true;
            }            
            wypiszInfo.append("Dzisiaj jest "+pogoda[generator.nextInt(pogoda.length)]+"\n");
        }        
        else if (!"".equals(komendy.getText()) && komendy.getText()!=null && !"\n".equals(komendy.getText()))
        {            
            System.out.println(komendy.getText()+" "+plansza.getClass().toString());//funkcja kontrolna
            //wybor=komendy.getText().toLowerCase().trim();       
            if (plansza.getClass().toString().contains("plansza3"))//komendy dla 3 piętra  
            {
                if(wybor.contains("przejdź na piętro 1"))
                {
                    zmiana_planszy("plansza1");
                    wypiszInfo.append("Przeszedłeś na 1 piętro\n");                        
                }
                else if(wybor.contains("przejdź na piętro 3"))
                {
                    wypiszInfo.append("Już jesteś na 3 piętrze\n");
                }
                else if(wybor.contains("przejdź na piętro 3"))
                {
                    zmiana_planszy("plansza2");
                    wypiszInfo.append("Przeszedłeś na 2 piętro\n");
                }   
                else if(wybor.contains("wyświetl parametry armii wroga"))
                    wypiszInfo.append("Atak Armii: "+Integer.toString(plansza3.Wyswietlenie_Ataku_Wroga())+"\nObrona Armii: "+Integer.toString(plansza3.Wyswietlenie_Obrony_Wroga())+"\n");                
                else if (wybor.contains("wyślij") && wybor.contains("armię") && wybor.contains("podatki"))
                {
                    if (aktualne.contains(Integer.valueOf(1)))
                    {
                        if (plansza.Armia_Zasoby().getPiechur()>=3)
                        {
                            plansza.Armia_Zasoby().addPiechur(-3);                
                        }
                        else
                        {
                            wypiszInfo.append("Przykro mi, nie spełniłeś warunków misji\n");
                            dzien_misja_1=0;
                            aktualne.remove(Integer.valueOf(1));
                        }
                        if (dzien_misja_1==3)
                        {
                            plansza.Armia_Zasoby().addPiechur(3);
                            plansza.Armia_Zasoby().addZloto(500);
                            aktualne.remove(Integer.valueOf(1));
                            wypiszInfo.append("Brawo, wykonałeś misję\n"+misje[1]+"\nWysłani żołnierze wrócili oraz zdobywasz 500 sztuk złota\n");
                            dzien_misja_1=0;
                        }
                    }
                    else
                        wypiszInfo.append("Nie masz dostępu do misji\n"+misje[1]+"\n");
                }
                else if(wybor.contains("wypisz") && wybor.contains("liczebność") && wybor.contains("armii") && wybor.contains("wroga"))
                    wypiszInfo.append(plansza3.Liczebnosc_Wojska_Wroga()+"\n");
                else if(wybor.contains("walcz"))
                    wypiszInfo.append(plansza3.Walka());                
                else
                    wypiszInfo.append("Źle wprowadziles polecenie\n");
                if (plansza.Armia_Zasoby().getKamien()<20)
                {
                    wypiszInfo.append("Uwaga, zauważyłem, że masz mało kamienia, jeśli wyślesz piechura to możesz zdobyć 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
                    malo_kamien=true;                    
                }
                if (plansza.Armia_Zasoby().getDrewno()<20)
                {
                    wypiszInfo.append("Uwaga, zauważyłem, że masz mało drewna, jeśli wyślesz piechura to możesz zdobyć 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
                    malo_drewno=true;   
                }
                if (plansza.Armia_Zasoby().getDiament()<2)
                {
                    wypiszInfo.append("Uwaga, zauważyłem, że masz mało diamentów, jeśli wyślesz piechura to możesz zdobyć 50 sztuk dowolnie innego zasobu, wybierz jakiego\n");            
                    malo_diament=true;
                    /*if (wybor.contains("kamień") || wybor.contains("kamien"))
                    {
                        if (plansza.Armia_Zasoby().getPiechur()>=1)
                        {
                            plansza.Armia_Zasoby().addPiechur(-1);
                            plansza.Armia_Zasoby().addKamien(50);
                        }
                        else
                            wypiszInfo.append("Masz za mało piechurów\n");
                    }
                    else if (wybor.contains("drewno"))
                    {
                        if (plansza.Armia_Zasoby().getPiechur()>=1)
                        {
                            plansza.Armia_Zasoby().addPiechur(-1);
                            plansza.Armia_Zasoby().addDrewno(50);
                        }
                        else
                            wypiszInfo.append("Masz za mało piechurów\n");
                    }
                    else if (wybor.contains("złoto") || wybor.contains("zloto"))
                    {
                        if (plansza.Armia_Zasoby().getPiechur()>=1)
                        {
                            plansza.Armia_Zasoby().addPiechur(-1);
                            plansza.Armia_Zasoby().addZloto(50);
                        }
                        else
                            wypiszInfo.append("Masz za mało piechurów\n");
                    }                    
                }*/                
            }
            }
            else if(plansza.getClass().toString().contains("plansza2"))//komendy dla 2 piętra
            {
                //switch(wybor)
                //{może tak będzie wyglądać jak nie będzie xml-a
                    if(wybor.contains("przejdź na piętro 1"))
                    {
                        zmiana_planszy("plansza1");
                        wypiszInfo.append("Przeszedłeś na 1 piętro\n");                        
                    }
                    else if(wybor.contains("przejdź na piętro 2"))
                    {
                        wypiszInfo.append("Już jesteś na 2 piętrze\n");
                    }
                    else if(wybor.contains("pokaż stan wojska"))
                        wypiszInfo.append("Atak Armii: "+Integer.toString(plansza3.Wyswietlenie_Ataku_Armii())+"\nObrona Armii: "+Integer.toString(plansza3.Wyswietlenie_Obrony_Armii())+"\n");                   
                    else if(aktualne.contains(1) && (wybor.contains("poślij") && wybor.contains("żołnierzy")))
                    {
                        if (plansza.Armia_Zasoby().getPiechur()>=3)
                            plansza.Armia_Zasoby().addPiechur(-3);
                        else
                            wypiszInfo.append("Masz za mało żołnierzy\n");
                    }
                    else if(wybor.contains("liczba") && wybor.contains("żołnierzy"))
                        wypiszInfo.append(plansza3.Liczebnosc_Wojska()+"\n");
                    else if(wybor.contains("przejdź na piętro 2"))
                    {
                        zmiana_planszy("plansza3");
                        wypiszInfo.append("Przeszedłeś na 3 piętro\n");
                    }                      
                    //case "wytrenuj husarza"                    
                    else if(wybor.contains("wytrenuj piechura"))//ulepsz
                    {
                        wypiszInfo.append(plansza2.ulepszenia("piechur")+"\n");
                    }
                    else if (wybor.contains("wytrenuj kusznika"))//ulepsz
                        wypiszInfo.append(plansza2.ulepszenia("kusznik")+"\n");
                    else if (wybor.contains("wytrenuj husarza"))//ulepsz
                        wypiszInfo.append(plansza2.ulepszenia("husarz")+"\n");                        
                    else if(wybor.contains("dodaj") && (wybor.contains("piechura") || wybor.contains("piechurów") || wybor.contains("piechurow")))
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
                                    wypiszInfo.append("Zużyjesz na to: "+90*Liczba_Jednostek()+" złota, "+10*Liczba_Jednostek()+" drewna\nJesteś pewien swojej decyzji\n");
                                    tworzenie_piechurow=true;
                                }
                                catch(NumberFormatException e)
                                {
                                    wypiszInfo.append("Podana liczba musi być liczbą całkowitą dodatnią\n");
                                }
                            }
                            else 
                                wypiszInfo.append("Podana liczba musi być liczbą całkowitą dodatnią\n");
                        }                            
                        else
                            wypiszInfo.append("Nie masz wystarczającej ilości surowców\n");
                    }
                    else if(wybor.contains("dodaj") && (wybor.contains("kusznika") || wybor.contains("kuszników") || wybor.contains("kusznikow")))
                    {                        
                        if (plansza.Armia_Zasoby().getZloto()>=140*Liczba_Jednostek() && plansza.Armia_Zasoby().getKamien()>=7*Liczba_Jednostek())
                        {
                            if (Liczba_Jednostek()>0)
                            {
                                try
                                {      
                                    liczba_jednostek=Liczba_Jednostek();
                                    wypiszInfo.append("Zużyjesz na to: "+140*Liczba_Jednostek()+" złota, "+7*Liczba_Jednostek()+" kamienia\nJesteś pewien swojej decyzji\n");
                                    tworzenie_kusznikow=true;
                                }
                                catch(NumberFormatException e)
                                {
                                    wypiszInfo.append("Podana liczba musi być liczbą całkowitą dodatnią\n");
                                }
                            }
                            else 
                                wypiszInfo.append("Podana liczba musi być liczbą całkowitą dodatnią\n");
                        }                            
                        else
                            wypiszInfo.append("Nie masz wystarczającej ilości surowców\n");
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
                                    wypiszInfo.append("Zużyjesz na to: "+200*Liczba_Jednostek()+" złota, "+10*Liczba_Jednostek()+" diamentów\nJesteś pewien swojej decyzji\n");
                                    tworzenie_husarzy=true;
                                }
                                catch(NumberFormatException e)
                                {                                    
                                    wypiszInfo.append("Podana liczba musi być liczbą całkowitą dodatnią\n");
                                }
                            }
                            else 
                                wypiszInfo.append("Podana liczba musi być liczbą całkowitą dodatnią\n");
                        }                            
                        else
                            wypiszInfo.append("Nie masz wystarczającej ilości surowców\n");
                        //Kup_Jednostke("wytrenuj husarza");                       
                    }
                    else if(wybor.contains("wyjdź z gry"))
                    {
                        System.exit(0); 
                    }                    
                    else
                        wypiszInfo.append("Źle wprowadziles polecenie\n");
                //}                
               /* if (wybor.contains("wytrenuj piechura"))
                {
                    plansza2.kup_jednostke("wytrenuj piechura", wybor.);
                }*/
            }
            else if (plansza.getClass().toString().contains("plansza1"))//komendy dla 1 piętra
            {
                if(wybor.contains("przejdź na piętro 1"))
                    wypiszInfo.append("Już jesteś na 1 piętrze\n");
                else if(wybor.contains("przejdź na piętro 2"))
                {
                   zmiana_planszy("plansza2");
                   wypiszInfo.append("Przeszedłeś na 2 piętro\n");
                }                        
                else if(wybor.contains("przejdź na piętro 3"))
                {
                   zmiana_planszy("plansza3");
                   wypiszInfo.append("Przeszedłeś na 3 piętro\n");
                }
                else if(wybor.contains("wyświetl statystyki"))
                   wypiszInfo.append(plansza1.wyswietlstatystyki()+"\n");//nie wiem czy to zostawic w tym pietrze, czy dać do zbrojowni
                else if(wybor.contains("pokaż zasoby"))
                   wypiszInfo.append(plansza1.Zasoby()+"\n"); 
                else if(wybor.contains("pokaż ile złota"))
                   wypiszInfo.append(plansza1.getZloto()+"\n"); 
                else if(wybor.contains("pokaż ile kamienia"))
                   wypiszInfo.append(plansza1.getKamien()+"\n"); 
                else if(wybor.contains("pokaż ile drewna"))
                   wypiszInfo.append(plansza1.getDrewno()+"\n"); 
                else if(wybor.contains("pokaż ile diamentów"))
                   wypiszInfo.append(plansza1.getDiament()+"\n"); 
                else if (zdarzenie_losowe_wiesniacy==true && wybor.contains("przekaż") && wybor.contains("zloto") && wybor.contains("wieśniakom"))
                {
                    if (plansza.Armia_Zasoby().getZloto()>200)
                    {
                        plansza.Armia_Zasoby().addZloto(-200);
                        plansza.Armia_Zasoby().addPiechur(1);
                        zdarzenie_losowe_wiesniacy=false;
                        wypiszInfo.append("Wieśniacy są zadowoleni, w zamian jeden z nich dołącza do twojej armii(piechur)");
                    }
                    else
                        wypiszInfo.append("Nie masz wystarczającej ilości złota\n");
                }
                else
                    wypiszInfo.append("Źle wprowadziles polecenie\n");
            }                  
        komendy.setText("");
        }
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
        if (dzien_misja_1>0)
            dzien_misja_1++;
        if (dzien==5 && aktualne.contains(0))
        {            
            if (plansza.Armia_Zasoby().getDefence()>=100)
            {
                wypiszInfo.append("Brawo, spełniłeś warunki misji "+misje[0]+"\n");
                aktualne.remove(Integer.valueOf(0));
            }
            else
            {
                wypiszInfo.append("Przykro mi, nie spełniłeś warunków misji\n"+misje[0]+"\n");
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
            planowany_atak--;
        //dodawanie zasobów przy przejściu do następnego dnia
        /*wojsko.addZloto();
        wojsko.addKamien();
        wojsko.addDrewno();
        wojsko.addDiament();*/
        return dzien;
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
        image = ImageIO.read(icon);
        komendy.setText("Tu wpisujemy polecenia");   
        //wypiszInfo.setOpaque(true);
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
        remove(komendy);
        remove(wypiszInfo);
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
    public void mouseMoved(MouseEvent e) //tu będą sprawdzane warunki niektórych misji
    {        
                
        // wypiszInfo.append("Przykro mi, nie spełniłeś warunków misji
        // wypiszInfo.append("Brawo, spełniłeś warunki misji        
    }        
}