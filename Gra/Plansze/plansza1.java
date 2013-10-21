/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra.Plansze;
import javax.swing.JPanel;
/**
 *
 * @author s384080
 */
public class plansza1 extends plansza_podst
{
    /*
     * rekrutacja wojska, liczebność, wydawanie poleceń, ulepszenia
     */
    
    public int liczebnosc_wojska()
    {
        return liczba_wojska;
    }
    public String ulepszenia(String ulepszenie)
    {
        switch(ulepszenie)// tu będą dokonywane ulepszenia
        {
            case "ulepsz atak":
                atak=atak*1.05; // lub metoda pozwalająca zmienić ten parametr
                return "atak ulepszony o 5%";
            case "ulepsz pancerz":
                pancerz=pancerz*1.05;
                return "pancerz ulepszony o 5%";
            case "ulepsz życie":
                zycie=zycie*1.10;
                return "punkty życia zwiększone o 10%";
            default:
                return "Nie ma takiej opcji";
        }
    }
}
