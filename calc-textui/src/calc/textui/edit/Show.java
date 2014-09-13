/** @version $Id: Show.java,v 1.9 2013-11-28 00:02:35 ist169568 Exp $ */
package calc.textui.edit;

import java.io.IOException;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import calc.Page;


/**
 * Show cells command.
 */
public class Show extends Command<Page> {
	/**
	 * @param receiver
	 */
	public Show(Page page) {
		super(MenuEntry.SHOW, page);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			boolean isvalid;
			try{
				String range = IO.readString(Message.addressRequest());
				
				isvalid = _receiver.analizeRangeValidity(range);
				if(!isvalid)
					throw new calc.textui.edit.InvalidCellRange(range);
				else{
					IO.println(_receiver.show());
				}
			}
			
			catch (NumberFormatException nbf) {}
			catch (IOException nbf) {}
        }

}
