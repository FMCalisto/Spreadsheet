/** @version $Id: SaveAs.java,v 1.7 2013-11-27 19:31:08 ist169568 Exp $ */
package calc.textui.main;

import java.io.IOException;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;
import calc.Manager;
import calc.Page;

/**
 * Command to save a file with a new name.
 */
public class SaveAs extends Command<Manager> {
	
	/**
	 * @param receiver
	 */
	public SaveAs(Manager manager) {
		super(false,MenuEntry.SAVE_AS, manager, null);
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
		  String name = "";
		  try{
			  if (_receiver.getChanges())
				  name = IO.readString(Message.saveAs());
			  
			  else
				  name = IO.readString(Message.newSaveAs());
		
			  _receiver.saveAs(name);
		  }
		  catch(IOException ioe){}
		  
	  }

}
