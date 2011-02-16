/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class GenerateViewAction extends AbstractCouchAppAction {

    public GenerateViewAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Generate View ...");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String viewName = showOptionDialog("Enter name of view");

        if (viewName == null) {
            return;
        }

        try {
            couchappUtil.generateView(couchAppDirectory, viewName);
            FileUtil.refreshFor(couchAppDirectory);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
