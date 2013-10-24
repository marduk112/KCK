/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KCK.Gra;

/**
 *
 * @author s383988
 */
public class Wojsko 
{
    public int husarz; //liczba husarzy w wojsku
    public int kusznik; //liczba kusznikow w wojsku
    public int piechur; //liczba piechurów
    public int atakh; //siła ataku husarza
    public int obronah; // sila obrony husarza
    public int atakk; //sila ataku kusznika
    public int obronak; // sila obrony kusznika
    public int atakp; //sila ataku piechura
    public int obronap; //sila obrony piechura
    
    public int defence; //obrona calej armii
    public int attack; // atak calej armii
    
    
    
    
    public int getHusarz() { return husarz; }    //zwraca wartosc husarz
	public void setHusarz(int i) { husarz = i; } // zmienia wartosc husarz 
	public void addHusarz(int i) { husarz += i; } //dodawanie/odejmowanie liczby husarzy,
	//mozna wykorzystac przy rekrutacji i przy stratach po bitwie
	public int getKusznik() { return kusznik; }    //analogicznie do husarza
	public void setKusznik(int i) { kusznik = i; } 
	public void addKusznik(int i) { kusznik += i; } 
	public int getPiechur() { return piechur; }    
	public void setPiechur(int i) { piechur = i; } 
	public void addPiechur(int i) { piechur += i; } 
	public int getObronah() { return obronah; }       //modernizacja obrona husarza
	public void setObronah(int i) { obronah = i; } 
	public void addObronah(int i) { obronah += i; }
	public int getObronak() { return obronak; }       //modernizacja obrony kusznika
	public void setObronak(int i) { obronak = i; } 
	public void addObronak(int i) { obronak += i; } 
	public int getObronap() { return obronap; }    		//modernizacja obrony piechura
	public void setObronap(int i) { obronap = i; } 
	public void addObronap(int i) { obronap += i; } 
	public int getAtakh() { return atakh; }       //modernizacja ataku husarza
	public void setAtakh(int i) { atakh = i; } 
	public void addAtakh(int i) { atakh += i; }
	public int getAtakk() { return atakk; }       //modernizacja ataku kusznika
	public void setAtakk(int i) { atakk = i; } 
	public void addAtakk(int i) { atakk += i; } 
	public int getAtakp() { return atakp; }    		//modernizacja ataku piechura
	public void setAtakp(int i) { atakp = i; } 
	public void addAtakp(int i) { atakp += i; } 
	
	public int getDefence() { return defence; }    //zwroc wartosc calkowitej obrony armii
	public void setDefence(int i) { defence = i; }//przyjmij wartosc obrony
	public int getAttack() { return attack; }    
	public void setAttack(int i) { attack = i; } 
	
	public void zmien(){		//zmiana calkowitej obrony i ataku po modernizacji
		setDefence(getHusarz()*getObronah()+getKusznik()*getObronak()+getPiechur()*getObronap());
		setAttack(getHusarz()*getAtakh()+getKusznik()*getAtakk()+getPiechur()*getAtakp());
	}
	
	
	public void ulepsz() {						//ulepszanie armii

		if(....)//klikniecie na ulepszenie jednostek husarza + odpowiednie zasoby
		{
			addObronah(2);  //dodanie 1 pkt  do obrony
			addAtakh(2);		//dodanie 2 pkt do ataku
			addZloto(-100);  // zasoby zlota -100
			addDiament(-5); //zasoby diamentu -5
			
		}
		if(....)//klikniecie na ulepszenie jednostek kusznika + odpowiednie zasoby
		{
			addObronak(2);
			addAtakk(3);
			addZloto(-80);
			addKamien(-3);
		}
		if(....)//klikniecie na ulepszenie jednostek piechura + odpowiednie zasoby
		{
			addObronap(3);
			addAtakp(2);
			addZloto(-50);
			addDrewno(-5);
		}
		zmien();
	}

	public void dodaj(int i){	//dodawanie jednostek
		if(....)//klikniecie na dodanie jednostek husarza + odpowiednie zasoby
		{
			addHusarz(i);  //dodanie i  jednostek  husarza
			addZloto(-200*i);  // zasoby zlota -200 za kazdego husarza
			addDiament(-10*i); //zasoby diamentu -10 za kazdego husarza
		}
		if(....)//klikniecie na dodanie jednostek kusznika + odpowiednie zasoby
		{
			addKusznik(i);
			addZloto(-140*i);
			addKamien(-7*i);
		}
		if(....)//klikniecie na dodanie jednostek piechura + odpowiednie zasoby
		{
			addPiechur(i);
			addZloto(-90*i);
			addDrewno(-10*i);
		}
		zmien();
	}


}
