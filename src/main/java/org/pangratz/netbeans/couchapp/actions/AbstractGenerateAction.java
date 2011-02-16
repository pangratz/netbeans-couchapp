/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.awt.event.ActionEvent;
import org.openide.filesystems.FileUtil;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public abstract class AbstractGenerateAction extends AbstractCouchAppAction {

    public AbstractGenerateAction(CouchAppProject cap) {
        super(cap);
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
            FileUtil.refreshFor(couchAppDirectory);
        }
    }

    protected boolean hasOptions() {
        return false;
    }

    protected Object[] getOptions() {
        return null;
    }

    protected abstract String getMessage();

    protected abstract boolean generate(Object chosenOption);
}
