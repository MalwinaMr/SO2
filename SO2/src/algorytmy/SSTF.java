package algorytmy;

import model.Blok;
import model.Dysk;

public class SSTF {

	public int iloscPrzesuniec = 0;
	Dysk dysk;
	
	public SSTF(Dysk dysk){
		this.dysk = dysk;
	}
	
	public void wykonaj(){
		int obecnyBlok = 50;
		int poprzedniBlok = 50;
		int czas = 0;
		int wykonaneOdwolania = 0;
		while(wykonaneOdwolania < dysk.liczbaOdwolan){
			obecnyBlok = dajNajblizszy(obecnyBlok, czas);
			if(obecnyBlok == -1){
				obecnyBlok = poprzedniBlok;
			} else {
				Blok blok = dysk.bloki[obecnyBlok];
				blok.odwolania.remove(0);
				iloscPrzesuniec += Math.abs(obecnyBlok - poprzedniBlok);
				poprzedniBlok = obecnyBlok;
				wykonaneOdwolania++;
			}
			czas++;
		}
	}
	
	public int dajNajblizszy(int obecnyBlok, int czas){
		int i = 0;
		int j = 0;
		while(obecnyBlok - i >= 0 || obecnyBlok + j < dysk.bloki.length){
			if(obecnyBlok - i >= 0 && dysk.czyJestOdwolanieDlaBlokuWCzasie(obecnyBlok - i, czas)){
				return obecnyBlok - i;
			}
			if(obecnyBlok + j < dysk.bloki.length && dysk.czyJestOdwolanieDlaBlokuWCzasie(obecnyBlok + j, czas)){
				return obecnyBlok + j;
			}
			i++;
			j++;
		}
		return -1;
	}
	
}
