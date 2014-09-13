/** @version $Id: Open.java,v 1.11 2013-11-26 22:46:04 ist169568 Exp $ */
package calc.textui.main;

import java.io.IOException;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import calc.Manager;
import calc.Page;
import java.io.*;

/**
 * Command to open a file.
 */
public class Open extends Command<Manager> {

	/**
	 * @param receiver
	 */
	public Open(Manager manager) {
		super(MenuEntry.OPEN, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			String pagename, answer;
			int nlines, ncolumns;

			
			if(_receiver.getChanges()){
				/* pergunta se quer gravar antes de abrir outra pagina */
				if (IO.readBoolean(Message.saveBeforeExit())){ 
					pagename = _receiver.getPageName();
					
					if (pagename == ""){  /* pagina anonima */
						pagename = IO.readString(Message.newSaveAs());			
						_receiver.save(pagename);
						 
					}
					else  /* a pagina ja tinha nome */
						_receiver.save(pagename);
				}
				else	/* gravar? nao */
					_receiver.setChanges(false);
				
            }          
            
            /* Executa o open em seguida */
			pagename = IO.readString(Message.openFile());
			try{	
				_receiver.open(pagename);
			}		   
		    catch (FileNotFoundException fnf){
			  IO.readString(Message.fileNotFound());
		    }  
		    catch(ClassNotFoundException e){}
		    catch (IOException ioe){}              
        }
}
