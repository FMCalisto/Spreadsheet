/** @version $Id: ShowFunctions.java,v 1.5 2013-12-02 16:15:55 ist169568 Exp $ */
package calc.textui.search;

import java.io.IOException;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import calc.Page;

/**
 * Class for searching functions.
 */
public class ShowFunctions extends Command<Page> {
	/**
	 * @param receiver
	 */
	public ShowFunctions(Page page) {
		super(MenuEntry.SEARCH_FUNCTIONS, page);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			String func = "" , text = "";
			
             try{
				func = IO.readString(Message.searchFunction());
				
				text = _receiver.searchFunction(func);
				
				IO.println(text);
			}
			
			catch (NumberFormatException nbf) {}
			catch (IOException nbf) {}
               
        }

}
