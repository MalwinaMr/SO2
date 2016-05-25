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
		for(int i = 0 ; i < dysk.odwolania.size() ; i++){
			obecnyBlok = dajNajblizszy(obecnyBlok);
			Blok blok = dysk.bloki[obecnyBlok];
			blok.odwolania.remove(0);
			iloscPrzesuniec += Math.abs(obecnyBlok - poprzedniBlok);
			poprzedniBlok = obecnyBlok;
		}
	}
	
	public int dajNajblizszy(int obecnyBlok){
		int i = 0;
		int j = 0;
		while(obecnyBlok - i >= 0 || obecnyBlok + j < dysk.bloki.length){
			if(obecnyBlok - i >= 0 && !dysk.bloki[obecnyBlok - i].odwolania.isEmpty()){
				return obecnyBlok - i;
			}
			if(obecnyBlok + j < dysk.bloki.length && !dysk.bloki[obecnyBlok + j].odwolania.isEmpty()){
				return obecnyBlok + j;
			}
			i++;
			j++;
		}
		return -1;
	}
	
}
