/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra;

/**
 *
 * @author s383988
 */
public class Krol 
{
    /*
     * zczytanie miejsca krola, zasoby, wydawanie poleceń wojsku
     */
           
        private int kamien; // liczba kamieni w zasobach
        private int diament; // liczba diamentow w zasobach
        private int drewno; // liczba drewna w zasobach
        private int zloto; //liczba zlota        
        public int getKamien() { return kamien; } //funkcja przyjmujaca liczbe kamienia
        //public void setKamien(int i) { kamien = i; } //zapisywanie liczby kamienia
        public void addKamien(int i) { kamien += i; } //dodawanie/odejmowanie liczby kamieni
        public int getDiament() { return diament; } //analogicznie do kamienia
        //public void setDiament(int i) { diament = i; }
        public void addDiament(int i) { diament += i; }
        public int getDrewno() { return drewno; }
        //public void setDrewno(int i) { drewno = i; }
        public void addDrewno(int i) { drewno += i; }
        public int getZloto() { return zloto; }
        //public void setZloto(int i) { zloto = i; }
        public void addZloto(int i) { zloto += i; }        
}
