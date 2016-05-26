package algorytmy;

import model.Blok;
import model.Dysk;

public class SCAN {

	public int iloscPrzesuniec = 0;
	Dysk dysk;
	
	public SCAN(Dysk dysk){
		this.dysk = dysk;
	}
	
	public void wykonaj(){
		int obecnyBlok = 50;
		int poprzedniBlok = 50;
		int czas = 0;
		int wykonaneOdwolania = 0;
		int kierunek = 1;
		while(wykonaneOdwolania < dysk.liczbaOdwolan){
			if(kierunek > 0){
				obecnyBlok = dajNajblizszyPrawy(obecnyBlok, czas);
				if(obecnyBlok == dysk.bloki.length){
					kierunek = -1;
					obecnyBlok = dysk.bloki.length - 1;
				} else {
					Blok blok = dysk.bloki[obecnyBlok];
					blok.odwolania.remove(0);
					wykonaneOdwolania++;
				}
			} else {
				obecnyBlok = dajNajblizszyLewy(obecnyBlok, czas);
				if(obecnyBlok < 0){
					kierunek = 1;
					obecnyBlok = 0;
				} else {
					Blok blok = dysk.bloki[obecnyBlok];
					blok.odwolania.remove(0);
					wykonaneOdwolania++;
				}
			}
			iloscPrzesuniec += Math.abs(obecnyBlok - poprzedniBlok);
			poprzedniBlok = obecnyBlok;
			czas++;
		}
	}
	
	public int dajNajblizszyPrawy(int obecnyBlok, int czas){
		int i = 1;
		while(obecnyBlok + i < dysk.bloki.length){
			if(obecnyBlok + i < dysk.bloki.length && dysk.czyJestOdwolanieDlaBlokuWCzasie(obecnyBlok + i, czas)){
				return obecnyBlok + i;
			}
			i++;
		}
		return dysk.bloki.length;
		
	}
	
	public int dajNajblizszyLewy(int obecnyBlok, int czas){
		int i = 1;
		while(obecnyBlok - i >= 0){
			if(obecnyBlok - i >= 0 && dysk.czyJestOdwolanieDlaBlokuWCzasie(obecnyBlok - i, czas)){
				return obecnyBlok - i;
			}
			i++;
		}
		return -1;
	}
}
