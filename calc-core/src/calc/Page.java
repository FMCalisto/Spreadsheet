package calc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representativa de uma Pagina de Calculo.
 *
 * @author      Sara Santos
 *
 * @author      Francisco Maria Calisto
 *
 */
public class Page implements Serializable{
         
	private int _nlines = 0, _ncolumns = 0, _line1 = -1, _line2 = -1, _column1 = -1, _column2 = -1;  
	private String _name = "";
	private Content _c1, _c2;
	private boolean _pageCreated = false;
	private ReferenceCell _cell = new ReferenceCell();
	private ReferenceCell _auxcell = new ReferenceCell();
	private ReferenceCell _auxcell2 = new ReferenceCell();
	private ReferenceCellList _cellsList = new ReferenceCellList();
	private ReferenceCellList _cutBuffer = new ReferenceCellList();
   
    public Page(){}
        
	/**
	* Construcao da Pagina.
	*
	* @param nlines
	*        Numero de linhas da pagina em questao.
	*
	* @param ncolunas
	*        Numero de colunas da pagina em questao.
	*
	*/
	public Page(int nlines, int ncolumns)
    {
		_nlines = nlines;
		_ncolumns = ncolumns;
		_pageCreated = true;
	}
   
  
	/**
	* Criacao de Conteudos atraves da analise do import
	*
	* @param contstr
	*        String que representa o conteudo a ser analizado e posteriormente criado.
	*
	* @see Content
	*
	* @return Conteudo especifico.
	*/
  
	public Content createContent(String contstr, int cellLine, int cellColumn){
		String []aux;	String []aux2;	String []aux3;
		String []aux4;	String []aux5;	String []aux6;	String []aux7;
		
		aux = contstr.split("=");
		
		/* Tratar Literais.*/
		if(isInteger(aux[0])){
			return createLiteral(aux[0]);
		}
		
		/* Tratar Referencias. */
		if(isReference(aux[1])){
			return createReference(aux[1], cellLine, cellColumn);
		}
		
		/* Tratar Funçoes: */
		if(isBinaryFunction(aux[1])){
			return createBinaryFunction(aux[1], cellLine, cellColumn);
		}
		
		if(isIntervalFunction(aux[1])){
			return createIntervalFunction(aux[1], cellLine, cellColumn);
		}
		
		/* Tratar conteudo vazio. */
		else{	
			return new Literal();

		}
	}
  
	/**
	* Teste de inteiros
	*
	* @param str
	*        String de teste.
	*
	* @return verdadeiro caso a string de teste corresponda a um inteiro
	*/
	public boolean isInteger(String str){
		try{
			Integer.parseInt(str);
		}
		catch(NumberFormatException e){
			return false;
		}
		return true;	
	}
	
	/**
	* Criacao de literais
	*
	* @param str
	*        String a processar.
	*
	* @return Literal
	*/
	public Literal createLiteral(String str){
		return new Literal(processInteger(str));	
	}
	
	/**
	* Processamento de literais
	*
	* @param str
	*        String a processar.
	*
	* @return inteiro
	*/
	public int processInteger(String str){
		return Integer.parseInt(str);
	}
	
	/**
	* Teste de literais
	*
	* @param cont
	*        Conteudo de teste.
	*
	* @return booleano
	*/
	public boolean isLiteral(Content cont){
		if(cont instanceof Literal)
		    return true;
		return false;
	}
	
	/**
	* Teste de referencias
	*
	* @param str
	*        String de teste.
	*
	* @return booleano
	*/
	public boolean isReference(String str){
		String []str2;
		str2 = str.split(";");
		if((isInteger(str2[0])) && (isInteger(str2[1])))
			return true;
		return false;
	}
	
	/**
	* Outro teste de referencias
	*
	* @param cont
	*        Conteudo de teste.
	*
	* @return booleano
	*/
	public boolean isReference(Content cont){
		if(cont instanceof ReferenceCell)
				return true;
		return false;
	}
	
	/**
	* Criacao de referencias
	*
	* @param str
	*        String a processar.
	*
	* @return ReferenceCell
	*/
	public ReferenceCell createReference(String str, int cellLine, int cellColumn){
		String []str2;
		
		str2 = str.split(";");
		ReferenceCell myRef = new ReferenceCell();
		
		/* Adiciona ao arraylist de referencias nao associadas.*/
		myRef.setLinePosition(cellLine);
		myRef.setColumnPosition(cellColumn);
		myRef.setPointLine(processInteger(str2[0]));
		myRef.setPointColumn(processInteger(str2[1]));
		return myRef;
	}
	
	/**
	* Teste de funcoes 
	*
	* @param cont
	*        Conteudo de teste.
	*
	* @return booleano
	*/
	public boolean isFunction(Content cont){
		if ((cont instanceof Add) ||
			(cont instanceof Sub) ||
			(cont instanceof Mul) ||
			(cont instanceof Div) ||
			(cont instanceof Avg) ||
			(cont instanceof Pia))
				return true;
		return false;
	}
	
	/**
	* Teste de funcoes binarias
	*
	* @param str
	*        String de teste.
	*
	* @return booleano
	*/
	public boolean isBinaryFunction(String str){
		String []str2;	String []str3;	String []str4;
		
		str2 =  str.split("\\)");
		str3 =  str2[0].split("\\(");

		if (str3[0].equals("ADD") ||
			str3[0].equals("SUB") ||
			str3[0].equals("MUL") ||
			str3[0].equals("DIV"))
				return true;
		return false;
	}
	
	/**
	* Criacao de funcoes binarias
	*
	* @param str
	*        String a processar.
	* @param cellLine
	*        Posicao da linha.
	* @param cellColumn
	*        Posicao da coluna.
	*
	* @return BinaryFunction
	*/
	public BinaryFunction createBinaryFunction(String str, int cellLine, int cellColumn){
		BinaryFunction newfunc;
		String []str2;	String []str3;	String []str4;
		String []str5;	String []str6;
		
		str2 =  str.split("\\)"); 
		str3 =  str2[0].split("\\(");
		str4 =  str3[1].split(",");
	
		if(isInteger(str4[0])){
			_c1 = createLiteral(str4[0]);
		}
		else if(isReference(str4[0])){
			//_c1 = createReference(str4[0], cellLine, cellColumn);
			_c1 = createReference(str4[0],-1,-1);
		}
		
		if(isInteger(str4[1])){
			_c2 = createLiteral(str4[1]);
		}
		else if(isReference(str4[1])){
			_c2 = createReference(str4[1], -1, -1);
		}

		/* criaçao da funçao */
		
		if(str3[0].equals("ADD")){
			newfunc = new Add(_c1,_c2);
			
		}
		else if(str3[0].equals("SUB")){
			newfunc = new Sub(_c1,_c2);
			
		}
		else if(str3[0].equals("MUL")){
			newfunc = new Mul(_c1,_c2);
			
		}
		else	
			newfunc = new Div(_c1,_c2);
	
		newfunc.setLinePosition(cellLine);
		newfunc.setColumnPosition(cellColumn);
		newfunc.setContent1(_c1);
		newfunc.setContent2(_c2);
		newfunc.setString(str);
		newfunc.setName(str3[0]);
		return newfunc;
	}
	
	/**
	* Teste de funcoes de intervalo
	*
	* @param str
	*        String de teste.
	*
	* @return booleano
	*/
	public boolean isIntervalFunction(String str){
		String []str2;	String []str3;	String []str4;
		
		str2 =  str.split("\\)"); 
		str3 =  str2[0].split("\\(");

		if (str3[0].equals("AVG") ||
			str3[0].equals("PRD"))
				return true;
		return false;
	}
	
	/**
	* Criacao de funcoes de intervalo
	*
	* @param str
	*        String a processar.
	* @param cellLine
	*        Posicao da linha.
	* @param cellColumn
	*        Posicao da coluna.
	*
	* @return IntervalFunction
	*/
	public IntervalFunction createIntervalFunction(String str, int cellLine, int cellColumn){
		IntervalFunction newfunc;
		String []str2;	String []str3;	String []str4;
		String []str5;	String []str6;
		
		str2 =  str.split("\\)"); 
		str3 =  str2[0].split("\\(");
		str4 =  str3[1].split(":");
		str5 =  str4[0].split(";");
		str6 =  str4[1].split(";");
		
		_c1 = createReference(str4[0], cellLine, cellColumn);
		_c2 = createReference(str4[1], cellLine, cellColumn);
		
		/* criaçao da funçao */		
		if(str3[0].equals("AVG")){
			newfunc = new Avg(_c1,_c2, processInteger(str5[0]), processInteger(str5[1]), processInteger(str6[0]), processInteger(str6[1]));			
		}
		else
			newfunc = new Pia(_c1,_c2, processInteger(str5[0]), processInteger(str5[1]), processInteger(str6[0]), processInteger(str6[1]));
		
		newfunc.setLinePosition(cellLine);
		newfunc.setColumnPosition(cellColumn);
		newfunc.setString(str);
		newfunc.setName(str3[0]);
		return newfunc;
	
	}

	/**
	* Cria uma celula (ReferenceCell) com conteudos.
	*
	* @param linePosition
	*        A posicao da sua linha.
	*
	* @param columnPosition
	*        A posicao da sua coluna.
	*
	* @param cont
	*        O conteudo que contem.
	*/
	public void createCell(int linePosition, int columnPosition, Content cont){
		ReferenceCell cell = new ReferenceCell(linePosition, columnPosition, cont);	
		/* Adiciona a celula à estrutura de dados escolhida. */
		_cellsList.put(cell);
	}
                                         
	public ReferenceCell createCell(Content cont, int linePosition, int columnPosition){
		ReferenceCell cell = new ReferenceCell(linePosition, columnPosition, cont);
		return cell;
	}
	/**
	* Funcao de analise da gama pedida para visualizar
	*
	* @param range
	*        Analisa se a gama pedida pelo utilizador pertence a uma celula valida na pagina.
	*
	* @return booleano
	*/
	public boolean analizeRangeValidity(String range){
		String []aux; String []aux2; String []aux3;

		aux = range.split(";");	
		_line1 = Integer.parseInt(aux[0]);
           
        /* Significa que e so 1 celula.*/
		if(isInteger(aux[1])){
			_column1 = Integer.parseInt(aux[1]);
		}
		
		else{ /* Significa que e um intervalo de celulas */
			aux2 = aux[1].split(":");
			aux3 = aux2[1].split(";");
			_column1 = Integer.parseInt(aux2[0]);
			_line2   = Integer.parseInt(aux3[0]);
			_column2 = Integer.parseInt(aux[2]);
		
			if((_line2 > _nlines) || (_column2 > _ncolumns))
				return false;
				
			if(_line1 != _line2)
				if(_column1 != _column2)
					return false;
			if(_column1 != _column2)
				if(_line1 != _line2)
					return false;	
		}
		if((_line1 > _nlines) || (_column1 > _ncolumns))
			return false;
		return true;
	}
	
	/**
	* Funcao que procura na estrutura de dados o valor correspondente a gama
	*	pedida para visualizar.      
	*
	* @return String com o resultado a imprimir no textui
	*/
	public String show(){
		String finaltext = "";
	
		if((_line1 == _line2) && (_column1 == _column2))
			_line2 = -1;
			
		/* Mostrar apenas 1 celula. */
		if(_line2 == -1)
			finaltext = showCell();
		
     	/* Mostrar um intervalo de celulas.*/
     	else
			finaltext = showInterval();
            
		/* Reset. */            
		_line2=-1; _column2=-1;
		
		return finaltext;
			
	}
	
    /**
	* Funcao que mostra uma celula      
	*  
	*/
    public String showCell(){
		  int value1=-1, value2=-1, pointline=-1, pointcolumn=-1, finalvalue=-1, backupline=-1, backupcolumn=-1;
		  String finaltext="";
		  
		  /* Aceder ao conteudo da celula atraves da posiçao.*/
		  _cell = _cellsList.find(_line1, _column1);
		  _auxcell = _cell;
		  if(_cell == null)	return _line1 + ";" + _column1 + "|"; 
		  
		  if(isFunction(_cell.getContent())){
		  	
			  finalvalue = _cell.getContent().calculate(_cell, _cellsList);
			  if(finalvalue == -100) return _line1 + ";" + _column1 + "|#VALUE=" + _cell.getContent().getString();
			  return _line1 + ";" + _column1 + "|" + finalvalue + "=" + _cell.getContent().getString();		
		  }
		  	  
		  else if(isReference(_cell.getContent())){
			  _auxcell2 = _cell;
			  backupline = _cell.getContent().getPointLine();
			  backupcolumn = _cell.getContent().getPointColumn();
			  
			  while(isReference(_cell.getContent())){				  
				  pointline = _cell.getContent().getPointLine();
				  pointcolumn = _cell.getContent().getPointColumn();
				  
				  _cell = _cellsList.find(pointline, pointcolumn);
				  if(_cell==null)
					   return _line1 + ";" + _column1 + "|#VALUE" + "=" + backupline + ";" +  backupcolumn; 
			  }
			  return _line1 + ";" + _column1 + "|" + _cell.getContent().getValue() + "=" + _auxcell2.getContent().getPointLine() + ";" + _auxcell2.getContent().getPointColumn(); 
		 }
		 else{ /* is Literal */
			  if(_auxcell == null)	return _line1 + ";" + _column1 + "|#VALUE";
			  if(_auxcell.getContent().getValue() == -100)	return _line1 + ";" + _column1 + "|";
			  return _line1 + ";" + _column1 + "|" + _auxcell.getContent().getValue();
		  }
     	}
    /**
	* Funcao que mostra um intervalo de celulas      
	*
	* @return String com o resultado a imprimir no textui
	*/ 	
    public String showInterval(){
    
		  int i=0, j=-1, value1=-1, value2=-1, pointline=-1, pointcolumn=-1;
		  int finalvalue=-1, backupline=-1, backupcolumn=-1, index=-1, flag=0;
		  int countl = _line2   - _line1;
		  int countc = _column2 - _column1;
		  String text="", finaltext="";
		  
		  /* Intervalo Vertical.*/
		  if(countl != 0){
			  j=_line1;
			  while(i<=countl){	
				  _cell = _cellsList.find(j, _column1);
				  _auxcell = _cell;
				  
				  if(_cell == null)	text += j + ";" + _column1 + "|";
				  else{
					  if(isLiteral(_cell.getContent())){
						  if(_cell.getContent().getValue() == -100) text += j + ";" + _column1 + "|";
						  else	  text += j + ";" + _column1 + "|" + _cell.getContent().getValue();
						     
					  }
					  else if(isFunction(_auxcell.getContent())){
							  finalvalue = _auxcell.getContent().calculate(_auxcell, _cellsList);
							  if(finalvalue == -100) 	text += j + ";" + _column1 + "|#VALUE=" + _cell.getContent().getString();
							  text += j + ";" + _column1 + "|" + finalvalue + "=" + _auxcell.getContent().getString();
					  }
					  
						else{
						  backupline = _cell.getContent().getPointLine();
						  backupcolumn = _cell.getContent().getPointColumn();
						  
						  do{		  
							  pointline = _auxcell.getContent().getPointLine();
							  pointcolumn = _auxcell.getContent().getPointColumn();
							  _auxcell = _cellsList.find(pointline, pointcolumn);
							  if(_auxcell==null){
								  text += j + ";" + _column1 + "|#VALUE" + "=" + backupline + ";" +  backupcolumn; 
								  flag=1;
							  }  
						  }
						  while(flag==0 && isReference(_auxcell.getContent()));
						  if(flag == 0)
							text += j + ";" + _column1 + "|" + _auxcell.getContent().getValue() + "=" + + _cell.getContent().getPointLine() + ";" + _cell.getContent().getPointColumn();  
					  } 
				  }
				  text += "\n";
				  j++; i++;
			  } 
		  }
			
		  /* Intervalo Horizontal.*/
		  else if(countc != 0){	
			  j=_column1; i=0; flag=0;
			  while(i<=countc){	
				  _cell = _cellsList.find(_line1, j);
				  _auxcell = _cell;

				  if(_cell == null)	text += _line1 + ";" + j + "|";
				  else{
					  if(isLiteral(_cell.getContent())){
						  if(_cell.getContent().getValue() == -100) text += _line1 + ";" + j + "|";
						  else	text += _line1 + ";" + j + "|" + _cell.getContent().getValue();
						   
					  }
					  else if(isFunction(_auxcell.getContent())){
							  finalvalue = _auxcell.getContent().calculate(_auxcell, _cellsList);
							  if(finalvalue == -100) 	text += _line1 + ";" + j + "|#VALUE=" + _cell.getContent().getString();
							  else						text += _line1 + ";" + j + "|" + finalvalue + "=" + _auxcell.getContent().getString();
					  }
					  
					  else{
						  backupline = _cell.getContent().getPointLine();
						  backupcolumn = _cell.getContent().getPointColumn();
						  
						  do{		  
							  pointline = _auxcell.getContent().getPointLine();
							  pointcolumn = _auxcell.getContent().getPointColumn();
							  _auxcell = _cellsList.find(pointline, pointcolumn);
							  if(_auxcell==null){
								  text += _line1 + ";" + j + "|#VALUE" + "=" + backupline + ";" +  backupcolumn; 
								  flag=1;
							  }  
						  }
						  while(flag==0 && isReference(_auxcell.getContent()));
						  if(flag == 0)
							text += _line1 + ";" + j + "|" + _auxcell.getContent().getValue() + "=" + + _cell.getContent().getPointLine() + ";" + _cell.getContent().getPointColumn();  
					 } 
				  }
				  text += "\n";
				  j++; i++;
			  } 
		  }
		return text.trim();
    }
    
    /**
	* Funcao que insere conteudos      
	*
	*/
	public void insert(String content){
		ReferenceCellList list = new ReferenceCellList();
		Content newcont;
		ReferenceCell newcell;
		int i=0, j=-1;
		int countl = _line2   - _line1;
		int countc = _column2 - _column1;
		
		if((_line1 == _line2) && (_column1 == _column2))
			_line2 = -1;
			
		/* Inserir apenas 1 celula. */
		if(_line2 == -1){
			newcont = createContent(content,_line1,_column1);
			newcell = createCell(newcont, _line1, _column1);
			insertCell(newcell, _line1, _column1);
		}
		
     	else{
			/* Intervalo vertical.*/
			if(countl != 0){	
				j=_line1;
				while(i<=countl){
					newcont = createContent(content, j, _column1);
					newcell = createCell(newcont, j, _column1);
					list.put(newcell);
					i++; j++;
				}
				insertInterval(list);
			}
			else{/* Intervalo horizontal.*/
				j=_column1;
				while(i<=countc){
					newcont = createContent(content, _line1, j);
					newcell = createCell(newcont, _line1, j);
					list.put(newcell);
					i++; j++;
				}
				insertInterval(list);
			}	
		}          
		_line2=-1; _column2=-1; /* Reset. */
	}
	
	/**
	* Funcao que insere uma celula     
	*
	*/
	public void insertCell(ReferenceCell cell, int line, int column){
		int index=-1;
		
		_cell = _cellsList.find(line, column);
		index = _cellsList.getFindIndex();
		
		if(_cell==null){
		  _cellsList.put(cell);
		  return;
		}
		_cellsList.replace(index, cell);
	}
	
	/**
	* Funcao que insere um intervalo      
	*
	*/
	public void insertInterval(ReferenceCellList list){
		int i, index=-1, line=-1, column=-1;		
		ReferenceCell newcell;
		
		for(i=0; i<list.size(); i++){
			newcell = list.get(i);
			line = newcell.getLinePosition();
			column = newcell.getColumnPosition();
	
			_cell = _cellsList.find(line, column);
			index = _cellsList.getFindIndex();
			
			if(_cell==null){
				_cellsList.put(newcell);
			}
			else
			_cellsList.replace(index, newcell);
		}
	}
	
	/**
	* Funcao que copia um conteudo para o cutbuffer   
	*
	*/
	public void copy(){
	  
		int i=0, j=-1;
		int countl = _line2   - _line1;
		int countc = _column2 - _column1;
		
		if((_line1 == _line2) && (_column1 == _column2))
			_line2 = -1;
			
		/* Copiar apenas 1 celula. */
		if(_line2 == -1){
			copyCell(_line1, _column1, true);	
		}
		
     	else{
			/* Intervalo vertical.*/
			if(countl != 0){	
				j=_line1;
				while(i<=countl){
					copyCell(j, _column1, false);
					i++; j++;
				}
			}
			else{/* Intervalo horizontal.*/
				j=_column1;
				while(i<=countc){
					copyCell(_line1, j, true);
					i++; j++;
				}	
			}
		}
		_line2=-1; _column2=-1; /* Reset. */
	}
	
	/**
	* Funcao que copia o conteudo de uma celula para o cutbuffer     
	*/
	public void copyCell(int copyline, int copycolumn, boolean horizontal){
		int bufline=-1, bufcol=-1, value=-1;
		ReferenceCell cell;
		Content cont;
		
		cell = _cellsList.find(copyline, copycolumn);
		if(cell==null){ 
			cont = new Literal();
			cell = createCell(cont, -1, -1);
		}
		
		value = _cutBuffer.size();
		if(horizontal){
		  bufline = 1;
		  bufcol = ++value;
		}
		else{
		  bufline = ++value;
		  bufcol = 1;
		}
		cell.setPosition(bufline, bufcol); /* para o cutbuffer começar em 1;1 */
		_cutBuffer.put(cell);	  
	}
	
	/**
	* Funcao que apaga um conteudo da lista de células     
	*/
	public void delete(){
		int i=0, j=-1;
		int countl = _line2   - _line1;
		int countc = _column2 - _column1;
		
		if((_line1 == _line2) && (_column1 == _column2))
			_line2 = -1;
			
		/* Eliminar apenas 1 celula. */
		if(_line2 == -1){
			deleteCell(_line1, _column1);	
		}
		
		else{
			/* Intervalo vertical.*/
			if(countl != 0){	
				j=_line1;
				while(i<=countl){
					deleteCell(j, _column1);
					i++; j++;
				}
			}
			else{/* Intervalo horizontal.*/
				j=_column1;
				while(i<=countc){
					deleteCell(_line1, j);
					i++; j++;
				}	
			}
		}	
		_line2=-1; _column2=-1; /* Reset. */
	}
	
	/**
	* Funcao que apaga uma celula da lista de células     
	*/
	public void deleteCell(int line, int column){
		int index=-1;

		_cell = _cellsList.find(line, column);
		index = _cellsList.getFindIndex();
		if(index==-1) 	return;
		_cellsList.deleteElement(index);
	}
	
	/**
	* Funcao que corta um conteudo      
	*
	*/
	public void cut(){
	  int backupline = _line2;
	  int backupcolumn = _column2;
	  copy();
	  
	  _line2 = backupline;
	  _column2 = backupcolumn;
	  delete();
	}
	
	/**
	* Funcao que cola os conteudos do cutbuffer na gama especificada      
	*
	*/
	public void paste(){
		Content cont;
		int i=0, j=-1, index=-1;
		int countl = _line2   - _line1;
		int countc = _column2 - _column1;
		int l=0, l2=0, c=0, c2=0;
		boolean horizontal = true;
		ReferenceCell cell;
		
		if((_line1 == _line2) && (_column1 == _column2))
			_line2 = -1;
			
		/* Colar todo o cutbuffer ate ao limite da folha. */
		if(_cutBuffer.size() > 0){
		  if(_line2 == -1){
			  l  = _cutBuffer.get(0).getLinePosition();
			  
			  if(_cutBuffer.size()>1){
				l2 = _cutBuffer.get(1).getLinePosition();
				if((l2-l)!=0)
					horizontal=false;
			  }	
			  pasteFromCell(_line1, _column1, horizontal);	
		  }
		
		  else{
			  /* Intervalo vertical.*/
			  if(countl != 0){	

				  if((_cutBuffer.size()-1) != countl)
					  return;
				  else{
					  while(i<=countl){
						  cont = _cutBuffer.get(i).getContent();
						  _cell = _cellsList.find(j, _column1);
						  index = _cellsList.getFindIndex();
						  cell = createCell(cont, j, _column1);
						  _cellsList.replace(index, cell);
						  i++; j++;
					  }
				  }
			  }
			  else{/* Intervalo horizontal.*/

				  if((_cutBuffer.size()-1) != countc)
					  return;
				  else
				  j=_column1;
				  while(i<=countc){
					  cont = _cutBuffer.get(i).getContent();
					  _cell = _cellsList.find(_line1, j);
					  index = _cellsList.getFindIndex();
					  cell = createCell(cont, _line1, j);
					  _cellsList.replace(index, cell);
					  i++; j++;
				  }	
			  }
		  }
		}
		_line2=-1; _column2=-1; /* Reset. */
	}
	
	/**
	*
	*/
	public void pasteFromCell(int line, int column, boolean horizontal){
		int i=0, k=1, index=-1, flag=0;	
		ReferenceCell cell;
		Content cont;
		
		if(horizontal){
			while(i<_cutBuffer.size() && k<=getNLines()){
				cont = _cutBuffer.get(i).getContent();
				_cell = _cellsList.find(line, column);
				if(flag==0)
				  index = _cellsList.getFindIndex();

				cell = createCell(cont, line, column);
				_cellsList.replace(index, cell);
				column++; i++; k++; index++; flag=1;
			}
		}
		else{
			while(i<_cutBuffer.size() && k<=getNColumns()){
				cont = _cutBuffer.get(i).getContent();
				_cell = _cellsList.find(line, column);
				
				if(flag==0)
				  index = _cellsList.getFindIndex();
				
				cell = createCell(cont, line, column);
				_cellsList.put(cell);
				line++; i++; k++;  index++; flag=1;
			}
		}
	}
	/**
	* Funcao que mostra os conteudos do cutbuffer      
	*
	*/
	public String showCutBuffer(){
		int i, finalvalue=-1, flag=0, backupline=-1, backupcolumn=-1, pointline=-1, pointcolumn=-1;
		String text = "";
		
		for(i=0; i<_cutBuffer.size(); i++){		
		  _cell = _cutBuffer.get(i);
		 
		  if(_cell == null)	return ""; // cutbuffer vazio
		  else{
			  if(isLiteral(_cell.getContent())){
				  if(_cell.getContent().getValue() == -100)		text += _cell.getLinePosition() + ";" + _cell.getColumnPosition() + "|";
				  else		text += _cell.getLinePosition() + ";" + _cell.getColumnPosition() + "|" + _cell.getContent().getValue();
			  }
			  else if(isFunction(_cell.getContent())){
				  finalvalue = _cell.getContent().calculate(_cell, _cellsList);
				  
				  if(finalvalue == -100) 	text += _cell.getLinePosition() + ";" + _cell.getColumnPosition() + "|#VALUE=" + _cell.getContent().getString();
				  else						text += _cell.getLinePosition() + ";" + _cell.getColumnPosition() + "|" + finalvalue + "=" + _cell.getContent().getString();
			  }
			  else{
				  backupline = _cell.getContent().getPointLine();
				  backupcolumn = _cell.getContent().getPointColumn();
				  _auxcell=_cell;
				  do{			  
					  pointline = _auxcell.getContent().getPointLine();
					  pointcolumn = _auxcell.getContent().getPointColumn();
					  _auxcell = _cellsList.find(pointline, pointcolumn);
					  if(_auxcell==null){
						  text += _cell.getLinePosition() + ";" + _cell.getColumnPosition() + "|#VALUE" + "=" + backupline + ";" +  backupcolumn; 
						  flag=1;
					  }  
				  }
				  while(flag==0 && isReference(_auxcell.getContent()));
				  text += _cell.getLinePosition() + ";" + _cell.getColumnPosition() + "|" + _auxcell.getContent().getValue() + "=" + + _cell.getContent().getPointLine() + ";" + _cell.getContent().getPointColumn();  
			  }
		  }
		  text += "\n";

		}
		return text.trim();
	}
	
	 /**
	* Funcao que pesquisa por nome de funcao      
	*
	*/
	 public String searchFunction(String function){
		String text = "";
		
		SearchSelection s = new SearchByFunc(function, _cellsList);  
		
		_cellsList.accept(s);
		
		text = s.search();	
		
		return text;
    }
    
    /**
	* Funcao que pesquisa por valor     
	*
	*/
    public String searchValue(int value){
		String text = "";
		
		SearchSelection s = new SearchByValue(value, _cellsList);  
		
		_cellsList.accept(s);
		
		text = s.search();	
		
		return text; 
    }
    
    
	/**
	* Recupera os dados guardados na ReferenceCellList (para as operacoes de guardar e abrir do textui).
	*
	* @param list
	*        Estrutura de dados das referencias para conteudos previamente guardadas.
	*
	* @see ReferenceCellList
	*/                                         
	public void retrieveReferenceCellList(ReferenceCellList list){
		_cellsList = list;
	}

	public void setName(String name){			_name = name;			}
                                              
	public int getNLines(){			return _nlines;			}                                          
   
	public int getNColumns(){		return _ncolumns;		}                                          
                                           
	public String getName(){		return _name;			}
   
	public boolean pageCreated(){	return _pageCreated;	}                    
	
	public ReferenceCellList getCellsList(){			return _cellsList;			}
}
