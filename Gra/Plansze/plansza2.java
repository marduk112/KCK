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
        switch (jednostka)//wywoływane będą takźe inne metody
        {
            case "wytrenuj husarza":
                wojsko.addHusarz(liczba);
                return "Wytrenowano "+liczba+" Husarza/y";
            case "wytrenuj kusznika":
                wojsko.addKusznik(liczba);                
                return "Wytrenowano "+liczba+" Kusznika/ów";
            case "wytrenuj piechura":
                wojsko.addPiechur(liczba);
                return "Wytrenowano "+liczba+" Piechura/ów";
            default:                
                return "Błąd";
        }
    }
    public String ulepszenia(String jednostka)
    {
        wojsko.ulepsz(jednostka);
        return "Ulepszono "+jednostka;
    }
    public String Liczebnosc_Wojska()
    {
        return "Liczba poszczególnych jednostek\nLiczba husarzy: "+wojsko.getHusarz()+"\nLiczba łuczników: "+wojsko.getKusznik()+"\nLiczba piechurów: "+wojsko.getPiechur()+"\nWszystkich jednostek razem jest: "+wojsko.liczebność();
    }
}
