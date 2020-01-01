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

		if (modelVariant("m1")) {
			Var nbfreq = var("nbfreq", dom(range(2 * n)));

			nValues(vars(allocated_r, allocated_e), EQ, nbfreq);
			minimize(nbfreq);
			// minimize(NVALUES,vars(allocated_r, allocated_e));//le solveur n integre pas
			// nvalues

		}
		if (modelVariant("m2")) {

			minimize(MAXIMUM, vars(allocated_r, allocated_e));// on veut minimiser la valeur maximale des frequences
																// utilisees == utiliser les fréquences les plus basses
																// possible
		}
		if (modelVariant("m3")) {
			List<Integer> freqs = new ArrayList<>();
			int m = 0;

			for (int i = 0; i < n; i++) {

				for (int r : stations[i].recepteur) {
					if (m < r)
						m = r;
					if (!freqs.contains(r))
						freqs.add(r);
				}
				for (int e : stations[i].emetteur) {
					if (m < e)
						m = e;
					if (!freqs.contains(e))
						freqs.add(e);
				}

			}

			Var maximum = var("maximum", dom(freqs));
			Var minimum = var("minimum", dom(freqs));
			Var distdelta = var("distdelta", dom(range(m + 1)));//Possible to reduce size by creating a list with the dist between all elements of freqs

			equal(maximum, max(vars(allocated_r, allocated_e)));
			equal(minimum, min(vars(allocated_r, allocated_e)));
			equal(distdelta, dist(maximum, minimum));
			minimize(distdelta);
		}

	}

}
