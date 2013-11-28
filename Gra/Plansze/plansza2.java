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
    public String Liczebnosc_Wojska_Wroga()
    {
        return "Liczba poszczególnych jednostek\nLiczba husarzy: "+wrog.getHusarz()+"\nLiczba kuszników: "+wrog.getKusznik()+"\nLiczba piechurów: "+wrog.getPiechur()+"\nWszystkich jednostek razem jest: "+wojsko.liczebność();
    }
    public String Liczebnosc_Wojska()
    {
        return "Liczba poszczególnych jednostek\nLiczba husarzy: "+wojsko.getHusarz()+"\nLiczba kuszników: "+wojsko.getKusznik()+"\nLiczba piechurów: "+wojsko.getPiechur()+"\nWszystkich jednostek razem jest: "+wojsko.liczebność();
    }
    //nie wiem do jakiego dokładnie piętra dać poniższe metody, obrona to suma punktów obrony wszystkich jednostek, atak analogicznie
    public int Wyswietlenie_Obrony_Armii()
    {
        return wojsko.getDefence();
    }
    public int Wyswietlenie_Ataku_Armii()
    {
        return wojsko.getAttack();
    }
    public int Wyswietlenie_Ataku_Wroga()
    {
        return wojsko.getAttack();
    }
    public int Wyswietlenie_Obrony_Wroga()
    {
        return wojsko.getDefence();
    }
}
