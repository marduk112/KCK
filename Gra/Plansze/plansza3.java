/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gra.Plansze;

import Gra.Wojsko;
import java.io.IOException;

/**
 *
 * @author s384080
 */
public class plansza3 extends plansza_podst
{
    /*
     * widok na króla oraz walki, wydawanie poleceń do walk
     * private static int kamien; // liczba kamieni w zasobach
     * private static int diament; // liczba diamentow w zasobach
     * private static int drewno; // liczba drewna w zasobach
     * private static int zloto; //liczba zlota 
     */
    private final Wojsko wojsko = new Wojsko();
    public plansza3() throws IOException
    {
        super("stage3.jpg");
        //JOptionPane.showMessageDialog(null,"Witam w"); 
    }
    public String wyswietlstatystyki()
    {
        return "Liczba jednostek";
    }
}
