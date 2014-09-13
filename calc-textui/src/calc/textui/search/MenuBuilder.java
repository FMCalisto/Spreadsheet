/** @version $Id: MenuBuilder.java,v 1.4 2013-11-08 18:00:21 ist169568 Exp $ */
package calc.textui.search;

import ist.po.ui.Command;
import ist.po.ui.Menu;
import calc.Page;
import calc.Manager;
/**
 * Menu builder for search operations.
 */
public class MenuBuilder {

	/**
	 * FIXME: commands may have one or more receivers
	 */
	public static void menuFor(Manager manager, Page page) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ShowValues(page),
				new ShowFunctions(page),
				});
		menu.open();
	}

}
