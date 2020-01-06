package org.xcsp.modeler.problems;

import java.util.List;
import java.util.ArrayList;

import org.xcsp.common.IVar.Var;
import org.xcsp.modeler.api.ProblemAPI;

public class Frequences implements ProblemAPI {

	int[] regions;

	Station[] stations;

	Interference[] interferences;

	Liaison[] liaisons;


	class Station {
		int num, region, delta;
		int[] emetteur, recepteur;
	}

	class Interference {
		int x, y, Delta;
	}

	class Liaison {
		int x, y;
	}

	// =================================LIAISONS=============================================

	private boolean liaisonExists(int i, int j) {
		for (Liaison l : this.liaisons) {
			if (l.x == i && l.y == j)
				return true;
		}
		return false;

	}

	private void liasonsRules(int N, Var[] allocated_r, Var[] allocated_e) {
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (liaisonExists(i, j)) {
					equal(allocated_e[i], allocated_r[j]);
					equal(allocated_e[j], allocated_r[i]);
				}
			}
		}
	}

	// =================================INTERFERENCES
	// EMETTEUR-RECEPTEUR=====================

	// ensure that the emission frequency is different enough from the reception
	// frequency
	private void deltaI(int i, Var[] allocated_r, Var[] allocated_e) {
		int delta = this.stations[i].delta;
		equal(dist(allocated_r[i], allocated_e[i]), delta);
	}

	private void diRules(int N, Var[] allocated_r, Var[] allocated_e) {
		for (int i = 0; i < N; i++) {
			deltaI(i, allocated_r, allocated_e);
		}
	}

	// =================================INTERFERENCES========================================
	private int deltaIJ(int i, int j) {
		for (Interference inter : this.interferences) {
			if (inter.x == i && inter.y == j)
				return inter.Delta;
		}
		return 0;
	}

	private void deltaIJRules(int N, Var[] allocated_r, Var[] allocated_e) {

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				int delta = deltaIJ(i, j);
				if (delta != 0) {
					greaterEqual(dist(allocated_r[i], allocated_r[j]), delta);
					greaterEqual(dist(allocated_r[i], allocated_e[j]), delta);
					greaterEqual(dist(allocated_e[i], allocated_r[j]), delta);
					greaterEqual(dist(allocated_e[i], allocated_e[j]), delta);
				}
			}

	}

	private void minFrequencesRegions(int N, Var[] allocated_r, Var[] allocated_e){
		for (int region = 0; region < this.regions.length; region ++){
			ArrayList<Var> station_in_region_list = new ArrayList<Var>();
			for (Station s : this.stations){
				if (s.region ==region){
					station_in_region_list.add(allocated_r[s.num]);
					station_in_region_list.add(allocated_e[s.num]);
				}
			}
			Var[] station_in_region = new Var[station_in_region_list.size()] ;
			for (int i = 0; i < station_in_region_list.size(); i ++){
				station_in_region[i] = station_in_region_list.get(i);
			}
			nValues(station_in_region,LE,regions[region]);
		}
	}

	@Override
	public void model() {
		int n = this.stations.length;

		Var[] allocated_r = array("allocated_frequencies_r", size(n), i -> dom(stations[i].recepteur),
				"allocated_frequencies_r[i] : frequence allouée au recepteur");
		Var[] allocated_e = array("allocated_frequencies_e", size(n), i -> dom(stations[i].emetteur),
				"allocated_frequencies_e[i] : frequence allouée à l'emetteur");

		liasonsRules(n, allocated_r, allocated_e); // si il existe une liaison entre deux stations ij Ei=Rj et Ri=Ej

		diRules(n, allocated_r, allocated_e);// distance entre E et R = delta
		deltaIJRules(n, allocated_r, allocated_e);
		minFrequencesRegions(n, allocated_r, allocated_e);

	}

}