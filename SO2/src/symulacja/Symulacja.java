package symulacja;

import algorytmy.C_SCAN;
import algorytmy.FCFS;
import algorytmy.SCAN;
import algorytmy.SSTF;

public class Symulacja {

	public static void main(String[] args) {
		FCFS fcfs = new FCFS();
		fcfs.wykonaj();
		System.out.println("Ilo�� przesuni�� FCSF: " + fcfs.iloscPrzesuniec);
		SSTF sstf = new SSTF();
		sstf.wykonaj();
		System.out.println("Ilo�� przesuni�� SSTF: " + sstf.iloscPrzesuniec);
		SCAN scan = new SCAN();
		scan.wykonaj();
		System.out.println("Ilo�� przesuni�� SCAN: " + scan.iloscPrzesuniec);
		C_SCAN c_scan = new C_SCAN();
		c_scan.wykonaj();
		System.out.println("Ilo�� przesuni�� C_SCAN: " + c_scan.iloscPrzesuniec);
	}

}
