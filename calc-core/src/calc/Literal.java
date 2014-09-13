
package calc;

import java.io.Serializable;


/**
 * Class Literal
 */
 

class Literal extends Content implements Serializable{

	public Literal(){
		_value = -100; /* representa conteudos vazios */
	}

	public Literal(int value){	
		_value = value;
	}
	
}
