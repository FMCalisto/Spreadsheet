package calc;

import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Manager.
 */
public class Manager implements Serializable {

    private boolean _changes, _pagecreated;
    private String _pageName;  /* nome da pagina activa */
    private Page _page = null;
    
   
	
    public Manager(){
	  _changes = false;
	  _pageName = "";
	  _pagecreated = false; /* começa sem pagina nenhuma */
    }
    
    public ArrayList<String> readImport(String datafile) throws FileNotFoundException, IOException{
    
		ArrayList<String> lines = new ArrayList<String>();
		
		BufferedReader in = new BufferedReader(new FileReader(datafile));
		while((datafile = in.readLine()) != null){
			lines.add(datafile);
		}
		in.close();
		
		return lines;
	}
	

    public int importPageLines(String line){
		int nlines=0;
		String [] aux;
	  
		aux = line.split("=");
		if(aux[0].equals("linhas")){
			nlines = Integer.parseInt(aux[1]);
		}
		return nlines;
    
    }
    
    public int importPageColumns(String line){
		int ncolumns=0;
		String []aux;

		aux = line.split("=");
		if(aux[0].equals("colunas")){
			ncolumns = Integer.parseInt(aux[1]);
		}
		return ncolumns;
    
    }
    
    /* inicializa por dados textuais */
    public void initializeImport(Page page, ArrayList<String> lines){
		Content cont;
		int fileLine;  /* linha do ficheiro a ler */
		int cellLine=-1, cellColumn=-1;
		String str;		String []aux;		String []aux2;
		_page = page;
	  
		for(fileLine=2; fileLine<lines.size(); fileLine++){
			str = lines.get(fileLine);
			aux = str.split("\\|");
			aux2 = aux[0].split(";");	
			cellLine = Integer.parseInt(aux2[0]);
			cellColumn = Integer.parseInt(aux2[1]);
			cont = _page.createContent(aux[1], cellLine, cellColumn);
			_page.createCell(cellLine, cellColumn, cont);
		}
		
	} 

     /* abre a pagina do ficheiro: pageName */
    public void createPage(String pageName, String nlines, String ncolumns){ 	  
		int lines = Integer.parseInt(nlines);
		int columns = Integer.parseInt(ncolumns);
		
		Page _page = new Page(lines,columns);
		pageIsCreated(true);
        setActivePage(_page);
		_page.setName(pageName); /* escrever o nome na propria pagina (que foi aberta) */
		setPageName(pageName);  /* alterar o nome da pagina activa no manager */

    }
    
    /* grava a pagina num ficheiro: pageName */
    public void save(String pageName) throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pageName));
		out.writeObject(_page);
		out.close();
    
		setPageName(pageName);
		setChanges(false);
		_page.setName(pageName);
    }
    
    public void saveAs(String pageName) throws IOException{
		save(pageName);
    }
    
    public void open(String pageName) throws FileNotFoundException, ClassNotFoundException, IOException{
		Page page = new Page();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(pageName));
		page = (Page) in.readObject();
		in.close();

		createPage(pageName,"" + page.getNLines(), "" + page.getNColumns());
		retrieveReferenceCellList(page.getCellsList());
				
    
    }
    
    
    /* recupera os dados guardados na ReferenceCellList */
    public void retrieveReferenceCellList(ReferenceCellList list){
		_page.retrieveReferenceCellList(list);
		
    }
    /* Guarda os conteudos da folha aberta (por ficheiro) */
    public Page openContents(Page page){
		return _page;
    }
    
    public Page getActivePage(){				return _page;			}
    
    public void setActivePage(Page page){		_page = page;			}
    
    public String getPageName(){  				return _pageName;  		}
    
    public void setPageName(String pageName){	_pageName = pageName;   }

    public boolean getChanges(){				return _changes;		}

    public void setChanges(boolean changes){	_changes = changes;		}

    public void pageIsCreated(boolean answer){	_pagecreated = answer;	}

    public boolean isPageCreated(){				return _pagecreated;	}
    
}