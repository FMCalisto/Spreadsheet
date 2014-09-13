/** @version $Id: Copy.java,v 1.4 2013-11-28 00:02:35 ist169568 Exp $ */
package calc.textui.edit;

import java.io.IOException;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import calc.Page;


/**
 * Copy command.
 */
public class Copy extends Command<Page> {
	/**
	 * @param receiver
	 */
	public Copy(Page page){
		super(MenuEntry.COPY, page);
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
					_receiver.copy();
				}
			}
				catch (NumberFormatException nbf) {}
        
	}

}
