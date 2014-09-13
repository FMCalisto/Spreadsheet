package calc;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
/**
 * ReferenceCellList.
 */
 public class ReferenceCellList implements DataStructure, Serializable{

	private int _findIndex=-1;
    private List<ReferenceCell> myArray = new ArrayList<ReferenceCell>();    
    
    public void put(ReferenceCell c){
		myArray.add(c);
    }

    public void accept(SearchSelection s){
		s.visit(this);
    }
    
    public ReferenceCell find(int linePosition, int columnPosition){
		int i;
		for(i=0; i<this.size(); i++){
			if((linePosition  == this.get(i).getLinePosition()) && 
			(columnPosition == this.get(i).getColumnPosition())){
				setFindIndex(i);
				return this.get(i);
			}
		}
		return null;
    }
    
    
    public ReferenceCell findRefValue(int pointLine, int pointColumn){
		int i;
		for(i=0; i<this.size(); i++){
			if((pointLine  == this.get(i).getPointLine()) && 
			(pointColumn == this.get(i).getPointColumn())){
				return this.get(i);
			}	
		}
		return null;
    }
    
    
    public ReferenceCellList findFuncName(String name){
		int i;
		ReferenceCellList foundlist = new ReferenceCellList();

		for(i=0; i<this.size(); i++){
			if(this.get(i).getContent() instanceof Function){
				if(this.get(i).getContent().getName().equals(name)){
					foundlist.put(this.get(i));
				}
			}
		}
		return foundlist;
    }
    
    
    public ReferenceCellList findValue(int value){
		int i, finalvalue=-1, pointline=-1, pointcolumn=-1;
		ReferenceCell cell;
		ReferenceCellList foundlist = new ReferenceCellList();
		
		for(i=0; i<this.size(); i++){
		  
		  if(this.get(i).getContent() instanceof Function){
			  finalvalue = this.get(i).getContent().calculate(this.get(i), this);
			  if(finalvalue == value) 
				  foundlist.put(this.get(i));	
		  }
		  	  
		  else if(this.get(i).getContent() instanceof ReferenceCell){
			  cell = this.get(i);
			  while(cell.getContent() instanceof ReferenceCell){				  
				  pointline = cell.getContent().getPointLine();
				  pointcolumn = cell.getContent().getPointColumn();
				  cell = this.find(pointline, pointcolumn);	  
			  }
			  if(cell.getContent().getValue() == value)
					  foundlist.put(this.get(i));   
		 }
		 
		 else if(this.get(i).getContent() instanceof Literal){ 
			
			if(this.get(i).getContent().getValue() == value){
				foundlist.put(this.get(i));
			}
		}
	  }
	  return foundlist;
    }
    
    
    public ReferenceCell get(int index){
		return myArray.get(index);
    }
    
    public void replace(int index, ReferenceCell refcell){
		myArray.set(index, refcell);
    }
    
    public void setFindIndex(int index){
		_findIndex = index;
    }
    
    public int getFindIndex(){
		return _findIndex;
    }
    
    public void deleteElement(int index){
		myArray.remove(index);
    }
    
    public void emptyAll(){
		myArray.clear();
    }
 
	public int size(){
		return myArray.size();
	}
}