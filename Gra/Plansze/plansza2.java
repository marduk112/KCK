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
    }    
    public String kup_jednostke(String jednostka,int liczba)//wykupywanie jednostek
    {
        try
        {
            switch (jednostka)//wywoływane będą takźe inne metody
            {
                case "wytrenuj husarza":
                    wojsko.addHusarz(liczba);
                    return "Dodano "+liczba+" Husarza/y";
                case "wytrenuj kusznika":
                    wojsko.addKusznik(liczba);                
                    return "Dodano "+liczba+" Kusznika/ów";
                case "wytrenuj piechura":
                    wojsko.addPiechur(liczba);
                    return "Dodano "+liczba+" Piechura/ów";
                default:                
                    return "Błąd";
            }
        }
        catch(NumberFormatException e)
        {
            return "Podana liczba musi być liczbą naturalną, np. 1,2,3 itd...";
        }
    }
    public String ulepszenia(String jednostka)
    {
        wojsko.ulepsz(jednostka);
        return "Ulepszono "+jednostka;
    }
    // suma wszystkich swoich jednostek    
}
