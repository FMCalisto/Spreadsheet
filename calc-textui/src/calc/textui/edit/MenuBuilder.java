/** @version $Id: MenuBuilder.java,v 1.5 2013-11-11 22:02:18 ist169568 Exp $ */
package calc.textui.edit;

import ist.po.ui.Command;
import ist.po.ui.Menu;
import calc.Page;
import calc.Manager;

/**
 * Menu builder for edit operations.
 */
public class MenuBuilder {

	/*
	 * FIXME: commands may have one or more receivers
	 */
	public static void menuFor(Manager manager, Page page) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new Show(page),
				new Insert(page),
				new Copy(page),
				new Delete(page),
				new Cut(page),
				new Paste(page),
				new ShowCutBuffer(page),
				});
		menu.open();
	}

}
