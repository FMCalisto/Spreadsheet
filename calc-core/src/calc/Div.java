
package calc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Div
 */

class Div extends BinaryFunction implements Serializable{

	public Div(Content cont1, Content cont2){
        super(cont1, cont2);       
    }
    
    public int calculate(ReferenceCell cell, ReferenceCellList list){
		 ArrayList<Integer> vec = new ArrayList<Integer>();
		 
		 vec = super.calc(cell, list);
		 
		 if(vec.get(0)==-100 || vec.get(1) == -100)	return -100;
		 
		 return vec.get(0)/vec.get(1);
	}
     

}