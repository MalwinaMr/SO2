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
		int kierunek = 1;
		for(int i = 0 ; i < dysk.odwolania.size() ; ){
			if(kierunek > 0){
				obecnyBlok = dajNajblizszyPrawy(obecnyBlok);
				if(obecnyBlok == dysk.bloki.length - 1 && dysk.bloki[obecnyBlok].odwolania.isEmpty()){
					kierunek = -1;
				} else {
					Blok blok = dysk.bloki[obecnyBlok];
					blok.odwolania.remove(0);
					i++;
				}
			} else {
				obecnyBlok = dajNajblizszyLewy(obecnyBlok);
				if(obecnyBlok == 0 && dysk.bloki[obecnyBlok].odwolania.isEmpty()){
					kierunek = 1;
				} else {
					Blok blok = dysk.bloki[obecnyBlok];
					blok.odwolania.remove(0);
					i++;
				}
			}
			iloscPrzesuniec += Math.abs(obecnyBlok - poprzedniBlok);
			poprzedniBlok = obecnyBlok;
		}
	}
	
	public int dajNajblizszyPrawy(int obecnyBlok){
		int i = 1;
		while(obecnyBlok + i < dysk.bloki.length){
			if(obecnyBlok + i < dysk.bloki.length && !dysk.bloki[obecnyBlok + i].odwolania.isEmpty()){
				return obecnyBlok + i;
			}
			i++;
		}
		return dysk.bloki.length - 1;
		
	}
	
	public int dajNajblizszyLewy(int obecnyBlok){
		int i = 1;
		while(obecnyBlok - i >= 0){
			if(obecnyBlok - i >= 0 && !dysk.bloki[obecnyBlok - i].odwolania.isEmpty()){
				return obecnyBlok - i;
			}
			i++;
		}
		return 0;
	}
}
