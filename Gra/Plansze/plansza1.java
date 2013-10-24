/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra.Plansze;
import javax.swing.JOptionPane;
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
    public plansza1()
    {
        JOptionPane.showMessageDialog(null,"Witam w"); 
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
