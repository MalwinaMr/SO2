package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dysk {

	public int iloscBlokow;
	public int liczbaOdwolan;
	public Blok[] bloki;
	public List<Odwolanie> odwolania;
	public Map<Integer, List<Odwolanie>> odwolaniaWgCzasu;

	public Dysk(int iloscBlokow, int liczbaOdwolan) {
		this.iloscBlokow = iloscBlokow;
		this.liczbaOdwolan = liczbaOdwolan;
		init();
		initMap();
	}

	public Dysk(Dysk dysk) {
		this.iloscBlokow = dysk.iloscBlokow;
		this.liczbaOdwolan = dysk.liczbaOdwolan;
		this.bloki = new Blok[iloscBlokow];
		this.odwolania = new ArrayList<>();
		this.odwolaniaWgCzasu = new HashMap<>();
		for (int i = 0; i < this.iloscBlokow; i++) {
			this.bloki[i] = new Blok();
		}
		for (Odwolanie odwolanie : dysk.odwolania) {
			this.odwolania.add(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok, odwolanie.priorytet));
			this.bloki[odwolanie.blok].odwolania
					.add(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok, odwolanie.priorytet));
			if (this.odwolaniaWgCzasu.containsKey(odwolanie.czasPrzyjscia)) {
				this.odwolaniaWgCzasu.get(odwolanie.czasPrzyjscia)
						.add(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok, odwolanie.priorytet));
			} else {
				this.odwolaniaWgCzasu.put(odwolanie.czasPrzyjscia, new ArrayList<Odwolanie>(
						Arrays.asList(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok, odwolanie.priorytet))));
			}
		}
	}

	public void init() {
		Random random = new Random();
		this.bloki = new Blok[iloscBlokow];
		this.odwolania = new ArrayList<>();
		for (int i = 0; i < this.iloscBlokow; i++) {
			this.bloki[i] = new Blok();
		}
		int blok = 0;
		for (int i = 0; i < this.liczbaOdwolan; i++) {
			blok = random.nextInt(this.iloscBlokow);
			Odwolanie o = new Odwolanie(random.nextInt(21), blok, random.nextInt(4) + 1);
			this.odwolania.add(o);
			this.bloki[blok].odwolania.add(o);
		}
		for (int i = 0; i < iloscBlokow; i++) {
			Collections.sort(this.bloki[i].odwolania, new OdwolaniaWgCzasuPrzyjscia());
		}
		Collections.sort(this.odwolania, new OdwolaniaWgCzasuPrzyjscia());
	}

	public void initMap() {
		this.odwolaniaWgCzasu = new HashMap<>();
		for (Odwolanie o : this.odwolania) {
			if (this.odwolaniaWgCzasu.containsKey(o.czasPrzyjscia)) {
				this.odwolaniaWgCzasu.get(o.czasPrzyjscia).add(o);
			} else {
				this.odwolaniaWgCzasu.put(o.czasPrzyjscia, new ArrayList<Odwolanie>(Arrays.asList(o)));
			}
		}
	}

	public Boolean czyJestOdwolanieDlaBlokuWCzasie(int blok, int czas) {
		if (this.bloki[blok].odwolania.isEmpty()) {
			return false;
		} else if (this.bloki[blok].odwolania.get(0).czasPrzyjscia <= czas) {
			return true;
		}
		return false;
	}

}
