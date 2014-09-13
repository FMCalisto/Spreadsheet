/** @version $Id: ShowCutBuffer.java,v 1.6 2013-11-29 18:35:34 ist169568 Exp $ */
package calc.textui.edit;

import java.io.IOException;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import calc.Page;


/**
 * Show cut buffer command.
 */
public class ShowCutBuffer extends Command<Page> {
	/**
	 * @param receiver
	 */
	public ShowCutBuffer(Page page) {
		super(MenuEntry.SHOW_CUT_BUFFER, page);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			
			try{
				IO.println(_receiver.showCutBuffer());
				
			}
				catch (NumberFormatException nbf) {}
        }

}
