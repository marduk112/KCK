/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gra.Plansze;
import java.io.IOException;
/**
 *
 * @author s384080
 */
public class plansza2 extends plansza_podst
{    
    /*
     * skarbiec, statystyki walk, polecenia z tym związane
     */
    
    public plansza2() throws IOException
    {
        super("stage2.jpg");        
        //JOptionPane.showMessageDialog(null,"Witam w zbrojowni");         
    }    
    public String kup_jednostke(String jednostka,int liczba)//wykupywanie jednostek
    {
        switch (jednostka)//wywoływane będą takźe inne metody
        {
            case "wytrenuj husarza":
                wojsko.addHusarz(liczba);
                return "Wytrenowano Husarza";
            case "wytrenuj kusznika":
                wojsko.addKusznik(liczba);                
                return "Wytrenowano Kusznika";
            case "wytrenuj piechura":
                wojsko.addPiechur(liczba);
                return "Wytrenowano Piechura";
            default:                
                return "Błąd";
        }
    }
    public String ulepszenia(String ulepszenie,String jednostka)
    {
        wojsko.ulepsz(jednostka);
        return "Ulepszono "+ulepszenie+" "+jednostka;
    }
}
