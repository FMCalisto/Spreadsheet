package calc;

import java.io.Serializable;

/**
 * SearchSelection.
 */
public interface SearchSelection{

	public String search();
	
	public void visit(DataStructure ds);

}