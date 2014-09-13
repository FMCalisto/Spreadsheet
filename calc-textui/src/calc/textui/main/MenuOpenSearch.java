/** @version $Id: MenuOpenSearch.java,v 1.7 2013-11-09 13:06:02 ist169568 Exp $ */
package calc.textui.main;

import ist.po.ui.Command;
import ist.po.ui.ValidityPredicate;
import calc.Manager;

/**
 * Open search menu.
 */
public class MenuOpenSearch extends Command<Manager> { 

	/**
	 * @param receiver
	 */
	public MenuOpenSearch(Manager manager) {  
		super(false, MenuEntry.MENU_SEARCH, manager, null);
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
	public final void execute() {
	    if(_receiver != null)
		calc.textui.search.MenuBuilder.menuFor(_receiver, _receiver.getActivePage());
	}

}
