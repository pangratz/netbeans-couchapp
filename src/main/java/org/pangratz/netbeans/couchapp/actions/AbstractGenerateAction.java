/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.awt.event.ActionEvent;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;

public abstract class AbstractGenerateAction extends AbstractCouchAppAction {

    public AbstractGenerateAction(Lookup context) {
        super(context);

        putValue(NAME, getName());
    }

    public AbstractGenerateAction() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String msg = getMessage();

        Object value;
        if (hasOptions()) {
            value = showOptionDialog(msg, getOptions());
        } else {
            value = showOptionDialog(msg);
        }

        // user cancelled dialog
        if (value == null) {
            return;
        }

        boolean fsModified = generate(value);

        // refresh the directory
        if (fsModified) {
            FileUtil.refreshFor(getCouchAppDirectory());
        }
    }

    protected boolean hasOptions() {
        return false;
    }

    protected Object[] getOptions() {
        return null;
    }

    protected abstract String getName();
    
    protected abstract String getMessage();

    protected abstract boolean generate(Object chosenOption);
}
