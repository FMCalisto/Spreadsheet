package calc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class IntervalFunction - herda 2 conteudos: inicio e fim do intervalo
 */
 

abstract class IntervalFunction extends Function implements Serializable{
    
    protected int _line1=-1, _column1=-1, _line2=-1, _column2=-1;
    
    protected IntervalFunction(Content cont1, Content cont2,int line1, int column1, int line2, int column2){
		super(cont1, cont2); 
		_line1 = line1;
		_column1 = column1;
		_line2 = line2;
		_column2 = column2;
    }
    
	public ArrayList<Integer> calc(ReferenceCell cell, ReferenceCellList list){		
		ArrayList<Integer> vec = new ArrayList<Integer>();
		ReferenceCell cell2 = cell;
		int pointline=-1, pointcolumn=-1, i=0, j=-1;
		int countl = _line2   - _line1;
		int countc = _column2 - _column1;

		/* Intervalo vertical.*/
		if(countl != 0){
			j=_line1;
			while(i<=countl){
				cell = list.find(j, _column1);
				if(cell==null){ 
					vec.add(-100);
					return vec;
				}
				if(cell.getContent() instanceof Literal){
					vec.add(cell.getContent().getValue());
				}	
				else if(cell.getContent() instanceof ReferenceCell){
					do{
						pointline = cell.getContent().getPointLine();
						pointcolumn = cell.getContent().getPointColumn();
						if(pointline == -1 || pointcolumn == -1){
							vec.add(-100);
							return vec;
						}
						cell = list.find(pointline, pointcolumn);
						if(cell==null){
							vec.add(-100);
							return vec;
						}
					}
					while(cell.getContent() instanceof ReferenceCell);
					/* quando encontrar o literal apontado guarda o seu valor */
					vec.add(cell.getContent().getValue());
			  }
			  i++; j++;
		  }
		}
		else{/* Intervalo horizontal.*/
			j=_column1;
			while(i<=countc){
				cell = list.find(_line1, j);
				if(cell==null){ 
					vec.add(-100);
					return vec;
				}
				if(cell.getContent() instanceof Literal){
					vec.add(cell.getContent().getValue());
				}	
				else if(cell.getContent() instanceof ReferenceCell){
					do{
						pointline = cell.getContent().getPointLine();
						pointcolumn = cell.getContent().getPointColumn();
						if(pointline == -1 || pointcolumn == -1){
							vec.add(-100);
							return vec;
						}
						cell = list.find(pointline, pointcolumn);
						if(cell==null){
							vec.add(-100);
							return vec;
						}
					}
					while(cell.getContent() instanceof ReferenceCell);
					vec.add(cell.getContent().getValue());
				}
				i++; j++;
			}	
		}
		return vec;
	}
	
	public abstract int calculate(ReferenceCell cell, ReferenceCellList list);
	
	
}
