package algorytmy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Blok;
import model.Dysk;
import model.OdwolaniaWgPriorytetu;
import model.Odwolanie;

public class C_SCAN {

	public int iloscPrzesuniec;
	Dysk dysk;
	public List<Odwolanie> workingList;
	
	public C_SCAN(Dysk dysk){
		this.dysk = dysk;
	}
	
	public void wykonaj(){
		iloscPrzesuniec = 0;
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
				Collections.sort(workingList, new OdwolaniaWgPriorytetu());
			}
			if(!workingList.isEmpty()){
				temp = workingList.remove(dajNajblizszyIndex(poprzedniBlok));
				if(temp.blok > poprzedniBlok){
					iloscPrzesuniec += poprzedniBlok + dysk.iloscBlokow - 1;
					poprzedniBlok = dysk.iloscBlokow - 1;
				}
				iloscPrzesuniec += Math.abs(temp.blok - poprzedniBlok);
				poprzedniBlok = temp.blok;
				wykonaneOdwolania++;
			}
			czas++;
		}
	}
	
	public int dajNajblizszyIndex(int poprzedniBlok){
		int priorytet = workingList.get(0).priorytet;
		int index = 0;
		int i = 0;
		while(i < workingList.size() && workingList.get(i).priorytet == priorytet){
			index = blizszyBlok(poprzedniBlok, index, i);
			i++;
		}
		return index;
	}
	
	public int blizszyBlok(int poprzedniBlok, int indexAktualnego, int indexPotencjalnego){
		if(workingList.get(indexPotencjalnego).blok > poprzedniBlok){
			if(workingList.get(indexAktualnego).blok > poprzedniBlok){
				if(Math.abs(workingList.get(indexPotencjalnego).blok - dysk.iloscBlokow) < Math.abs(workingList.get(indexAktualnego).blok - dysk.iloscBlokow)){
					return indexPotencjalnego;
				}
			}
		} else {
			if(workingList.get(indexAktualnego).blok > poprzedniBlok){
				return indexPotencjalnego;
			} else {
				if(Math.abs(workingList.get(indexPotencjalnego).blok - poprzedniBlok) < Math.abs(workingList.get(indexAktualnego).blok - poprzedniBlok)){
					return indexPotencjalnego;
				}
			}
		}
		return indexAktualnego;
	}
}
