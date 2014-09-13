package calc;

import java.io.Serializable;


/**
 * Class Content
 */

public abstract class Content implements Serializable{

    protected int _linePos, _columnPos;
    protected int _value= -1;
    protected String _name = "";
    protected String _str  = "";
    protected Content _cont, _cont2;
    
    public void setPosition(int line, int column){
		_linePos = line;
		_columnPos = column;
    }
    
    public void setLinePosition(int pos){
		_linePos = pos;
    }
    
    public void setColumnPosition(int pos){
		_columnPos = pos;
    }
    
    public int getLinePosition(){
		return _linePos;
	}

    public int getColumnPosition(){
	   return _columnPos;
    }

	public void setContent(Content cont){
		_cont = cont;
    }
    
    public Content getContent(){
	    return _cont;
    }
    
    public void setValue(int value){
		_value = value;
    }
    
    public int getValue(){
		return _value;
    }
    
    public void setName(String name){
		_name = name;
    }
    
    public String getName(){
		return _name;
    }
   
    
    public void setString(String str){
		_str = str;
    }
    
    public String getString(){
		return _str;
    }
    
    
    public int getPointLine(){
		return -1;
    }
    public int getPointColumn(){
		return -1;
    }
    
    public void setPointLine(int pointLine){}
    
    public void setPointColumn(int pointColumn){}
    
    public int calculate(ReferenceCell cell, ReferenceCellList list){
		return -1;
	}
    
    public void setContent1(Content cont1){
		_cont = cont1;
	}
	
    public Content getContent1(){
	    return _cont;
    }
    
    public void setContent2(Content cont2){
		_cont2 = cont2;
	}
    public Content getContent2(){
	    return _cont2;
    }
}