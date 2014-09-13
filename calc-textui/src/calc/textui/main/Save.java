/** @version $Id: Save.java,v 1.9 2013-11-26 22:46:04 ist169568 Exp $ */
package calc.textui.main;

import java.io.IOException;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;
import calc.Manager;
import calc.Page;
import java.io.*;

/**
 * Command to save a file.
 */
public class Save extends Command<Manager> {

	/**
	 * @param receiver
	 */
	public Save(Manager manager) {
		super(false, MenuEntry.SAVE, manager, null);
		this.isValid();
	}
	
	@Override
	public boolean isValid() {
		if(_receiver.isPageCreated() == false)
			return false;
		return true;
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			String pageName = _receiver.getPageName();
		   
		    if(pageName == ""){	/* pagina anonima */
			  pageName = IO.readString(Message.newSaveAs());
			  try{
				_receiver.save(pageName);
			  }
			  catch(NullPointerException npe){}
			  catch (IOException ioe){}
		    }
		    else{	/* se a pagina ja tinha nome */
				if(_receiver.getChanges()){
					try{
					  _receiver.save(pageName);
						
					}
					catch(NullPointerException npe){}
					catch (IOException ioe){}
			}
	  
		   }	
	
        }

}
