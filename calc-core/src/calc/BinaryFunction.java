/* BinaryFunction.java*/ 

package calc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class BinaryFunction
 */
 

abstract class BinaryFunction extends Function implements Serializable{
	
	protected BinaryFunction(Content cont1, Content cont2){
		super(cont1, cont2);       
    }
	
	
	public ArrayList<Integer> calc(ReferenceCell cell, ReferenceCellList list){
		ArrayList<Integer> vec = new ArrayList<Integer>();
		ReferenceCell cell2 = cell;
		int pointline=-1, pointcolumn=-1;

		if(cell.getContent().getContent1() instanceof Literal){
			vec.add(cell.getContent().getContent1().getValue());
		}	
		else if(cell.getContent().getContent1() instanceof ReferenceCell){
			
			do{
			  pointline = cell.getContent().getContent1().getPointLine();
			  pointcolumn = cell.getContent().getContent1().getPointColumn();
			  if(pointline == -1 || pointcolumn == -1){
				  vec.add(-100);
				  return vec;
			  }
			  cell = list.find(pointline, pointcolumn);
			  if(cell==null){
				  vec.add(-100);System.out.println("null");
				  return vec;			  
			  }
			}
			while(cell.getContent() instanceof ReferenceCell);	
			/* quando encontrar o literal apontado pede o seu valor */
			vec.add(cell.getContent().getValue());
		}
		
		if(cell2.getContent().getContent2() instanceof Literal){
			vec.add(cell2.getContent().getContent2().getValue()); 
		}
		else if(cell2.getContent().getContent2() instanceof ReferenceCell){
			
			do{
			  pointline = cell2.getContent().getContent2().getPointLine();
			  pointcolumn = cell2.getContent().getContent2().getPointColumn();
			  if(pointline == -1 || pointcolumn == -1){
				vec.add(-100);
				return vec;
			  }
			  cell2 = list.find(pointline, pointcolumn);
			  if(cell2==null){
				vec.add(-100);
				return vec;
			  }
			}
			while(cell2.getContent() instanceof ReferenceCell);
			vec.add(cell2.getContent().getValue());
		}	
		return vec;
	}
	
	
	public abstract int calculate(ReferenceCell cell, ReferenceCellList list);
  
	
 
}
