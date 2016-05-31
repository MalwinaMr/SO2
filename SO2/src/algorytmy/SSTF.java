package algorytmy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Blok;
import model.Dysk;
import model.OdwolaniaWgPriorytetu;
import model.Odwolanie;

public class SSTF {

	public int iloscPrzesuniec;
	public Dysk dysk;
	public List<Odwolanie> workingList;
	
	public SSTF(Dysk dysk){
		this.dysk = dysk;
	}
	
	public void wykonaj(){
		iloscPrzesuniec = 0;
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
	
	public void real_time(){
		workingList = new ArrayList<>();
		iloscPrzesuniec = 0;
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
				temp = workingList.remove(dajNajblizszyIndexBloku(poprzedniBlok));
				iloscPrzesuniec += Math.abs(temp.blok - poprzedniBlok);
				poprzedniBlok = temp.blok;
				wykonaneOdwolania++;
			}
			czas++;
		}
	}
	
	public int dajNajblizszyIndexBloku(int poprzedniBlok){
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
		if(Math.abs(workingList.get(indexPotencjalnego).blok - poprzedniBlok) < Math.abs(workingList.get(indexAktualnego).blok - poprzedniBlok)){
			return indexPotencjalnego;
		}
		return indexAktualnego;
	}
	
}
