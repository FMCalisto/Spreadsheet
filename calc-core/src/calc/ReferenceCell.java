package calc;

import java.io.Serializable;
/**
 * Class ReferenceCell
 */

class ReferenceCell extends Content implements Serializable{

    private int _pointLine = -1, _pointColumn = -1;

    public ReferenceCell(){
		_cont = null;
    }
       
    public ReferenceCell(int lineposition, int columnposition, Content cont){
        _linePos = lineposition;
        _columnPos = columnposition;
		_cont = cont;
		_pointLine = -1;
		_pointColumn = -1;
		
    }

	@Override
    public void setPointLine(int i){
	    _pointLine = i;
    }
    
    @Override
    public void setPointColumn(int i){
	    _pointColumn = i;
    }
    
    @Override
    public int getPointLine(){
	    return	_pointLine;
    }
    
    @Override
    public int getPointColumn(){
	    return	_pointColumn;
    }
    
}
