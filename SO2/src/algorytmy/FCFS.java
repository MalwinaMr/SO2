package algorytmy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Dysk;
import model.OdwolaniaWgPriorytetuICzasu;
import model.Odwolanie;

public class FCFS {
	
	public int iloscPrzesuniec;
	Dysk dysk;
	public List<Odwolanie> workingList;
	
	public FCFS(Dysk dysk){
		this.dysk = dysk;
	}
	
	public void wykonaj(){
		iloscPrzesuniec = 0;
		int poprzedniBlok = 50;
		for(Odwolanie o : dysk.odwolania){
			iloscPrzesuniec += Math.abs(o.blok - poprzedniBlok);
			poprzedniBlok = o.blok;
		}
	}
	
	public void real_time(){
		iloscPrzesuniec = 0;
		workingList = new ArrayList<>();
		int poprzedniBlok = 50;
		int wykonaneOdwolania = 0;
		int czas = 0;
		Odwolanie temp;
		while(wykonaneOdwolania < dysk.liczbaOdwolan){
			if(dysk.odwolaniaWgCzasu.containsKey(czas)){
				workingList.addAll(dysk.odwolaniaWgCzasu.get(czas));
				Collections.sort(workingList, new OdwolaniaWgPriorytetuICzasu());
			}
			if(!workingList.isEmpty()){
				temp = workingList.remove(0);
				iloscPrzesuniec += Math.abs(temp.blok - poprzedniBlok);
				poprzedniBlok = temp.blok;
				wykonaneOdwolania++;
			}
			czas++;
		}
	}

}
