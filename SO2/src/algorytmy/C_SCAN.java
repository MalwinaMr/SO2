package algorytmy;

import model.Blok;
import model.Dysk;

public class C_SCAN {

	public int iloscPrzesuniec = 0;
	Dysk dysk = new Dysk();
	
	public void wykonaj(){
		int obecnyBlok = 50;
		int poprzedniBlok = 50;
		for(int i = 0 ; i < dysk.odwolania.size() ; ){
			obecnyBlok = dajNajblizszyLewy(obecnyBlok);
			if(obecnyBlok == 0 && dysk.bloki[obecnyBlok].odwolania.isEmpty()){
				obecnyBlok = 100;
			} else {
				Blok blok = dysk.bloki[obecnyBlok];
				blok.odwolania.remove(0);
				i++;
			}
			iloscPrzesuniec += Math.abs(obecnyBlok - poprzedniBlok);
			poprzedniBlok = obecnyBlok;
		}
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
