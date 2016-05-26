package algorytmy;

import model.Blok;
import model.Dysk;

public class C_SCAN {

	public int iloscPrzesuniec = 0;
	Dysk dysk;
	
	public C_SCAN(Dysk dysk){
		this.dysk = dysk;
	}
	
	public void wykonaj(){
		int obecnyBlok = 50;
		int poprzedniBlok = 50;
		int czas = 0;
		int wykonaneOdwolania = 0;
		while(wykonaneOdwolania < dysk.liczbaOdwolan){
			obecnyBlok = dajNajblizszyLewy(obecnyBlok, czas);
			if(obecnyBlok < 0){
				iloscPrzesuniec += Math.abs(0 - poprzedniBlok);
				obecnyBlok = dysk.bloki.length;
				poprzedniBlok = 0;
			} else {
				Blok blok = dysk.bloki[obecnyBlok];
				blok.odwolania.remove(0);
				wykonaneOdwolania++;
			}
			iloscPrzesuniec += Math.abs((obecnyBlok == dysk.bloki.length ? 100 : obecnyBlok)  - poprzedniBlok);
			poprzedniBlok = obecnyBlok;
			czas++;
		}
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
