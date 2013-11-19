/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package Gra;
/**
*
* @author s383988
*/
public class Wojsko extends Krol
{
    private int husarz; //liczba husarzy w wojsku
    private int kusznik; //liczba kusznikow w wojsku
    private int piechur; //liczba piechurów
    private int atakh=10; //siła ataku husarza
    private int obronah=10; // sila obrony husarza
    private int atakk=8; //sila ataku kusznika
    private int obronak=5; // sila obrony kusznika
    private int atakp=6; //sila ataku piechura
    private int obronap=7; //sila obrony piechura    
    private int defence; //obrona calej armii
    private int attack; // atak calej armii    
    //public Krol krol=new Krol();
    
    
        public int liczebność(){return husarz+kusznik+piechur;}
        public int getHusarz() { return husarz; } //zwraca wartosc husarz
        //public void setHusarz(int i) { husarz = i; } // zmienia wartosc husarz
        public void addHusarz(int i) { husarz += i; } //dodawanie/odejmowanie liczby husarzy,
        //mozna wykorzystac przy rekrutacji i przy stratach po bitwie
        public int getKusznik() { return kusznik; } //analogicznie do husarza
        //public void setKusznik(int i) { kusznik = i; }
        public void addKusznik(int i) { kusznik += i; }
        public int getPiechur() { return piechur; }
        //public void setPiechur(int i) { piechur = i; }
        public void addPiechur(int i) { piechur += i; }
        public int getObronah() { return obronah; } //modernizacja obrona husarza
        //public void setObronah(int i) { obronah = i; }
        public void addObronah(int i) { obronah += i; }
        public int getObronak() { return obronak; } //modernizacja obrony kusznika
        //public void setObronak(int i) { obronak = i; }
        public void addObronak(int i) { obronak += i; }
        public int getObronap() { return obronap; }                 //modernizacja obrony piechura
        //public void setObronap(int i) { obronap = i; }
        public void addObronap(int i) { obronap += i; }
        public int getAtakh() { return atakh; } //modernizacja ataku husarza
        //public void setAtakh(int i) { atakh = i; }
        public void addAtakh(int i) { atakh += i; }
        public int getAtakk() { return atakk; } //modernizacja ataku kusznika
        //public void setAtakk(int i) { atakk = i; }
        public void addAtakk(int i) { atakk += i; }
        public int getAtakp() { return atakp; }                 //modernizacja ataku piechura
        //public void setAtakp(int i) { atakp = i; }
        public void addAtakp(int i) { atakp += i; }
        public int Liczba_Jednostek()
        {
            return getKusznik()+getHusarz()+getPiechur();
        }
        public int getDefence() { return defence; } //zwroc wartosc calkowitej obrony armii
        //public void setDefence(int i) { defence = i; }//przyjmij wartosc obrony
        public int getAttack() { return attack; }
        //public void setAttack(int i) { attack = i; }        
        private void zmien()
        {                //zmiana calkowitej obrony i ataku po modernizacji
                defence=(getHusarz()*getObronah()+getKusznik()*getObronak()+getPiechur()*getObronap());
                attack=(getHusarz()*getAtakh()+getKusznik()*getAtakk()+getPiechur()*getAtakp());
        }           
        public void ulepsz(String jednostka) 
        {                                                //ulepszanie armii
                if("husarz".equals(jednostka))//klikniecie na ulepszenie jednostek husarza + odpowiednie zasoby
                {                    
                        obronah+=2; //dodanie 2 pkt do obrony
                        atakh+=2;                //dodanie 2 pkt do ataku
                        addZloto(100); // zasoby zlota -100
                        addDiament(-5); //zasoby diamentu -5
                        
                }
                if("kusznik".equals(jednostka))//klikniecie na ulepszenie jednostek kusznika + odpowiednie zasoby
                {
                        obronak+=2;
                        atakk+=3;
                        addZloto(-80);
                        addKamien(-3);
                }
                if("piechur".equals(jednostka))//klikniecie na ulepszenie jednostek piechura + odpowiednie zasoby
                {
                        obronap+=3;
                        atakp+=2;
                        addZloto(-50);
                        addDrewno(-5);
                }
                zmien();
        }
        public void dodaj(String jednostka,int i)
        {        //dodawanie jednostek
                if("husarz".equals(jednostka))//klikniecie na dodanie jednostek husarza + odpowiednie zasoby
                {
                        husarz+=i; //dodanie i jednostek husarza
                        addZloto(-200*i); // zasoby zlota -200 za kazdego husarza
                        addDiament(-10*i); //zasoby diamentu -10 za kazdego husarza
                }
                if("kusznik".equals(jednostka))//klikniecie na dodanie jednostek kusznika + odpowiednie zasoby
                {
                        kusznik+=i;
                        addZloto(-140*i);
                        addKamien(-7*i);
                }
                if("piechur".equals(jednostka))//klikniecie na dodanie jednostek piechura + odpowiednie zasoby
                {
                        piechur+=i;
                        addZloto(-90*i);
                        addDrewno(-10*i);
                }
                zmien();
        }
}