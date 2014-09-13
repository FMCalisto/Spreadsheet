/** @version $Id: MenuOpenEdit.java,v 1.7 2013-11-09 13:06:02 ist169568 Exp $ */
package calc.textui.main;

import ist.po.ui.Command;
import ist.po.ui.ValidityPredicate;
import calc.Page;
import calc.Manager;

/**
 * Open edit menu.
 */
public class MenuOpenEdit extends Command<Manager> { 
	/**
	 * @param receiver
	 */
	public MenuOpenEdit(Manager manager) {
		super(false , MenuEntry.MENU_CALC, manager, null);
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
		calc.textui.edit.MenuBuilder.menuFor(_receiver, _receiver.getActivePage());
	}

}
