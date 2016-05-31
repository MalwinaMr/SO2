package algorytmy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Blok;
import model.Dysk;
import model.OdwolaniaWgPriorytetu;
import model.Odwolanie;

public class SCAN {

	public int iloscPrzesuniec;
	Dysk dysk;
	public List<Odwolanie> workingList;
	
	public SCAN(Dysk dysk){
		this.dysk = dysk;
	}
	
	public void wykonaj(){
		iloscPrzesuniec = 0;
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
	
	public void real_time(){
		iloscPrzesuniec = 0;
		workingList = new ArrayList<>();
		int poprzedniBlok = 50;
		int wykonaneOdwolania = 0;
		int czas = 0;
		int kierunek = 1;
		Odwolanie temp;
		while(wykonaneOdwolania < dysk.liczbaOdwolan){
			if(dysk.odwolaniaWgCzasu.containsKey(czas)){
				workingList.addAll(dysk.odwolaniaWgCzasu.get(czas));
				Collections.sort(workingList, new OdwolaniaWgPriorytetu());
			}
			if(!workingList.isEmpty()){
				temp = workingList.remove(dajNajblizszyIndex(poprzedniBlok, kierunek));
				if(kierunek > 0){
					if(temp.blok < poprzedniBlok){
						iloscPrzesuniec += 2 * poprzedniBlok;
						kierunek = -1;
					}
				} else {
					if(temp.blok > poprzedniBlok){
						kierunek = 1;
						iloscPrzesuniec += 2 * poprzedniBlok;
					}
				}
				iloscPrzesuniec += Math.abs(temp.blok - poprzedniBlok);
				poprzedniBlok = temp.blok;
				wykonaneOdwolania++;
			}
			czas++;
		}
	}
	
	public int dajNajblizszyIndex(int poprzedniBlok, int kierunek){
		int priorytet = workingList.get(0).priorytet;
		int index = 0;
		int i = 0;
		while(i < workingList.size() && workingList.get(i).priorytet == priorytet){
			index = blizszyBlok(poprzedniBlok, index, i, kierunek);
			i++;
		}
		return index;
	}
	
	public int blizszyBlok(int poprzedniBlok, int indexAktualnego, int indexPotencjalnego, int kierunek){
		int odlegloscNowego = 0;
		int odlegloscAktualnego = 0;
		if(kierunek > 0){
			if(workingList.get(indexPotencjalnego).blok < poprzedniBlok){
				odlegloscNowego = 2 * poprzedniBlok;
			}
			if(workingList.get(indexAktualnego).blok < poprzedniBlok){
				odlegloscAktualnego = 2 * poprzedniBlok;
			}
			if(odlegloscNowego + Math.abs(workingList.get(indexPotencjalnego).blok - poprzedniBlok) < odlegloscAktualnego + Math.abs(workingList.get(indexAktualnego).blok - poprzedniBlok)){
				return indexPotencjalnego;
			}
		} else {
			if(workingList.get(indexPotencjalnego).blok > poprzedniBlok){
				odlegloscNowego = 2 * poprzedniBlok;
			}
			if(workingList.get(indexAktualnego).blok > poprzedniBlok){
				odlegloscAktualnego = 2 * poprzedniBlok;
			}
			
			if(odlegloscNowego + Math.abs(workingList.get(indexPotencjalnego).blok - poprzedniBlok) < odlegloscAktualnego + Math.abs(workingList.get(indexAktualnego).blok - poprzedniBlok)){
				return indexPotencjalnego;
			}
		}
		return indexAktualnego;
	}
}
