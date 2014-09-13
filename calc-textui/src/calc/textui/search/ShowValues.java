/** @version $Id: ShowValues.java,v 1.4 2013-12-02 16:15:55 ist169568 Exp $ */
package calc.textui.search;

import java.io.IOException;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import calc.Page;

/**
 * Class for searching values.
 */
public class ShowValues extends Command<Page> {
	/**
	 * @param receiver
	 */
	public ShowValues(Page page) {
		super(MenuEntry.SEARCH_VALUES, page );
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			String value = "", text = "";
			
             try{
				value = IO.readString(Message.searchValue());
				
				text = _receiver.searchValue(Integer.parseInt(value));
				
				IO.println(text);
			}
			
			catch (NumberFormatException nbf) {}
			catch (IOException nbf) {}
        }

}
