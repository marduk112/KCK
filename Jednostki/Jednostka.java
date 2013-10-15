/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Jednostki;

/**
 *
 * @author Szymon Wójcik
 * Z tej klasy poszczególne rodzaje jednostek będą dziedziczyć parametry
 */
public abstract class Jednostka
{
    private int zycie;
    private int obrazenia;
    private int pancerz;    
    /*
     * Konstruktor klasy, przez niego będą tworzone poszczególne jednostki
     */
    public Jednostka(int zycie1,int obrazenia1,int pancerz1)
    {
        zycie=zycie1;
        obrazenia=obrazenia1;
        pancerz=pancerz1;        
    }
}
