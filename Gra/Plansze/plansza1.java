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
public class plansza1 extends plansza_podst
{
    /*
     * rekrutacja wojska, liczebność, wydawanie poleceń, ulepszenia
     */    
    public plansza1() throws IOException
    {
        super("stage1.jpg");        
        //JOptionPane.showMessageDialog(null,"Witam w pokoju króla");        
    }
    public int liczebnosc_wojska()
    {
        return wojsko.liczebność();
    }    
    public String Zasoby()
    {
        return "Liczba kamienia = "+wojsko.getKamien()+"\n"+"Liczba diamentu = "+wojsko.getDiament()+"\n"+"Liczba drewna = "+wojsko.getDrewno()+"\n"+"Liczba złota = "+wojsko.getZloto();
    }
}
