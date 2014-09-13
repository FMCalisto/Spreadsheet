/** @version $Id: Calc.java,v 1.13 2013-11-28 00:02:35 ist169568 Exp $ */
package calc.textui;

import static ist.po.ui.Dialog.IO;
import java.io.IOException;
import ist.po.ui.DialogException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import calc.Manager;
import calc.Page;
/**
 * Class that represents the spreadsheet's textual interface.
 */
public class Calc {
  /**
   * @param args
   */
  @SuppressWarnings("nls")
  public static void main(String[] args) throws IOException {
  
    Manager manager = new Manager();
    
    String datafile = System.getProperty("import");
    ArrayList<String> lines = new ArrayList<String>();
	int nlines, ncolumns;
	
    if (datafile != null) {
		try{
			lines = manager.readImport(datafile);
			nlines = manager.importPageLines(lines.get(0));
			ncolumns = manager.importPageColumns(lines.get(1));
			Page page = new Page(nlines, ncolumns);
			manager.initializeImport(page, lines);
			manager.pageIsCreated(true);
	      }
	      catch (FileNotFoundException fnf)  {
                  //IO.readString(Message.fileNotFound());
                 // IO.println("File not found exception");
          } 
          catch (IOException ex)  {}
     } 
    calc.textui.main.MenuBuilder.menuFor(manager);
    IO.closeDown();
  }

}


