
package calc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Avg
 */

class Avg extends IntervalFunction implements Serializable{
    
    public Avg(Content cont1, Content cont2, int line1, int col1, int line2, int col2){
		super(cont1, cont2, line1, col1, line2, col2);       
    }
    
    public int calculate(ReferenceCell cell, ReferenceCellList list){
		 ArrayList<Integer> vec = new ArrayList<Integer>();
		 int i, sum=0, avg=-1, nelements=-1;
		 
		 vec = super.calc(cell, list);
		 
		 for(i=0; i<vec.size(); i++){
			if(vec.get(i) == -100)
			  return -100;
			  
			sum += vec.get(i);
		 }
		 nelements = vec.size();
		 if(nelements==0) nelements=1;
		 
		 avg = sum/nelements;
		 
		 return avg;
	}
    
}