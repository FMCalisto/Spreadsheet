package calc;

import java.io.Serializable;

/**
 * SearchByValue.
 */
class SearchByValue implements SearchSelection{

	private int _value;
	private ReferenceCellList _list, _foundlist;
	
	public SearchByValue(int value, ReferenceCellList list){
		_value = value;
		_list = list;
	}

	public void visit(DataStructure ds){
	
	  _foundlist = ds.findValue(_value);
	}
    
	
	
	public String search(){
	  String text = "";  
	  int i, finalvalue=-1;

		for(i=0; i<_foundlist.size(); i++){
		
			if(_foundlist.get(i).getContent() instanceof Literal){
				
				text += _foundlist.get(i).getLinePosition() + ";" + _foundlist.get(i).getColumnPosition() + "|" + _value;
			}
			
			else if(_foundlist.get(i).getContent() instanceof ReferenceCell){

					text += _foundlist.get(i).getLinePosition() + ";" + _foundlist.get(i).getColumnPosition() + "|" + _value + "=" + 
					_foundlist.get(i).getContent().getPointLine() + ";" + _foundlist.get(i).getContent().getPointColumn();
				}
			
			else if(_foundlist.get(i).getContent() instanceof Function){
				finalvalue = _foundlist.get(i).getContent().calculate(_foundlist.get(i), _list);
				if(finalvalue == -100) text += _foundlist.get(i).getLinePosition() + ";" + _foundlist.get(i).getColumnPosition() + "|#VALUE=" + _foundlist.get(i).getContent().getString();
				else	  text += _foundlist.get(i).getLinePosition() + ";" + _foundlist.get(i).getColumnPosition() + "|" + finalvalue + "=" + _foundlist.get(i).getContent().getString();  
		  
			  
				
			  }
			text += "\n";
			
		}
	  
	  return text.trim();
	  
    }
	

}