/**
 * AbsCon - Copyright (c) 2017, CRIL-CNRS - lecoutre@cril.fr
 * 
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the terms of the CONTRAT DE LICENCE DE LOGICIEL LIBRE CeCILL which accompanies this
 * distribution, and is available at http://www.cecill.info
 */
package org.xcsp.modeler.problems;
import java.util.Map;
import java.util.HashMap;
import org.xcsp.common.IVar.Var;
import org.xcsp.modeler.api.ProblemAPI;

public class Frequences implements ProblemAPI {

	//Map<String,Object>[] datas;
	HashMap<String, Object>[] stations ;
	int [] regions;
	HashMap<String, Integer>[] interferences;
	HashMap<String, Integer>[] liaisons;

	

	//=================================LIAISONS=============================================
	/*
	private boolean liaisonExists(int i, int j){
		for (HashMap<String, Integer> l : this.liaisons){
			if( l.get("x") == i && l .get("y") == j)
				return true;
		}
		return false;

	}	

	private void liasonsRules(int N){
		for (int i = 0; i < N ; i ++){
			for (int j = i; j < N ; j ++){
				if(liaisonExists(i,j)){
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
		int delta = this.stations[i].get("delta");
		eq(dist(this.allocated_r[i], this.allocated_e[i]), delta);
	}

	private void diRules(int N){
		for (int i = 0 ; i < N ; i ++){
			deltaI(i);
		}
	}


	//=================================INTERFERENCES========================================
	private int deltaIJ(int i, int j){
		for (HashMap<String, Integer>l : this.interferences){
			if( l.get("x") == i && l .get("y") == j)
				return l.get("Delta");
		}
		return 0;
	}

	private void deltaIJRules(int N){

		for (int i = 0 ; i < N ; i ++)
			for (int j = 0 ; j < N ; j ++){
				int delta = deltaIJ(i,j);	
				System.out.println(delta);
				if(delta != 0){
					ge(dist(this.allocated_r[i], this.allocated_r[j]), delta);
					ge(dist(this.allocated_r[i], this.allocated_e[j]), delta);
					ge(dist(this.allocated_e[i], this.allocated_r[j]), delta);
					ge(dist(this.allocated_e[i], this.allocated_e[j]), delta);
				}
			}
	}
*/
	@Override
	public void model() {
		int n = this.stations.length;
		
		
		//Var[] allocated_r= array("allocated_frequencies_r", size(10), dom(range(10)), "allocated_frequencies_r[i] : frequence allouée au recepteur");
		//Var[] allocated_e= array("allocated_frequencies_e", size(10), dom(range(10)), "allocated_frequencies_r[i] : frequence allouée à l'emetteur");
		/*deltaIJRules(n);
		diRules(n);
		liasonsRules(n);

		//???
		//minimize({allocated_r,allocated_e})*/
	}

}
