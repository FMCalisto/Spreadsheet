package calc;

import java.io.Serializable;

/**
 * SearchByFunc.
 */

class SearchByFunc implements SearchSelection{
	
	private String _func = "";
	private ReferenceCellList _list, _foundlist;
	
	public SearchByFunc(String func, ReferenceCellList list){
		_func = func;
		_list = list;
	}

	public void visit(DataStructure ds){
	
	  _foundlist = ds.findFuncName(_func);
	}
	

	public String search(){
		String text = "";  
		int i, finalvalue=-1;

		for(i=0; i<_foundlist.size(); i++){
	
		  finalvalue = _foundlist.get(i).getContent().calculate(_foundlist.get(i), _list);
		  if(finalvalue == -100) text += _foundlist.get(i).getLinePosition() + ";" + _foundlist.get(i).getColumnPosition() + "|#VALUE=" + _foundlist.get(i).getContent().getString();
		  else	  text += _foundlist.get(i).getLinePosition() + ";" + _foundlist.get(i).getColumnPosition() + "|" + finalvalue + "=" + _foundlist.get(i).getContent().getString();  
		  text += "\n";	
		}
	  return text.trim(); 
    }
	
}