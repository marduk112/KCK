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
        int zwroc=pomoc;
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
                if (pomoc<=wojsko.getObronak())
                    wojsko.addKusznik(-pomoc);
                else
                {
                    pomoc=pomoc-wojsko.getKusznik();
                    wojsko.addKusznik(-wojsko.getKusznik());
                    if (wojsko.getObronah()<wojsko.getObronak())
                    {
                        if (pomoc<=wojsko.getHusarz())
                            wojsko.addHusarz(-pomoc);
                        else
                        {
                            pomoc=pomoc-wojsko.getHusarz();
                            wojsko.addHusarz(-wojsko.getHusarz());
                            if (pomoc<=wojsko.getPiechur())
                                wojsko.addPiechur(-pomoc);
                            else
                                wojsko.addPiechur(-wojsko.getPiechur());
                        }
                    }
                    else
                    {
                        if (pomoc<=wojsko.getKusznik())
                            wojsko.addKusznik(-pomoc);
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
                    if (wojsko.getObronah()<wojsko.getObronap())
                    {
                        if (pomoc<=wojsko.getHusarz())
                            wojsko.addHusarz(-pomoc);
                        else
                        {
                            pomoc=pomoc-wojsko.getHusarz();
                            wojsko.addHusarz(-wojsko.getHusarz());
                            if (pomoc<=wojsko.getKusznik())
                                wojsko.addKusznik(-pomoc);
                            else
                                wojsko.addKusznik(-wojsko.getKusznik());
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
                            if (pomoc<=wojsko.getPiechur())
                                wojsko.addPiechur(-pomoc);
                            else
                                wojsko.addPiechur(-wojsko.getPiechur());
                        }
                    }
                }
            }
        }
        wojsko.addKamien(zwroc);
        wojsko.addZloto(zwroc);
        wojsko.addDiament(zwroc);
        return "Utraciłeś "+zwroc+" jednostek";
    }
    /*
    * Algorytm walki, dokładne warunki zwycięstwa opisane są w dokumentacji
    */
    public String Walka()
    {
        wrog.addPiechur(6);
        wrog.addKusznik(3);
        wrog.addHusarz(2);
        if (wojsko.getDefence()>wrog.getDefence() && wrog.getDefence()*1.7 < wojsko.getDefence() && wojsko.getAttack()<wrog.getAttack() && wojsko.getAttack()>=wrog.getAttack()*0.8)
            return Utrata_Jednostek((float)0.1);
        if (wojsko.getDefence()>wrog.getDefence() && (wrog.getDefence()*1.5 < wojsko.getDefence() && wojsko.getDefence() <= wrog.getDefence()*1.7) && wojsko.getAttack()<wrog.getAttack() && wojsko.getAttack()>=wrog.getAttack()*0.8)
            return Utrata_Jednostek((float)0.3);
        if (wojsko.getDefence()>wrog.getDefence() && (wrog.getDefence()*1.3 < wojsko.getDefence() && wojsko.getDefence() <= wrog.getDefence()*1.5) && wojsko.getAttack()<wrog.getAttack() && wojsko.getAttack()>=wrog.getAttack()*0.8)
            return Utrata_Jednostek((float)0.5);
        if (wojsko.getDefence()>wrog.getDefence() && wrog.getDefence()*1.3 >= wojsko.getDefence() && wojsko.getAttack()<wrog.getAttack() && wojsko.getAttack()>=wrog.getAttack()*0.8)        
            return Utrata_Jednostek((float)0.7);  
        if (wojsko.getDefence()>wrog.getDefence() && (wrog.getDefence()*1.5 < wojsko.getDefence() && wojsko.getDefence()<=wrog.getDefence()*1.7) && wojsko.getAttack()<wrog.getAttack() && wojsko.getAttack()>=wrog.getAttack()*0.5)
            return Utrata_Jednostek((float)0.5);
        if (wojsko.getDefence()>wrog.getDefence() && (wrog.getDefence()*1.3 < wojsko.getDefence() && wojsko.getDefence()<=wrog.getDefence()*1.5) && wojsko.getAttack()<wrog.getAttack() && wojsko.getAttack()>=wrog.getAttack()*0.5)
            return Utrata_Jednostek((float)0.7);
        if (wojsko.getDefence()>wrog.getDefence() && wrog.getDefence()*1.3 >= wojsko.getDefence() && wojsko.getAttack()<wrog.getAttack() && wojsko.getAttack()>=wrog.getAttack()*0.5)        
            return Utrata_Jednostek((float)0.9);        
        if (wojsko.getDefence()>wrog.getDefence() && wojsko.getAttack()>wrog.getAttack())        
            return Utrata_Jednostek((float)0.05);
        //zmiana z obrony na atak
        if (wojsko.getAttack()>wrog.getAttack() && wrog.getAttack()*1.7 < wojsko.getAttack() && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.8)
            return Utrata_Jednostek((float)0.1);
        if (wojsko.getAttack()>wrog.getAttack() && (wrog.getAttack()*1.5 < wojsko.getAttack() && wojsko.getAttack() <= wrog.getAttack()*1.7) && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.8)
            return Utrata_Jednostek((float)0.3);
        if (wojsko.getAttack()>wrog.getAttack() && (wrog.getAttack()*1.3 < wojsko.getAttack() && wojsko.getAttack() <= wrog.getAttack()*1.5) && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.8)
            return Utrata_Jednostek((float)0.5);
        if (wojsko.getAttack()>wrog.getAttack() && wrog.getAttack()*1.3 >= wojsko.getAttack() && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.8)        
            return Utrata_Jednostek((float)0.7);  
        if (wojsko.getAttack()>wrog.getAttack() && (wrog.getAttack()*1.5 < wojsko.getAttack() && wojsko.getAttack()<=wrog.getAttack()*1.7) && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.5)
            return Utrata_Jednostek((float)0.5);
        if (wojsko.getAttack()>wrog.getAttack() && (wrog.getAttack()*1.3 < wojsko.getAttack() && wojsko.getAttack()<=wrog.getAttack()*1.5) && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.5)
            return Utrata_Jednostek((float)0.7);
        if (wojsko.getAttack()>wrog.getAttack() && wrog.getAttack()*1.3 >= wojsko.getAttack() && wojsko.getDefence()<wrog.getDefence() && wojsko.getDefence()>=wrog.getDefence()*0.5)        
            return Utrata_Jednostek((float)0.9);        
        if (wojsko.getAttack()>wrog.getAttack() && wojsko.getDefence()>wrog.getDefence())        
            return Utrata_Jednostek((float)0.05);        
        else
        {
            wojsko.addPiechur(-wojsko.getPiechur());
            wojsko.addKusznik(-wojsko.getKusznik());
            wojsko.addHusarz(-wojsko.getHusarz());
            return "Utraciłeś wszystkie jednostki";
        }        
    }    
}
