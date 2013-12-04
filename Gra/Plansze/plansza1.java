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
    }      
    public String Zasoby()
    {
        return "Liczba kamienia = "+wojsko.getKamien()+"\n"+"Liczba diamentu = "+wojsko.getDiament()+"\n"+"Liczba drewna = "+wojsko.getDrewno()+"\n"+"Liczba złota = "+wojsko.getZloto();
    }
    public String wyswietlstatystyki()
    {
        return "Liczba utworzonych jednostek: "+wojsko.Liczba_Jednostek()+"\nLiczba zabitych jednostek: "+"\n";
    }
    public int getZloto()
    {
        return wojsko.getZloto();
    }
    public int getDiament()
    {
        return wojsko.getDiament();
    }
    public int getDrewno()
    {
        return wojsko.getDrewno();
    }
    public int getKamien()
    {
        return wojsko.getKamien();
    }
}
