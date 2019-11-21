/**
 * AbsCon - Copyright (c) 2017, CRIL-CNRS - lecoutre@cril.fr
 * 
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the terms of the CONTRAT DE LICENCE DE LOGICIEL LIBRE CeCILL which accompanies this
 * distribution, and is available at http://www.cecill.info
 */
package org.xcsp.modeler.problems;

import org.xcsp.common.IVar.Var;
import org.xcsp.modeler.api.ProblemAPI;

public class Frequences implements ProblemAPI {

	Map<String, Object> stations [];
	int [] regions;
	Map<String, int>[] interferences;
	Map<String, int>[] liaisons;

	Var[] allocated_r = array("allocated_frequencies_r", size(n), dom(range(n)), "allocated_frequencies_r[i] : frequence allouée au recepteur");
	Var[] allocated_e = array("allocated_frequencies_e", size(n), dom(range(n)), "allocated_frequencies_r[i] : frequence allouée à l'emetteur");

	//=================================LIAISONS=============================================
	
	private boolean liaisonExists(int i, int j){
		for (l : this.liaisons){
			if( l ["x"] == i && l ["y"] == j)
				return true;
		}
		return false;

	}	

	private void liasonsRules(int N){
		for (int i = 0; i < N ; i ++){
			for (int j = i; j < N ; j ++){
				if(liasonExists(i,j){
					eq(this.allocated_e[i],this.allocated_r[j]);
					eq(this.allocated_e[j],this.allocated_r[j]);
				}
			}
		}
	}

	//=================================INTERFERENCES EMETTEUR-RECEPTEUR=====================

	//ensure that the emission frequency is different enough from the reception frequency
	private void deltaI(int i){
		// |allocated_r[i] - allocated_e[i]| <= delta
		delta = this.stations[i]["delta"];
		eq(dist(this.allocated_r[i], this.allocated_e[i]), delta);
	}

	private void diRules(int N){
		for (int i = 0 ; i < N ; i ++){
			delta_i(i);
	}


	//=================================INTERFERENCES========================================
	private boolean deltaIJ(int i, int j){
		for (l : this.interferences){
			if( l ["x"] == i && l ["y"] == j)
				return l["Delta"];
		}
		return null;
	}

	private void deltaIJRules(int N){

		for (int i = 0 ; i < N ; i ++)
			for (int j = 0 ; j < N ; j ++){
				int delta = deltaIJ(i,j);	
				if(delta != null){
					ge(dist(this.allocated_r[i], this.allocated_r[j]), delta);
					ge(dist(this.allocated_r[i], this.allocated_e[j]), delta);
					ge(dist(this.allocated_e[i], this.allocated_r[j]), delta);
					ge(dist(this.allocated_e[i], this.allocated_e[j]), delta);
				}
			}
	}

	@Override
	public void model() {
		int n = 1;
		
		deltaIJRules(n);
		diRules(n)
		liasonsRules(n)
		//???
		//minimize({allocated_r,allocated_e})
	}

}
