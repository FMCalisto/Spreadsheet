/** @version $Id: Insert.java,v 1.5 2013-11-28 00:02:35 ist169568 Exp $ */
package calc.textui.edit;

import java.io.IOException;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import calc.Page;


/**
 * Insert command.
 */
public class Insert extends Command<Page> {
	/**
	 * @param receiver
	 */
	public Insert(Page page) {
		super(MenuEntry.INSERT, page);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
            boolean isvalid;
            String range = "", content = "";
			try{
				range = IO.readString(Message.addressRequest());
				
				isvalid = _receiver.analizeRangeValidity(range);
				if(!isvalid)
					throw new calc.textui.edit.InvalidCellRange(range);
				else{
					content = IO.readString(Message.contentsRequest());
					_receiver.insert(content);
				}
			}
				catch (NumberFormatException nbf) {}
        }

}
