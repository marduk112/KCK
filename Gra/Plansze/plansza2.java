/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra.Plansze;
import KCK.Gra.*;
/**
 *
 * @author s384080
 */
public class plansza2 extends plansza_podst
{    
    /*
     * skarbiec, statystyki walk, polecenia z tym związane
     */
    public int ilosc_zlota()
    {
        return zloto;
    }
    public int liczba_zabitych()
    {
        return liczba_zabitych;
    }
    public int liczba_stworzonych_jednostek()
    {
        return ;// tu zależnie od tego jak w innych klasach będzie tworzenie jednostek załatwione
    }
    public int liczba_straconych_jednostek()
    {
        return ;
    }
}
