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
public class plansza3 extends plansza_podst
{
    /*
     * widok na króla oraz walki, wydawanie poleceń do walk
     * private static int kamien; // liczba kamieni w zasobach
     * private static int diament; // liczba diamentow w zasobach
     * private static int drewno; // liczba drewna w zasobach
     * private static int zloto; //liczba zlota 
     */   
    public plansza3() throws IOException
    {
        super("stage3.jpg");        
    }
    private String Utrata_Jednostek(float procent)
    {
        int pomoc;
        if (wojsko.getObronah()<wojsko.getObronak())
        {
            if (wojsko.getObronah()<wojsko.getObronap())
            {
                pomoc=(int)(wojsko.getHusarz()*procent);
                wojsko.addHusarz(-pomoc);
                if (pomoc>0.05*(wojsko.Liczba_Jednostek()+pomoc))
                {
                    if (wojsko.getObronap()<wojsko.getObronak())
                    {
                        pomoc=(int)((wojsko.getPiechur()*0.05)-pomoc);
                        wojsko.addPiechur(pomoc);
                    }
                    //else
                        
                }
                return "Utraciłeś "+pomoc+" husarzy";
            }
            else
            {
                pomoc=(int)(wojsko.getPiechur()*procent);
                wojsko.addPiechur(-pomoc);
                return "Utraciłeś "+pomoc+" piechurów";
            }
        }
        else
        {
            if (wojsko.getObronak()<wojsko.getObronap())
            {
                pomoc=(int)(wojsko.getKusznik()*procent);
                wojsko.addKusznik(-pomoc);
                return "Utraciłeś "+pomoc+" kuszników";
            }
            else
            {
                pomoc=(int)(wojsko.getPiechur()*procent);
                wojsko.addPiechur(-pomoc);
                return "Utraciłeś "+pomoc+" piechurów";
            }
        }
    }
    public String Walka()
    {
        if (wojsko.getAttack()>wrog.getAttack() && wrog.getAttack()*1.3 >= wojsko.getAttack() && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.8)
        {
            return Utrata_Jednostek((float)0.7);
        }
        else if (wojsko.getAttack()>wrog.getAttack() && wrog.getAttack()*1.3 >= wojsko.getAttack() && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.5)
        {
            return Utrata_Jednostek((float)0.9);
        }
        else if (wojsko.getAttack()>wrog.getAttack() && wojsko.getDefence()>wrog.getDefence())
        {
            return Utrata_Jednostek((float)0.05);
        }
        return "";
    }
}
