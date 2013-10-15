/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Zasoby;

/**
 *
 * @author Szymon
 * W tej klasie będą zaimplementowane zasoby używane w grze
 */
public class Zasoby 
{
    private int zloto;
    /*
     * Metoda ta zwieksza ilosc zlota o wartosc zloto1
     */
    public Zasoby(int zloto1)
    {
        zloto=zloto1;
    }
    public void dodajzlota(int zloto1)
    {
        zloto+=zloto1;
    }
    public int ilezlota()
    {
        return zloto;
    }
}
