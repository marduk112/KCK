
package KCK;

import java.lang.reflect.Array;

//autor: Adrian Rembisz
	



abstract class Games{
	
	//ladowanie gry z mapami poszczegolnych pieter
	//inicjalizacja postaci krola
	//inicjalizacja rekrutcji wojsk
	//inicjalizacja walk
	//inicjalizacja okienka
	//obiekt utworzony jako 2 zaraz po menu
		//pokazuje wybor pietra do wyboru
		
		//wybierzpietro
		//pietro1
		//pietro2
		//pietro3
		//pietro4
	
	private String mapa;
	
	
	public void wydajPolecenie(){
		//zmiana polecen
	}
	
	public void inicjujKrola(){
		Krol krol;
	}
	
	public void rekrutujWojsko(){
		
	}

	public void walcz()
	{
		
	}
}



class Level1  extends Games{  //polecenie z pietra 1// wojsko rekrutacja
	
	
	public String getMapa()
	{
	  //zmiana mapy pietra
		return "";
	}
	
	public void dodajWojownika{
		
		private String wojownik;

		//dodawanie wojownikow ze wzgledu na typ
		
		
		public void dodajW(String wojownik){ //tworzy nowe obiekty wojownikow
			
			switch(){
			case  "kusznik": {
				
			}
			
			case  "lucznik": {
				
			}
			
			case  "rycerz": {
				
			}
			
			case  "wlocznik": {
				
			}
			}
		}
		
		
	}
	
	
	public String wydajPolecenie(){
		
	}
	
	
	
}


class Level2 extends Games{  //polecenie z pietra 2  // formacja wojska
	
	
	public String getMapa()
	{
	  //zmiana mapy pietra
		return "";
	}
	
	public String wydajPolecenie(){
		
	}
	
}

class Level3 extends Games{ //polecenie z pietra 3  //skarbiec i zasoby
	
	
	public String getMapa()
	{
	  //zmian mapy pietra
		return "";
	}
	public String wydajPolecenie(){
		
	}
}

class Level4 extends Games{
	
	
	public String getMapa()
	{
	  //zmiana mapy pietra
		return "";
	}
	public String wydajPolecenie(){ //polecenie z pietra 4 //krol i walka //krol wydaje polecenie do walki
		return;
	}
}

 

class Krol{
	protected int zycie;
	protected int liczba_wojska;
	protected int zloto;
	
	Krol(){
		this.zycie=100;
		this.liczba_wojska=0;
		this.zloto=1000;
	}
	
	public void rozkaz(){
		
	}
	
}



class Walcz{
	
}

class KoniecWalki{
	
}

class PokazWynikiWalki{
	
}


class Rozkaz{ //rozkazy wydawane przez krola w terminalu
	
	public void StartWalka(){
		
	}
	
	public void StopWalka(){
		
	}
	
	public void ZmienWojsko(){
		
	}
}

class Armia{
	
	private int rycerz;
	private int lucznik;
	private int kusznik;
	private int wlocznik;
	
	Armia(){
		this.rycerz=0;
		this.lucznik=0;
		this.kusznik=0;
		this.wlocznik=0;
	}
	
	void dodajRycerz(int a){
		this.rycerz+=a;
	}
	void dodajLucznik(int a){
		this.lucznik+=a;
	}
	void dodajKusznik(int a){
		this.kusznik+=a;
	}
	void dodajWlocznik(int a){
		this.wlocznik+=a;
	}
	
	public int liczbaWojska()
	{
		return kusznik+lucznik+rycerz+wlocznik;
	}
	
}
