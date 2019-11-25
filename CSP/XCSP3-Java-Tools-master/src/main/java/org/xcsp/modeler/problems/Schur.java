package org.xcsp.modeler.problems;

import org.xcsp.common.IVar.Var;
import org.xcsp.modeler.api.ProblemAPI;

public class Schur implements ProblemAPI {
//On passe en argument via -data[n,k] les valeurs de n et k
	int n;
	int k;
	@Override
	public void model() {
		Var x[] = array("x", size(n), dom(range(k)));		
		
		for (int i = 0; i < n; i++)
			for(int j = i+1; j < n-1; j++)
				if(i+j+1<n)
					notAllEqual(x[i],x[j],x[i+j+1]);//+1 car on commence a 0
		
	}
}