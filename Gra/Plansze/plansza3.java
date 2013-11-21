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
        int pomoc=(int)(wojsko.Liczba_Jednostek()*procent);        
        if (wojsko.getObronah()<wojsko.getObronak())
        {
            if (wojsko.getObronah()<wojsko.getObronap())
            {
                if (pomoc<=wojsko.getHusarz())
                {
                    wojsko.addHusarz(-pomoc);                       
                }      
                else
                {
                    pomoc=pomoc-wojsko.getHusarz();
                    wojsko.addHusarz(-wojsko.getHusarz());
                    if (wojsko.getObronak()<wojsko.getObronap())
                    {
                        if (pomoc<=wojsko.getKusznik())
                        {
                            wojsko.addKusznik(-pomoc);
                        }
                        else
                        {
                            pomoc=pomoc-wojsko.getKusznik();
                            wojsko.addKusznik(-wojsko.getKusznik());
                            if (pomoc<=wojsko.getPiechur())
                                wojsko.addPiechur(-pomoc);
                            else
                                wojsko.addPiechur(-wojsko.getPiechur());
                        }
                    }
                    else
                    {
                        if (pomoc<=wojsko.getPiechur())
                        {
                            wojsko.addPiechur(-pomoc);
                        }
                        else
                        {
                            pomoc=pomoc-wojsko.getPiechur();
                            wojsko.addPiechur(-wojsko.getPiechur());
                            if (pomoc<=wojsko.getKusznik())
                                wojsko.addKusznik(-pomoc);
                            else
                                wojsko.addKusznik(-wojsko.getKusznik());
                        }
                    }
                }
            }
            else
            {
                if (pomoc<=wojsko.getPiechur())                
                    wojsko.addPiechur(-pomoc);                
                else           
                {
                    pomoc=pomoc-wojsko.getPiechur();
                    wojsko.addPiechur(-wojsko.getPiechur());
                    if (wojsko.getObronak()<wojsko.getObronah())
                    {
                        if (pomoc<=wojsko.getKusznik())
                        {
                            wojsko.addKusznik(-pomoc);
                        }
                        else
                        {
                            pomoc=pomoc-wojsko.getKusznik();
                            wojsko.addKusznik(-wojsko.getKusznik());
                            if (pomoc<=wojsko.getHusarz())
                            {
                                wojsko.addHusarz(-pomoc);
                            }
                            else
                                wojsko.addHusarz(wojsko.getHusarz());
                        }
                    }
                    else
                    {
                        if (pomoc<=wojsko.getHusarz())
                            wojsko.addHusarz(-pomoc);
                        else
                        {
                            pomoc=pomoc-wojsko.getHusarz();
                            wojsko.addHusarz(wojsko.getHusarz());
                            if (pomoc<=wojsko.getKusznik())
                                wojsko.addKusznik(-pomoc);
                            else
                                wojsko.addKusznik(-wojsko.getKusznik());
                        }
                    }
                }                
            }
        }
        else
        {
            if (wojsko.getObronak()<wojsko.getObronap())
            {
                pomoc=(int)(wojsko.getKusznik()*procent);
                wojsko.addKusznik(-pomoc);                
            }
            else
            {
                pomoc=(int)(wojsko.getPiechur()*procent);
                wojsko.addPiechur(-pomoc);               
            }
        }
        return "Utraciłeś "+pomoc+" jednostek";
    }
    public String Walka()
    {
        if (wojsko.getAttack()>wrog.getAttack() && (wrog.getAttack()*1.3 < wojsko.getAttack() && wojsko.getAttack() <= wrog.getAttack()*1.5) && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.8)
        {
            return Utrata_Jednostek((float)0.5);
        }
        else if (wojsko.getAttack()>wrog.getAttack() && wrog.getAttack()*1.3 >= wojsko.getAttack() && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.8)
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
