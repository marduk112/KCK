/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gra.Plansze;

/**
 *
 * @author s384080
 */
public class plansza1 extends plansza_podst
{
    /*
     * rekrutacja wojska, liczebność, wydawanie poleceń, ulepszenia
     */    
    public plansza1()
    {
        super("stage1.jpg");        
        //JOptionPane.showMessageDialog(null,"Witam w pokoju króla");        
    }
    public int liczebnosc_wojska()
    {
        return wojsko.liczebność();
    }    
    public void Zasoby()
    {
        wojsko.krol.getDiament();
        wojsko.krol.getDrewno();
        wojsko.krol.getKamien();
        wojsko.krol.getZloto();
    }
}
