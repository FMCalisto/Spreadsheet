/** @version $Id: MenuBuilder.java,v 1.5 2013-11-08 18:00:21 ist169568 Exp $ */
package calc.textui.main;

import ist.po.ui.Command;
import ist.po.ui.Menu;
import calc.Manager;

/**
 * Menu builder for the main menu.
 */
public abstract class MenuBuilder {

	/**
	 * FIXME: commands may have one or more receivers
	 */
	public static void menuFor(Manager manager) { 
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new New(manager),
				new Open(manager),
				new Save(manager),
				new SaveAs(manager),
				new MenuOpenEdit(manager),
				new MenuOpenSearch(manager),
        });
		menu.open();
	}

}
