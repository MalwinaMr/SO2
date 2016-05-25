package algorytmy;

import model.Dysk;
import model.Odwolanie;

public class FCFS {
	
	public int iloscPrzesuniec = 0;
	Dysk dysk = new Dysk();
	
	public void wykonaj(){
		int poprzedniBlok = dysk.odwolania.get(0).blok;
		for(Odwolanie o : dysk.odwolania){
			iloscPrzesuniec += Math.abs(o.blok - poprzedniBlok);
			poprzedniBlok = o.blok;
		}
	}

}
