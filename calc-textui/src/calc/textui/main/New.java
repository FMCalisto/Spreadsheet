/** @version $Id: New.java,v 1.12 2013-11-26 22:46:04 ist169568 Exp $ */
package calc.textui.main;

import java.io.IOException;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import java.io.*;
import calc.Manager;
import calc.Page;

/**
 * Create a new spreadsheet.
 */
public class New extends Command<Manager> {

	/**
	 * @param receiver
	 */
	public New(Manager manager) {
		super(MenuEntry.NEW, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
            String pagename, linesrequest, columnsrequest;
                
            try{
				if (_receiver.isPageCreated()) /* se ja existe uma pagina */
					if(IO.readBoolean(Message.saveBeforeExit())){
						pagename = IO.readString(Message.newSaveAs());
						_receiver.save(pagename);
				}
				/* Executa o New em seguida */
				linesrequest = IO.readString(Message.linesRequest());
				columnsrequest = IO.readString(Message.columnsRequest());
				_receiver.createPage("", linesrequest, columnsrequest);
			}
			catch(IOException ioe){}
	    
	    }
        

}





