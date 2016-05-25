package symulacja;

import algorytmy.C_SCAN;
import algorytmy.FCFS;
import algorytmy.SCAN;
import algorytmy.SSTF;
import model.Dysk;

public class Symulacja {

	public static void main(String[] args) {
		Dysk dysk = new Dysk();
		FCFS fcfs = new FCFS(new Dysk(dysk));
		fcfs.wykonaj();
		System.out.println("Ilo�� przesuni�� FCSF: " + fcfs.iloscPrzesuniec);
		SSTF sstf = new SSTF(new Dysk(dysk));
		sstf.wykonaj();
		System.out.println("Ilo�� przesuni�� SSTF: " + sstf.iloscPrzesuniec);
		SCAN scan = new SCAN(new Dysk(dysk));
		scan.wykonaj();
		System.out.println("Ilo�� przesuni�� SCAN: " + scan.iloscPrzesuniec);
		C_SCAN c_scan = new C_SCAN(new Dysk(dysk));
		c_scan.wykonaj();
		System.out.println("Ilo�� przesuni�� C_SCAN: " + c_scan.iloscPrzesuniec);
	}

}
