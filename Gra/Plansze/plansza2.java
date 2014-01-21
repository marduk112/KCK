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
                    wojsko.dodaj("husarz",liczba);
                    return "Dodano "+liczba+" Husarza/y";
                case "wytrenuj kusznika":
                    wojsko.dodaj("kusznik",liczba);                
                    return "Dodano "+liczba+" Kusznika/ów";
                case "wytrenuj piechura":
                    wojsko.dodaj("piechur",liczba);
                    return "Dodano "+liczba+" Piechura/ów";
                default:                
                    return "Błąd";
            }
        }
        catch(NumberFormatException e)
        {
            return "Podana liczba musi byc liczbą naturalna, np. 1,2,3 itd...";
        }
    }
    public String ulepszenia(String jednostka)
    {
        wojsko.ulepsz(jednostka);
        return "Ulepszono "+jednostka;
    }
    public String Najsilniejsza_Jednostka()
    {
        if (wojsko.getAtakh()+wojsko.getObronah() >= wojsko.getAtakk()+wojsko.getObronak())
        {
            if (wojsko.getAtakh()+wojsko.getObronah() >= wojsko.getAtakp()+wojsko.getObronap())
                return "husarz";
            else
                return "piechur";
        }
        else if (wojsko.getAtakk()+wojsko.getObronak() >= wojsko.getAtakp()+wojsko.getObronap())
        {
            if (wojsko.getAtakh()+wojsko.getObronah() >= wojsko.getAtakp()+wojsko.getObronap())
                return "husarz";
            else
                return "piechur";
        }
        else
            return "piechur";
    }
    public String Najslabsza_Jednostka()
    {
        if (wojsko.getAtakh()+wojsko.getObronah() >= wojsko.getAtakk()+wojsko.getObronak())
        {
            if (wojsko.getAtakk()+wojsko.getObronak() >= wojsko.getAtakp()+wojsko.getObronap())
                return "piechur";
            else
                return "kusznik";
        }
        else if (wojsko.getAtakk()+wojsko.getObronak() >= wojsko.getAtakp()+wojsko.getObronap())
        {
            if (wojsko.getAtakh()+wojsko.getObronah() >= wojsko.getAtakp()+wojsko.getObronap())
                return "piechur";
            else
                return "husarz";
        }
        else
            return "husarz";
    }
    // suma wszystkich swoich jednostek    
}
