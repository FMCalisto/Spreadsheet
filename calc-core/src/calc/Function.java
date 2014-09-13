
package calc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Function
 */
 
abstract class Function extends Content implements Serializable{
	
	
	protected Function(Content cont1, Content cont2){
        _cont = cont1;
        _cont2 = cont2;
    }
    
    public abstract ArrayList<Integer> calc(ReferenceCell cell, ReferenceCellList list);
    
	public abstract int calculate(ReferenceCell cell, ReferenceCellList list);
	    
   
	
}
