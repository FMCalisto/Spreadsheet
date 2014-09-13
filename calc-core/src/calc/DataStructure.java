package calc;

import java.io.Serializable;

/**
 * DataStructure.
 */
interface DataStructure extends Serializable{
    
    public void put(ReferenceCell c);
	
	public void accept(SearchSelection s);
	
	public ReferenceCell find(int linePosition, int columnPosition);
	
	public ReferenceCell findRefValue(int pointLine, int pointColumn);
	
	public ReferenceCellList findFuncName(String name);
	
	public ReferenceCellList findValue(int value);
	
	public ReferenceCell get(int index);
		
    public void replace(int index, ReferenceCell refcell);
    
    public void setFindIndex(int index);
    
    public int getFindIndex();
    
    public void deleteElement(int index);
  
	public void emptyAll();
	
	public int size();
}